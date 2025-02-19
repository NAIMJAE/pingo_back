package com.pingo.dto.chat;

import com.pingo.document.ChatMsgDocument;
import lombok.*;
import org.apache.logging.log4j.message.Message;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Setter
@Getter
public class ChatRoomDTO {

    private List<ChatUserDTO> chatUser;
    private List<ChatMsgDTO> message;
    private String lastMessage;

    public void insertChatUser(ChatUserDTO chatUser) {
        this.chatUser.add(chatUser);
    }

}
