package com.pingo.controller;

import com.pingo.dto.ResponseDTO;
import com.pingo.service.signService.SignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class SignController {

    final private SignService signService;

/*    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> userInfo) {
        log.info("Login attempt - userId: {}", userInfo.get("userId"));

        // 로그인 서비스 호출 후 응답 받아오기
        ResponseEntity<?> response = signService.signInService(userInfo.get("userId"), userInfo.get("userPw"));

        // 로그인 서비스 응답이 성공적이면 ResponseDTO로 감싸서 반환
        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(ResponseDTO.of("1", "로그인 성공", response.getBody()));
        } else {
            return ResponseEntity.badRequest().body(ResponseDTO.of("2", "로그인 실패", false));
        }
    }*/

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
