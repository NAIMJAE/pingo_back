package com.pingo.dto.chat;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Setter
@Getter
public class ChatUserDTO {

    private String userId;
    private String sessionId;
}
