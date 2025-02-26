package com.pingo.entity.membership;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserMembership {
    private String umNo;
    private String userNo;
    private String msNo;
    private LocalDateTime payDate;
    private LocalDateTime expDate;
    private String state;
}
