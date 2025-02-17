package com.pingo.dto.chat;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Setter
@Getter
public class ChatUserDTO {

    private String userNo;
    private String roomId;
    private String imageUrl;
    private String userName;
}
