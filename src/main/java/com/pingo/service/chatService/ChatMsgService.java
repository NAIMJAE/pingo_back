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
    private final ObjectMapper objectMapper;

    public List<ChatMsgDTO> selectMessage(String roomId){
        List<ChatMsgDTO> chatMsgDTO = chatMsgRepository.findByRoomId(roomId);
        log.info("너의 값은? : " + chatMsgDTO);

        return chatMsgDTO;

    }

    public String selectLastMessage(String roomId) throws JsonProcessingException {
        List<String> lastMessages = chatMsgRepository.findByMsgContentByRoomId(roomId);
        if (lastMessages.isEmpty()) {
            return "0"; //
        }
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(lastMessages.get(0));
            return jsonNode.get("msgContent").asText();
        }catch (Exception e){
            return "message 파싱 실패";
        }

    }

    public ChatMsgDTO insertMessage(ChatMsgDTO chatMsgDTO){
        ChatMsgDocument chatMsgDsgDocument = ChatMsgDocument.builder()
                .roomId(chatMsgDTO.getRoomId())
                .msgContent(chatMsgDTO.getMsgContent())
                .msgTime(chatMsgDTO.getMsgTime())
                .isRead(chatMsgDTO.isRead())
                .userNo(chatMsgDTO.getUserNo())
                .msgType(chatMsgDTO.getMsgType())
                .build();
        ChatMsgDocument savedDocument = chatMsgRepository.save(chatMsgDsgDocument);

        chatMsgDTO.setMsgId(savedDocument.getMsgId());

        return chatMsgDTO;
    }

}
