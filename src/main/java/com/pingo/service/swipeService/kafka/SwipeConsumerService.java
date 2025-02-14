package com.pingo.service.swipeService.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pingo.dto.swipe.SwipeRequest;
import com.pingo.entity.swipe.Swipe;
import com.pingo.exception.BusinessException;
import com.pingo.exception.ExceptionCode;
import com.pingo.mapper.SwipeMapper;
import com.pingo.util.KafkaTopics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SwipeConsumerService {

    private final SwipeMapper swipeMapper;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = KafkaTopics.SWIPE_EVENTS, groupId = "swipe-consumer-group")
    public void consumeSwipeEvent(ConsumerRecord<String, String> record, Acknowledgment ack) {
        log.info("📩 Kafka 메시지 수신: {}", record.value());

        SwipeRequest swipeRequest = null;
        try {
            swipeRequest = objectMapper.readValue(record.value(), SwipeRequest.class);

            if (swipeRequest.getFromUserNo() == null || swipeRequest.getToUserNo() == null) {
                log.error("❌ [오류] Kafka 메시지 데이터 이상: {}", record.value());
                ack.acknowledge(); // 🚀 메시지를 소비했다고 커밋 (다시 처리되지 않도록 함)
                return;
            }

            Swipe swipe = new Swipe().toInsertEntity(swipeRequest);
            swipeMapper.insertUserSwipe(swipe);

            log.info("[DB 저장 완료] fromUserNo: {}, toUserNo: {}, swipeType: {}",
                    swipeRequest.getFromUserNo(), swipeRequest.getToUserNo(), swipeRequest.getSwipeType());

            ack.acknowledge(); // ✅ 정상 처리된 경우 Offset 커밋

        } catch (Exception e) {
            log.error("[스와이프 저장 오류] fromUserNo: {}, toUserNo: {}, 오류: {}",
                    swipeRequest.getFromUserNo(), swipeRequest.getToUserNo(), e.getMessage(), e);
            throw new BusinessException(ExceptionCode.SWIPE_SAVE_FAILED);
        }
    }
}