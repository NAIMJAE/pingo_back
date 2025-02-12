package com.pingo.dto.chat;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Setter
@Getter
public class ChatMsgResponseDTO {
    private String messageNo;
    private String chatNo;
    private String fromId;
    private String messageContent;
    private String messageTime;
    private String type;
    private int readCount;
    private String profileImageUrl;

}
