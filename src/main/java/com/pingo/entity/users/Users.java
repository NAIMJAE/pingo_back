package com.pingo.entity.users;

import com.pingo.exception.BusinessException;
import com.pingo.exception.ExceptionCode;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private String userNo;
    private String userId;
    private String userPw;
    private String userName;
    private String userNick;
    private String userGender;
    private String userState;
    private String userRole;
    private LocalDateTime userrDate;

    // userNo 생성 로직 (중복 호출시 예외 처리)
    public String createUserNo() {
        if(this.userNo == null) {
            String uuid = UUID.randomUUID().toString();
            return "US" + uuid.substring(0, 8);
        }else {
            throw new BusinessException(ExceptionCode.DUPLICATE_USER_NO);
        }
    }
}
