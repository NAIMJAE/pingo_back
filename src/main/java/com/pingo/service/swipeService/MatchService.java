package com.pingo.service.swipeService;

import com.pingo.entity.match.MatchMapperEntity;
import com.pingo.entity.match.Matching;
import com.pingo.exception.BusinessException;
import com.pingo.exception.ExceptionCode;
import com.pingo.mapper.MatchMapper;
import com.pingo.mapper.MatchingMapper;
import com.pingo.service.chatService.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Slf4j
@Service
public class MatchService {
    private final MatchingMapper matchingMapper;
    private final MatchMapper matchMapper;
    private final ChatRoomService chatRoomService;

    // 매칭이 성공되면 실행
    // 1. 매칭테이블 데이터 삽입
    // 2. 매칭매퍼 테이블 데이터 삽입
    // 3. 매칭된 상대방 데이터 조회 (이름, 사진, 나이) + 채팅방 생성 (비동기 병렬 처리)
    // 4. 웹소켓 연결
    @Transactional
    public void processMatch(String fromUserNo, String toUserNo) {
        try {
            // 1) 매칭 객체 생성 및 저장
            Matching matching = new Matching(fromUserNo, toUserNo);

            matchingMapper.insertMatching(matching);
            log.info("매칭 저장 완료: {} <-> {}", fromUserNo, toUserNo);

            // 2) 매칭 매퍼 테이블에도 삽입 (양방향 저장)
            MatchMapperEntity matchA = new MatchMapperEntity(fromUserNo, toUserNo, matching.getMatchNo());
            MatchMapperEntity matchB = new MatchMapperEntity(toUserNo, fromUserNo, matching.getMatchNo());

            matchMapper.insertMatchMapper(matchA);
            matchMapper.insertMatchMapper(matchB);

            log.info("매칭 매퍼 저장 완료: {} <-> {}", fromUserNo, toUserNo);

////             3) 상대방 정보 조회 + 채팅방 생성 (비동기 병렬 처리)
//            CompletableFuture<UserProfile> fetchOpponentInfoFuture = CompletableFuture.supplyAsync(() -> {
//                return matchingMapper.getUserProfile(toUserNo);
//            });

            List<String> userNoList = new ArrayList<>();
            userNoList.add(fromUserNo);
            userNoList.add(toUserNo);
            CompletableFuture<Void> createChatRoomFuture = CompletableFuture.runAsync(() -> {
                chatRoomService.createChatRoomAndUser(userNoList);
            });
//
//            // 4) 두 작업이 완료되면 웹소켓을 통해 알림 전송
//            fetchOpponentInfoFuture.thenCombine(createChatRoomFuture, (opponentProfile, chatRoomId) -> {
//                webSocketService.sendMatchNotification(fromUserNo, toUserNo, opponentProfile, chatRoomId);
//                log.info(" 웹소켓 알림 전송 완료: {} <-> {}", fromUserNo, toUserNo);
//                return null;
//            }).exceptionally(ex -> {
//                log.error("[웹소켓 전송 중 오류 발생] {}", ex.getMessage(), ex);
//                return null;
//            });

        } catch (Exception e) {
            log.error("[매칭 처리 중 오류 발생] fromUserNo: {}, toUserNo: {}, 오류: {}",
                    fromUserNo, toUserNo, e.getMessage(), e);
            throw new BusinessException(ExceptionCode.MATCHING_FAILED);
        }
    }
}
