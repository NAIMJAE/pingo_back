package com.pingo.controller;

import com.pingo.dto.ResponseDTO;
import com.pingo.exception.BusinessException;
import com.pingo.exception.ExceptionCode;
import com.pingo.service.signService.SignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SignController {

    final private SignService signService;

    @PostMapping("/permit/signin")
    public ResponseEntity<?> login(@RequestBody Map<String, String> userInfo) {
        log.info("Login attempt - userId: {}", userInfo.get("userId"));
        log.info("데이터 구조좀 볼까? " + userInfo.toString());

        // 위치 정보 추가
        double latitude = Double.parseDouble(userInfo.get("latitude"));
        double longitude = Double.parseDouble(userInfo.get("longitude"));

        // 로그인 서비스 호출 후 응답 받아오기
        return signService.signInProcess(userInfo.get("userId"), userInfo.get("userPw"),latitude,longitude);
    }

    @GetMapping("/permit/test")
    public ResponseEntity<?> autoLogin() {
        return signService.test();
    }

    // 회원가입시 아이디 중복 검증
    @GetMapping("/permit/validateId")
    public ResponseEntity<?> validateId(@RequestParam String inputId) {
        log.info("inputId : " + inputId);
        return signService.validateId(inputId);
    }

    // 회원가입시 3차 키워드 조회
    @GetMapping("/permit/3ndKeyword")
    public ResponseEntity<?> select3ndKeyword() {
        return signService.select3ndKeyword();
    }

    // 회원가입
    @PostMapping("/permit/signup")
    public ResponseEntity<?> signup(@RequestPart("userSignUp") String userSignUp, @RequestPart("image") MultipartFile profileImage) {
        log.info("userSignUp : " + userSignUp);
        log.info("profileImage : " + profileImage.getOriginalFilename());

        return signService.signUpProcess(userSignUp, profileImage);
    }
}
