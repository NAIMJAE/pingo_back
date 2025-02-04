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
import org.springframework.stereotype.Controller;


@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatWebSocketController {
// DestinationVariable 메시징의 경우 파라미터값을 받아오기 위해 사용
// STOMP에서는 requestBody X -> Payload 로 사용해야 함

    @MessageMapping("/{chatNo}") // pub 클라이언트 -> 서버로 메시지 전송 / WebsocketConfig prefixes에서 pub 적용한 것 삭제
    @SendTo("/sub/1")
    public MessageResponseDTO chatMessage(@DestinationVariable String chatNo, @Payload MessageResponseDTO messageResponseDTO) {
        log.info("Chat Message: " + messageResponseDTO);
        return messageResponseDTO;
    }
}
