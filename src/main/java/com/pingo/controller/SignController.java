package com.pingo.controller;

import com.pingo.dto.ResponseDTO;
import com.pingo.dto.UserSignUp;
import com.pingo.service.signService.SignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Objects;

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

    // 회원가입시 아이디 중복 검증
    @GetMapping("/validateId")
    public ResponseEntity<?> validateId(@RequestParam String inputId) {
        log.info("inputId : " + inputId);
        return signService.validateId(inputId);
    }

    // 회원가입시 3차 키워드 조회
    @GetMapping("/3ndKeyword")
    public ResponseEntity<?> select3ndKeyword() {
        return signService.select3ndKeyword();
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestPart("userSignUp") String userSignUp, @RequestPart("image") MultipartFile profileImage) {
        log.info("userSignUp : " + userSignUp);
        log.info("profileImage : " + profileImage.getOriginalFilename());

        try {
            Thread.sleep(3000);
        }catch (Exception e) {
            log.info(e.getMessage());
        }

        return ResponseEntity.ok().body(ResponseDTO.of("1", "성공", true));
    }
}
