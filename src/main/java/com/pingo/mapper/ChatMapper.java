package com.pingo.mapper;

import com.pingo.dto.chat.ChatRoomDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {
    List<ChatRoomDTO> selectChatRoom(String userNo);


}
