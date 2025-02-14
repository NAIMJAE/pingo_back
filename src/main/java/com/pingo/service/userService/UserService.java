package com.pingo.service.userService;

import com.pingo.dto.ResponseDTO;
import com.pingo.entity.users.UserImage;
import com.pingo.entity.users.UserMypageInfo;
import com.pingo.exception.BusinessException;
import com.pingo.exception.ExceptionCode;
import com.pingo.mapper.UserMapper;
import com.pingo.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    final private ImageService imageService;
    final private UserMapper userMapper;

    // 마이페이지를 위한 회원 정보 조회
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

    // 유저 이미지 추가
    public ResponseEntity<?> addUserImage(String userNo, MultipartFile userImageForAdd) {

        // 유저 번호에 해당하는 이미지 호출
        List<UserImage> userImages = userMapper.getUserImages(userNo);

        // 리스트에 담긴 사진 수
        int userImagecount = userImages.size();
        log.info("userImagecount : " + userImagecount);

        if (userImagecount < 6 ) {
            // 이미지 서버에 저장하기
            // 새로운 유저이미지 객체 생성
            UserImage userImage = new UserImage();

            // 이미지 번호 랜덤 생성
            userImage.makeImageNo();

            // 이미지 번호 호출
            String imageNo = userImage.getImageNo();

            // 이미지 경로 호출 후 업로드 로직
            String userImagePath = "users" + File.separator + userNo;
            String imageUrl = imageService.imageUpload(userImageForAdd, userImagePath, imageNo);

            // 이미지 디비에 저장하기
            userMapper.addUserImage(imageNo, imageUrl, "F", userNo);

            return ResponseEntity.ok().body(ResponseDTO.of("1","성공", true));
        } else {
            throw new BusinessException(ExceptionCode.FILE_UPLOAD_FAIL);
        }
    }

    // 유저의 서브이미지를 메인이미지로 설정
    public ResponseEntity<?> setMainImage(String currentMainImageNo, String newMainImageNo) {

        // 대표이미지를 서브이미지로 설정
        userMapper.setMainImageAsSubImage(currentMainImageNo);

        // 선택한 서브이미지를 대표이미지로 설정
        userMapper.setSubImageAsMainImage(newMainImageNo);

        return ResponseEntity.ok().body(ResponseDTO.of("1","성공", true));
    }

    // 유저 이미지 삭제
    public ResponseEntity<?> deleteUserImage(String imageNo) {

        return null;
    }
}
