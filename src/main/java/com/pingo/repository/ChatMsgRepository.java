package com.pingo.repository;

import com.pingo.document.ChatMsgDocument;
import com.pingo.dto.chat.ChatMsgDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatMsgRepository extends MongoRepository<ChatMsgDocument, String> {

    // 모든 메세지
    List<ChatMsgDTO> findByRoomId(String roomId);

    // 마지막으로 보낸 메세지
    @Query(value = "{'roomId':  ?0}", fields = "{'msgContent' : 1, '_id' :  0}" , sort = "{'msgTime': -1}")
    List<String> findByMsgContentByRoomId(String roomId);
}
