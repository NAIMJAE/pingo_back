package com.pingo.entity.users;

import com.pingo.exception.BusinessException;
import com.pingo.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserMypageInfo {

    private Users users;
    private UserInfo userInfo;
    private List<UserImage> userImageList;

    public void inputUserImage(List<UserImage> userImageList) {
        this.userImageList = userImageList;
    }

}
