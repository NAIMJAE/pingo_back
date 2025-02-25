package com.pingo.service.chatService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    // 전체 메세지 조회
    public List<ChatMsgDTO> selectMessage(String roomId){
        List<ChatMsgDTO> chatMsgDTO = chatMsgRepository.findByRoomId(roomId);
        log.info("너의 값은? : " + chatMsgDTO);

        return chatMsgDTO;
    }

    // 메세지 삽입
    public void insertMessage(ChatMsgDTO chatMsgDTO){
        ChatMsgDocument chatMsgDsgDocument = ChatMsgDocument.builder()
                .roomId(chatMsgDTO.getRoomId())
                .msgContent(chatMsgDTO.getMsgContent())
                .msgTime(chatMsgDTO.getMsgTime())
                .isRead(chatMsgDTO.isRead())
                .userNo(chatMsgDTO.getUserNo())
                .msgType(chatMsgDTO.getMsgType())
                .build();
        ChatMsgDocument savedDocument = chatMsgRepository.save(chatMsgDsgDocument);
        log.info("챗저장 값 : " +savedDocument);

    }

}
