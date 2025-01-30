package com.pingo.entity.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String userNo;
    private LocalDateTime userBirth;
    private int userHeight;
    private String userAddress;
    private String user1stJob;
    private String user2ndJob;
    private String userReligion;
    private String userDrinking;
    private String userSmoking;
    private String userBloodType;
}
