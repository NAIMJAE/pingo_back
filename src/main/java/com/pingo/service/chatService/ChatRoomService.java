package com.pingo.service.chatService;

import com.pingo.dto.chat.ChatRoomDTO;
import com.pingo.dto.chat.ChatUserDTO;
import com.pingo.dto.chat.ChatUserListDTO;
import com.pingo.entity.chat.ChatUser;
import com.pingo.mapper.ChatMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatMapper chatMapper;

    public List<ChatUserDTO> selectChatRoom(String userNo) {
        log.info("이 user의 No는? : " + userNo);
        List<ChatUserDTO> chatUserDTOS = chatMapper.selectChatUser(userNo);
        log.info("이거머나오노! : " + chatUserDTOS.toString());
        for(ChatUserDTO chatUserDTO : chatUserDTOS) {
//            chatUserDTO.setLastMessage("우리집고양이는귀여워오예");
        }
        return chatUserDTOS;
    }

    public Map<String,List<String>> selectChatRoomUser(String userNo) {
        List<ChatUserListDTO> chatUserListDTOS = chatMapper.selectChatUserList(userNo);
        Map<String, List<String>> roomUserMap = new HashMap<>();
        for(ChatUserListDTO chatUser : chatUserListDTOS) {
            roomUserMap.computeIfAbsent(chatUser.getRoomId(), k -> new ArrayList<>()).add(chatUser.getUserNo());
        }

        return roomUserMap;
    }
}
