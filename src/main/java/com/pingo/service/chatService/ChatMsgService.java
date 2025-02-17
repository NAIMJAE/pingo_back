package com.pingo.service.chatService;

import com.pingo.document.ChatMsgDocument;
import com.pingo.dto.chat.ChatMsgDTO;
import com.pingo.repository.ChatMsgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatMsgService {

    private final ChatMsgRepository chatMsgRepository;

    public List<ChatMsgDTO> selectMessage(String roomId){
        List<ChatMsgDTO> chatMsgDTO = chatMsgRepository.findByRoomId(roomId);
        log.info("너의 값은? : " + chatMsgDTO);

        return chatMsgDTO;

    }

    public String selectLastMessage(String roomId){
        List<String> lastMessages = chatMsgRepository.findByMsgContentByRoomId(roomId);
        if (lastMessages.isEmpty()) {
            return "0"; //
        }

        return lastMessages.get(0); //
    }


}
