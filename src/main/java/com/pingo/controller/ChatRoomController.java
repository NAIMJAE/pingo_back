package com.pingo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pingo.service.chatService.ChatMsgService;
import com.pingo.service.chatService.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    // 채팅방 목록 조회
    @GetMapping("/select/chatRoom")
    public ResponseEntity<?> selectRoomId(@RequestParam String userNo) {
        log.info("userNo 가져왔나 : " + userNo);
        // 사용자의 채팅방 목록 모두 가져오기
        return chatRoomService.selectChatRoom(userNo);

    }
//
//    // 채팅방 생성
//    @PostMapping("/permit/insert/chatRoom")
//    public ResponseEntity<?> insertChatRoom(@RequestBody List<String> userNoList) {
//        return chatRoomService.insertChatRoom(userNoList);
//    }
}
