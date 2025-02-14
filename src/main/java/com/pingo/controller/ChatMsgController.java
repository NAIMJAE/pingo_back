package com.pingo.controller;


import com.pingo.dto.ResponseDTO;
import com.pingo.dto.chat.ChatMsgDTO;
import com.pingo.service.chatService.ChatMsgService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatMsgController {

    private final ChatMsgService chatMsgService;

    //해당 채팅방의 메세지 전체 조회
    @GetMapping("/select/message")
    public ResponseEntity<?> selectMessage(@RequestParam String roomId){
        log.info("룸아이디는?:"+roomId);
        List<ChatMsgDTO> chatMsgDTOS = chatMsgService.selectMessage(roomId);
        log.info("해당갑승ㄴ? : " + chatMsgDTOS);
        return ResponseEntity.ok().body(ResponseDTO.of("1", "성공", chatMsgDTOS));

    }
}
