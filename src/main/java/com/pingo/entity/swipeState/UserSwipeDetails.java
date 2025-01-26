package com.pingo.entity.swipeState;

import com.pingo.entity.swipe.Swipe;
import com.pingo.entity.users.UserImage;
import com.pingo.entity.users.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserSwipeDetails {
    // Users, UserImage, Swipe 조인
    private String userNo;  // 상대방 정보
    private String userName;
    private String userNick;
    private String userGender;

    private String imageUrl;
    private String swipeNo;
}
