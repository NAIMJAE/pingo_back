package com.pingo.controller;

import com.pingo.entity.users.Users;
import com.pingo.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/mypageinfo")
    public ResponseEntity<?> getMyPageInfo(@RequestParam("userNo") String userNo) {

        return userService.getUserInfo(userNo);
    }

}
