package com.pingo.service.userService;

import com.pingo.dto.ResponseDTO;
import com.pingo.exception.BusinessException;
import com.pingo.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageService {

    // 이미지 서버에 저장하기 [이미지 객체, 이미지 저장 경로, 파일 저장 이름]
    public String imageUpload(MultipartFile image, String imagePath, String imageName) {

        log.info("imageUpload........11");
        log.info("image : " + image.isEmpty() + " | path : " + imagePath + " | name : " + imageName);
        try {
            // 프로젝트 루트 경로 (pingo_back/)
            String projectRootPath = System.getProperty("user.dir");

            // 파일 객체를 생성 (File 클래스 사용)
            String uploadDir = projectRootPath + File.separator + "uploads" + File.separator + "images" + File.separator + imagePath + File.separator;
            File uploadFolder = new File(uploadDir);

            log.info("imageUpload........22");


            // 파일이 저장될 경로 확인 (존재하지 않으면 디렉터리 생성)
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            // 랜덤화된 이름 imageName과 getContentType()을 활용한 확장자 추출을 이용해서 업로드할 파일이름 생성
            // MIME 타입에서 확장자 추출
            String contentType = image.getContentType();
            String[] arr = contentType.split("/");
            String fileExtension = "." + arr[1];

            log.info("imageUpload........33");
            log.info("fileExtension : " + fileExtension);


            // 최종 저장될 파일 이름
            String imageFullName = imageName + fileExtension;
            File destinationFile = new File(uploadDir + imageFullName);

            // 파일로 저장 (transferTo 사용)
            image.transferTo(destinationFile);

            log.info("imageFullName : " + imageFullName);

            return uploadDir + imageFullName;
        } catch (Exception e) {
            throw new BusinessException(ExceptionCode.FILE_UPLOAD_FAIL);
        }
    }

    // 이미지 삭제




}
