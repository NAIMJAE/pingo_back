package com.pingo.entity.swipeState;

import com.pingo.entity.User;
import com.pingo.entity.swipe.Swipe;
import com.pingo.entity.users.UserImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserSwipeDetails {
    private User user;
    private UserImage userImage;
    private Swipe swipe;
}
