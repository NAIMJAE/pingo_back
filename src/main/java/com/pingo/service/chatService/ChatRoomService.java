package com.pingo.service.chatService;

import com.pingo.dto.chat.ChatRoomDTO;
import com.pingo.mapper.ChatMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatMapper chatMapper;

    public List<ChatRoomDTO> selectChatRoom(String userNo) {
        log.info("이 user의 No는? : " + userNo);
        List<ChatRoomDTO> chatRoomDTOS = chatMapper.selectChatRoom(userNo);
        log.info("이거머나오노! : " + chatRoomDTOS.toString());
        for(ChatRoomDTO chatRoomDTO : chatRoomDTOS) {
            chatRoomDTO.setLastMessage("우리집고양이는귀여워오예");
        }
        return chatRoomDTOS;
    }
}
