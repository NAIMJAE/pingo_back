package com.pingo.dto.chat;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Setter
@Getter
public class ChatUserListDTO {
    private String userNo;
    private String roomId;
}
