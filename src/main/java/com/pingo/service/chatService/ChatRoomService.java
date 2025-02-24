package com.pingo.service.chatService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pingo.dto.ResponseDTO;
import com.pingo.dto.chat.*;
import com.pingo.entity.chat.ChatRoom;
import com.pingo.entity.chat.ChatUser;
import com.pingo.mapper.ChatMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatMapper chatMapper;
    private final ChatMsgService chatMsgService;

    // chatRoom 조회 (채팅방 목록, 메세지, 마지막메세지)
    public ResponseEntity<?> selectChatRoom(String userNo) throws JsonProcessingException {
        log.info("이 user의 No는? : " + userNo);
        List<ChatUserDTO> chatUserDTOS = chatMapper.selectChatUser(userNo);
        // 결과를 저장할 맵
        Map<String, ChatRoomDTO> chatRoomMap = new HashMap<>();

        // 채팅방별로 데이터를 구성 : String이 roomId가 되어야한다.

        // 하나의 List를 추출
        for(ChatUserDTO chatUserDTO : chatUserDTOS) {
            // 채팅방 존재 여부 확인
            String roomId = chatUserDTO.getRoomId();

            if (chatRoomMap.containsKey(roomId)) {
                // 이미 방이 존재하면
                chatRoomMap.get(roomId).insertChatUser(chatUserDTO);
            }else {
                // 방이 없으면
                // ChatRoomDTO 초기화 시키기
                ChatRoomDTO chatRoomDTO = new ChatRoomDTO(new ArrayList<>(), new ArrayList<>(), null);            // chatUser추가하기
                chatRoomDTO.insertChatUser(chatUserDTO);
                // 해당 방에 해당되는 메세지들 조회하기
                List<ChatMsgDTO> msgDTO = chatMsgService.selectMessage(roomId);
                log.info("msgDTO : " + msgDTO);
                chatRoomDTO.setMessage(msgDTO);
                // 마지막 메세지 추가하기
                chatRoomDTO.setLastMessage(chatMsgService.selectLastMessage(roomId));

                chatRoomMap.put(roomId, chatRoomDTO);
            }

        }

        log.info("맵 ChatUserDTO 값은? :" + chatRoomMap);
        return ResponseEntity.ok().body(ResponseDTO.of("1", "성공", chatRoomMap));
    }

    // 채팅방 user조회
    public Map<String,List<String>> selectChatRoomUser(String userNo) {
        List<ChatUserListDTO> chatUserListDTOS = chatMapper.selectChatUserList(userNo);
        Map<String, List<String>> roomUserMap = new HashMap<>();
        for(ChatUserListDTO chatUser : chatUserListDTOS) {
            roomUserMap.computeIfAbsent(chatUser.getRoomId(), k -> new ArrayList<>()).add(chatUser.getUserNo());
        }

        return roomUserMap;
    }


//    // 채팅방 생성
//    public ResponseEntity<?> insertChatRoom(List<String> userNoList){
//        boolean result = createChatRoomAndUser(userNoList);
//        return ResponseEntity.ok().body("채팅방 생성");
//    }

    @Transactional
    public void createChatRoomAndUser(List<String> userNoList) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.createRoomId();

        chatMapper.insertChatRoom(chatRoom);

        for(String user : userNoList) {
            ChatUser chatUser = new ChatUser();
            chatUser.insertUserAndRoom(user, chatRoom.getRoomId());
            chatMapper.insertChatUser(chatUser);
        }
    }
}
