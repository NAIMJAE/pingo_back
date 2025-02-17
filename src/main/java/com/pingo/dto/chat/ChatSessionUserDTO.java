package com.pingo.dto.chat;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ChatSessionUserDTO {
    private String userId;
    private String sessionId;
}
