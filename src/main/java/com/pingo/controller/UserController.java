package com.pingo.controller;

import com.pingo.entity.users.UserMypageInfo;
import com.pingo.service.ImageService;
import com.pingo.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    // 사용자 정보 조회
    @GetMapping("/user")
    public ResponseEntity<?> getMyPageInfo(@RequestParam("userNo") String userNo) {

        return userService.getUserInfo(userNo);
    }

    // http 통신 -> Header에는 Content-Type을 작성한다
    // 일반적으로 텍스트 데이터를 주고 받을 때는 content-type : application/json
    // 이미지를 주고 받기 위해서는 content-type : multipart/form-data
    // 유저 이미지 추가
    @PostMapping("/user/image")
    public ResponseEntity<?> addUserImage(@RequestPart("userNo") String userNo, @RequestPart("userImageForAdd") MultipartFile userImageForAdd) {

        return userService.addUserImage(userNo, userImageForAdd);
    }

    // 유저의 서브이미지를 메인이미지로 설정
    @PutMapping("/user/image")
    public ResponseEntity<?> setMainImage(@RequestBody Map<String, String> reqData) {

        String currentMainImageNo = (String) reqData.get("currentMainImageNo");
        String newMainImageNo = (String) reqData.get("newMainImageNo");

        return userService.setMainImage(currentMainImageNo, newMainImageNo);
    }

    // 유저 이미지 삭제
    @DeleteMapping("/user/image")
    public ResponseEntity<?> deleteUserImage(@RequestBody Map<String, String> reqData) {

        String ImageNoForDelete = (String) reqData.get("ImageNoForDelete");

        return userService.deleteUserImage(ImageNoForDelete);
    }

    // 유저 정보 수정
    @PostMapping("/user/info")
    public ResponseEntity<?> updateUserInfo(@RequestBody UserMypageInfo userMypageInfo) {

        log.info("userMypageInfo : " + userMypageInfo);

        return userService.updateUserInfo(userMypageInfo);
    }

}
