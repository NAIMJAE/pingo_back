package com.pingo.entity.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserImage {
    private Integer imageNo;
    private String imageUrl;
    private String imageProfile;
    private String userNo;
}
