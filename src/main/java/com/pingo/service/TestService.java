package com.pingo.service;

import com.pingo.dto.ResponseDTO;
import com.pingo.entity.User;
import com.pingo.exception.BusinessException;
import com.pingo.exception.ExceptionCode;
import com.pingo.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TestService {

    private final TestMapper testMapper;

    public ResponseEntity<?> selectTest() {
        List<User> userList = testMapper.selectUser();
        log.info("userList : " + userList);

        if (userList.isEmpty()) {
            throw new BusinessException(ExceptionCode.INVALID_TEST);
        }

        return ResponseEntity.ok().body(ResponseDTO.of("1","성공", userList));
    }
}
