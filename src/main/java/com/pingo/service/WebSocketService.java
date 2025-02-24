package com.pingo.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendMatchNotification(String fromUserNo, String toUserNo, String opponentProfile) {
        String destination = "/topic/match/notification/";
        // 담아서 보낼 정보 DTO
        messagingTemplate.convertAndSend("destination" + fromUserNo, "담아서 보낼 정보");
        messagingTemplate.convertAndSend("destination" + toUserNo, "담아서 보낼 정보");


    }
}
