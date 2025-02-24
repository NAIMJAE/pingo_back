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

    // 모든메세지, 마지막 메세지 저장
    public void saveMessage(List<ChatMsgDTO> messageList) {
        // 마지막 인덱스 조회
        String lastMessage = messageList.get(messageList.size() - 1).getMsgContent();
        this.message.addAll(messageList);
        this.lastMessage = lastMessage;

    }

}
