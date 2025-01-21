package com.pingo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userNo; // 사용자 번호 (Primary Key)
    private String userId; // 사용자 ID
    private String userPw; // 사용자 비밀번호
    private String userName; // 사용자 이름
    private String userNick; // 사용자 닉네임
    private String userGender; // 성별 (M, W)
    private LocalDate userBirth; // 생년월일
    private String userState; // 상태 (활동, 정지, 탈퇴)
    private LocalDateTime userRDate; // 가입일 (기본값 현재 시간)
}
