package com.pingo.controller;

import com.pingo.dto.chat.MessageResponseDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatWebSocketController {

    // 서버에서 클라이언트로 메세지를 전송하기 위해서
    private final SimpMessagingTemplate messagingTemplate;

// DestinationVariable 메시징의 경우 파라미터값을 받아오기 위해 사용
// STOMP에서는 requestBody X -> Payload 로 사용해야 함
// 파라미터의값을 받아온 것을 SendTo("/sub/{chatNo})로 사용할 수 없음 // SendTo는 정적인 값만 지원하기 때문에 {chatNo}와 같은 동적인 값을 지원하지 않음
// 그래서 messagingTemplate를 쓰는것이 낫다.

    @MessageMapping("/{chatNo}") // pub 클라이언트 -> 서버로 메시지 전송 / WebsocketConfig prefixes에서 pub 적용한 것 삭제
    public void chatMessage(@DestinationVariable String chatNo, @Payload MessageResponseDTO messageResponseDTO) {
        log.info("Chat Message: " + messageResponseDTO);
        messagingTemplate.convertAndSend("/sub/" + chatNo, messageResponseDTO);
    }
}

// 1. 둘 다 몽고 : 속도 때문에
// 2. 오라클 + 몽고 : 데이터 유실 때문에
// 오라클 : 채팅리스트 , 몽고 : 채팅메세지
// 둘다 몽고 : 채팅리스트 채팅메세지

