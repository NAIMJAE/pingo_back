package com.pingo.service.swipeService.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pingo.dto.swipe.SwipeRequest;
import com.pingo.entity.swipe.Swipe;
import com.pingo.exception.BusinessException;
import com.pingo.exception.ExceptionCode;
import com.pingo.mapper.SwipeMapper;
import com.pingo.service.swipeService.MatchService;
import com.pingo.util.KafkaTopics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class SwipeConsumerService {

    private final SwipeMapper swipeMapper;
    private final ObjectMapper objectMapper;
    private final MatchService matchService;

    @KafkaListener(topics = KafkaTopics.SWIPE_EVENTS, groupId = "swipe-consumer-group-1", concurrency = "3")
    public void consumeSwipeEvent(ConsumerRecord<String, String> record, Acknowledgment ack) {
        log.info("Kafka 리스너 실행됨");  // 실행 확인용 로그 추가
        log.info("Kafka 리스너 실행됨 - Partition: {}, Offset: {}", record.partition(), record.offset());

        SwipeRequest swipeRequest = null;

        try {
            swipeRequest = objectMapper.readValue(record.value(), SwipeRequest.class);

            if (swipeRequest == null || swipeRequest.getFromUserNo() == null ||
                    swipeRequest.getToUserNo() == null || swipeRequest.getSwipeType() == null) {
                log.error("[오류] Kafka 메시지 데이터 이상: {}", record.value());
                ack.acknowledge(); // 데이터가 이상하면 Offset을 커밋하여 재처리 방지
                return;
            }

            // Lambda에서 안전하게 사용하기 위해 final 변수 선언
            final SwipeRequest finalSwipeRequest = swipeRequest;
            final String fromUserNo = finalSwipeRequest.getFromUserNo();
            final String toUserNo = finalSwipeRequest.getToUserNo();
            final String swipeType = finalSwipeRequest.getSwipeType();

            // 1) PING 저장
            CompletableFuture<Void> saveSwipeFuture = CompletableFuture.runAsync(() -> { // runAsync() 반환값이 없는 비동기 처리
                Swipe swipe = new Swipe(finalSwipeRequest);
                log.info("[DEBUG] swipe 테이블 INSERT 실행 시작: {}", swipe.toString());
                swipeMapper.insertUserSwipe(swipe);
                log.info("[DEBUG] swipe 테이블 INSERT 실행 완료: {} -> {}, type: {}", fromUserNo, toUserNo, swipeType);

            });

            // 2) PANG이면 매칭 조회 생략, PING/SUPERPING이면 매칭 조회 실행
            if ("PANG".equalsIgnoreCase(swipeType)) {
                saveSwipeFuture.thenRun(ack::acknowledge) // PANG이면 바로 Kafka Offset 커밋
                        .exceptionally(ex -> {
                            log.error("[PANG 처리 중 오류 발생] {}", ex.getMessage(), ex);
                            return null;
                        });
            } else {
                CompletableFuture<Boolean> checkMatchFuture = CompletableFuture.supplyAsync(() -> { // supplyAsync() 반환값이 없는 비동기 처리
                    boolean result = swipeMapper.isSwipeMatched(fromUserNo, toUserNo);
                    log.info("[DEBUG] 매칭 여부 확인 결과: {} <-> {} => {}", fromUserNo, toUserNo, result);
                    return result;
                });

                // thenCombine() -> 두 작업을 독립적으로 실행하면서 둘 다 완료되었을 때 실행
                saveSwipeFuture.thenCombine(checkMatchFuture, (voidResult, isMatched) -> {
                            log.info("[DEBUG] 매칭 여부 확인 결과:" +isMatched);
                            if (isMatched) {
                                log.info("매칭 성공! {} <-> {}", fromUserNo, toUserNo);
                                // 매칭 성공시 매칭관련 작업 시작
                                matchService.processMatch(fromUserNo, toUserNo);
                            } else {
                                log.info("매칭 실패! {} <-> {}", fromUserNo, toUserNo);
                            }
                            return null;
                        }).thenRun(ack::acknowledge)
                        .exceptionally(ex -> {
                            log.error("[스와이프 처리 중 오류 발생] {}", ex.getMessage(), ex);
                            return null;
                        });
            }

        } catch (Exception e) {
            log.error("[스와이프 저장 오류] fromUserNo: {}, toUserNo: {}, 오류: {}",
                    swipeRequest != null ? swipeRequest.getFromUserNo() : "null",
                    swipeRequest != null ? swipeRequest.getToUserNo() : "null",
                    e.getMessage(), e);
            throw new BusinessException(ExceptionCode.SWIPE_SAVE_FAILED);
        }
    }

}