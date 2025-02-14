package com.pingo.controller;

import com.pingo.dto.ResponseDTO;
import com.pingo.dto.chat.ChatRoomDTO;
import com.pingo.service.chatService.ChatRoomService;
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
public class ChatRoomController {

    private final ChatRoomService chatRoomService;


    //채팅방 목록 가져오기
    @GetMapping("/select/chatRoom")
    public ResponseEntity<?> selectRoomId(@RequestParam String userNo) {
        log.info("userNo 가져왔나 : " + userNo);
        List<ChatRoomDTO> chatRoomDto = chatRoomService.selectChatRoom(userNo);
        log.info("야오야오야오야 :" + chatRoomDto);
        ResponseDTO<List<ChatRoomDTO>> response = ResponseDTO.of("1","성공",chatRoomDto);
        log.info("이거좀.. : " + response);
        return ResponseEntity.ok().body(ResponseDTO.of("1", "성공", chatRoomDto));
    }
}
