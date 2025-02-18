package com.pingo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pingo.dto.ResponseDTO;
import com.pingo.dto.chat.ChatRoomDTO;
import com.pingo.dto.chat.ChatUserDTO;
import com.pingo.entity.chat.ChatRoom;
import com.pingo.service.chatService.ChatMsgService;
import com.pingo.service.chatService.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatMsgService chatMsgService;


    //채팅방 목록 가져오기 Map<String, ChatRoom> 형태로 반환 ,
    //class ChatRoom {
    //  final List<ChatUser> chatUser; //
    //  final List<Message>? message;
    //  final String lastMessage;
    // 이때 message 부분은 null로 해서 보낸다.
    //class ChatUser {
    //  final String? userNo;
    //  final String roomId;
    //  final String? imageUrl;
    //  final String? userName;
    @GetMapping("/select/chatRoom")
    public ResponseEntity<?> selectRoomId(@RequestParam String userNo) throws JsonProcessingException {
        log.info("userNo 가져왔나 : " + userNo);
        // 사용자의 채팅방 목록 모두 가져오기
        List<ChatUserDTO> chatUserDTOS = chatRoomService.selectChatRoom(userNo);
        log.info("chatUserDTOS : " + chatUserDTOS);

        // 결과를 저장할 맵
        Map<String, ChatRoomDTO> chatRoomMap = new HashMap<>();

        // 채팅방별로 데이터를 구성 : String이 roomId가 되어야한다.


        for(ChatUserDTO chatUserDTO : chatUserDTOS) {
            // 채팅방 존재 여부 확인
            String roomId = chatUserDTO.getRoomId();
            // ChatRoomDTO 초기화 시키기
            ChatRoomDTO chatRoomDTO = new ChatRoomDTO(new ArrayList<>(), null, null);            // chatUser추가하기
            chatRoomDTO.getChatUser().add(chatUserDTO);
            // 마지막 메세지 추가하기
            chatRoomDTO.setLastMessage(chatMsgService.selectLastMessage(roomId));

            // 맵에 저장하기
            chatRoomMap.put(roomId, chatRoomDTO);

        }
        log.info("맵 ChatUserDTO 값은? :" + chatRoomMap);
        return ResponseEntity.ok().body(ResponseDTO.of("1", "성공", chatRoomMap));
    }
}
