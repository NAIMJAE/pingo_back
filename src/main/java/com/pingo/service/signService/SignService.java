package com.pingo.service.signService;

import com.pingo.dto.ResponseDTO;
import com.pingo.entity.keywords.Keyword;
import com.pingo.entity.users.SignIn;
import com.pingo.entity.users.Users;
import com.pingo.mapper.KeywordMapper;
import com.pingo.mapper.SignMapper;
import com.pingo.security.MyUserDetails;
import com.pingo.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class SignService {

    private final SignMapper signMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public ResponseEntity<?> signInProcess(String userId, String userPw) {
        log.info("userId : {}", userId);
        log.info("userPw : {}", userPw);

        try {
            log.info("signInProcess.........11");

            // 인증용 객체 생성
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, userPw);
            log.info("signInProcess.........22");
            // DB 조회
            // AuthenticationManager -> AuthenticationProvider(s) -> UserDetailsService -> DB 조회까지 이 한줄로 해결
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            log.info("signInProcess.........33");
            // 인증된 사용자 정보 가져오기
            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
            log.info("signInProcess.........44");
            Users users = userDetails.getUsers();
            log.info("signInProcess.........55");

            // 토큰 발급
            String accessToken = jwtProvider.createToken(users, 1);
            String refreshToken = jwtProvider.createToken(users, 7);
            log.info("signInProcess.........66");

            Map<String, Object> userMap = new HashMap<>();

            userMap.put("userNo", users.getUserNo());
            userMap.put("userRole", users.getUserRole());
            userMap.put("accessToken", accessToken);
            userMap.put("refreshToken", refreshToken);
            log.info("signInProcess.........77");

            return ResponseEntity.ok().body(userMap);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
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

    // 회원가입시 아이디 중복 검사
    public ResponseEntity<?> validateId(String inputId) {
        int result = signMapper.selectUserIdForValidateId(inputId);
        if(result > 0) {
            return ResponseEntity.ok().body(ResponseDTO.of("2", "실패", false));
        }else {
            return ResponseEntity.ok().body(ResponseDTO.of("1", "성공", true));
        }
    }

    // 회원 가입시 선택할 키워드 조회
    public ResponseEntity<?> select3ndKeyword() {
        List<Keyword> keywordList = signMapper.select3ndKeyword();
        if (keywordList.isEmpty()) {
            return ResponseEntity.ok().body(ResponseDTO.of("2", "실패", false));
        } else {
            return ResponseEntity.ok().body(ResponseDTO.of("1", "성공", keywordList));
        }
    }
    
}
