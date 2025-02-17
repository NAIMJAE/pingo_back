package com.pingo.mapper;

import com.pingo.dto.chat.ChatRoomDTO;
import com.pingo.dto.chat.ChatUserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {
    List<ChatUserDTO> selectChatUser(String userNo);


}
