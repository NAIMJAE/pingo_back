package com.pingo.service;

import com.pingo.entity.match.MatchUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendMatchNotification(Map<String, MatchUser> matchUsers, String toUserNo, String fromUserNo) {
        String destination = "/topic/match/notification/";

        // 담아서 보낼 정보 DTO
        messagingTemplate.convertAndSend(destination + toUserNo, matchUsers);
        messagingTemplate.convertAndSend(destination + fromUserNo, matchUsers);

    }
}
