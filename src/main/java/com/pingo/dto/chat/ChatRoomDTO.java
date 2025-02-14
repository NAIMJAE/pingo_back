package com.pingo.dto.chat;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Setter
@Getter
public class ChatRoomDTO {

    private String userNo;
    private String roomId;
    private String imageUrl;
    private String lastMessage;
    private String userName;

}
