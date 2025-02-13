package com.pingo.repository;

import com.pingo.document.ChatMsgDocument;
import com.pingo.dto.chat.ChatMsgDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMsgRepository extends MongoRepository<ChatMsgDocument, String> {

    List<ChatMsgDTO> findByRoomId(String roomId);
}
