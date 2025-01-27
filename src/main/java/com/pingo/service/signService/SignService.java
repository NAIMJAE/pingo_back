package com.pingo.service.signService;

import com.pingo.entity.users.SignIn;
import com.pingo.mapper.SignMapper;
import com.pingo.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SignService {

    private final SignMapper signMapper;
    private final JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<?> signInService(String userId, String userPw) {
        log.info("userId : " + userId);
        log.info("userPw : " + userPw);

        // 1. DB에서 사용자 정보 조회
        SignIn user = signMapper.findUserById(userId);
        if (user == null) {
            log.error("아이디가 존재하지 않습니다.");
            return ResponseEntity.badRequest().body("Invalid userId");
        }

        // 2. 비밀번호 검증
        if (!validationPw(user.getUserPw(), userPw)) {
            log.error("비밀번호가 일치하지 않습니다.");
            return ResponseEntity.badRequest().body("Invalid password");
        }

        // 3. JWT 토큰 생성
        String token = jwtTokenProvider.createToken(user.getUserId(), user.getUserNo());

        return ResponseEntity.ok().body(token);
    }

    private boolean validationPw(String userPw, String inputPw) {
        // 1. 입력한 비밀번호 암호화
        String encryptedPw = encryptPassword(inputPw);

        // 2. 암호화된 비밀번호와 DB의 비밀번호 비교
        return encryptedPw.equals(userPw);
    }

    private String encryptPassword(String password) {
        // 비밀번호 암호화 로직 (예: BCrypt, SHA256 등 사용 가능)
        return password; // 테스트용으로 암호화 없이 반환. 실제 구현 시 암호화 적용 필요.
    }
}
