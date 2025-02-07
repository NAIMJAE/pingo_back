package com.pingo.service.userService;

import com.pingo.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    public ResponseEntity<?> aaaaa(String userNo) {

// 비즈니스로직들

        return ResponseEntity.ok().body(ResponseDTO.of("1","성공","하하"));
    }
}
