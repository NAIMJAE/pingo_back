package com.pingo.dto.chat;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Setter
@Getter

// Map<String,ChatRoom>을 사용하기 위한 DTO
public class ChatRoomDTO {

    private List<ChatUserDTO> chatUser;
    private List<ChatMsgDTO> message;
    private String lastMessage;

    public void insertChatUser(ChatUserDTO chatUser) {
        this.chatUser.add(chatUser);
    }

    public void saveMessage(List<ChatMsgDTO> messageList) {
        // List<ChatMsgDTO> message 에 저장

        // String lastMessage에 저장
    }

}
