package com.pingo.service.communityService;

import com.pingo.dto.ResponseDTO;
import com.pingo.dto.community.DatingGuideDTO;
import com.pingo.dto.community.PlaceReviewDTO;
import com.pingo.entity.community.PlaceReview;
import com.pingo.mapper.CommunityMapper;
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
public class CommunityService {

    final private ImageService imageService;
    final private CommunityMapper communityMapper;

    // 정렬로 PlaceReview 조회
    public ResponseEntity<?> searchPlaceReview(String cateSort, String searchSort, String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            List<PlaceReviewDTO> placeReviewList = communityMapper.selectPlaceReviewWithSort(cateSort, searchSort);

            return ResponseEntity.ok().body(ResponseDTO.of("1","성공",placeReviewList));
        }else {
            List<PlaceReviewDTO> placeReviewList = communityMapper.selectPlaceReviewWithKeyword(keyword);

            return ResponseEntity.ok().body(ResponseDTO.of("1","성공",placeReviewList));
        }
    }

    // PlaceReview 삽입
    public ResponseEntity<?> insertPlaceReview(PlaceReview placeReview, MultipartFile placeImage) {
        // 이미지 저장
        placeReview.createPrNo();
        String thumbName = placeReview.createThumbName();

        String placeImagePath = "placeImages" + File.separator + placeReview.getPrNo();
        String imageUrl = imageService.imageUpload(placeImage, placeImagePath, thumbName);

        // 내용 저장
        placeReview.insertThumb(imageUrl);
        communityMapper.insertPlaceReview(placeReview);
        
        return ResponseEntity.ok().body(ResponseDTO.of("1", "성공", true));
    }

    // DatingGuide 최초 조회
    public ResponseEntity<?> selectDatingGuideForInit() {
        List<DatingGuideDTO> datingGuideList = communityMapper.selectDatingGuideForInit();

        log.info("datingGuideList : " + datingGuideList);
        
        // 조회 확인 했음
        // 이거 이제 Map 구조로 변경해서 프론트로 날리면 됨
        
        return null;
    }

}
