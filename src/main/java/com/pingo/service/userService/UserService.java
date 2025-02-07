package com.pingo.service.userService;

import com.pingo.dto.ResponseDTO;
import com.pingo.entity.users.UserImage;
import com.pingo.entity.users.UserMypageInfo;
import com.pingo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    final private UserMapper userMapper;

    public ResponseEntity<?> getUserInfo(String userNo) {

        log.info("userNo : " + userNo);

        // 유저 마이페이지 상세 정보 조회
        UserMypageInfo userMypageInfo = userMapper.getUserMypageInfo(userNo);
        log.info("userMypageInfo : " + userMypageInfo);

        // 유저 이미지 조회
        List<UserImage> userImages = userMapper.getUserImages(userNo);
        userMypageInfo.inputUserImage(userImages);

        log.info("userMypageInfo : " + userMypageInfo);

        return ResponseEntity.ok().body(ResponseDTO.of("1","성공", userMypageInfo));
    }
}
