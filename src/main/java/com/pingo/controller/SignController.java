package com.pingo.controller;

import com.pingo.service.signService.SignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class SignController {

    final private SignService signService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> userInfo) {
        log.info("userInfo userId : " + userInfo.get("userId"));
        log.info("userInfo userPw : " + userInfo.get("userPw"));

        // JWT 토큰 생성 로직 추가
        return signService.signInService(userInfo.get("userId"), userInfo.get("userPw"));
    }
}
