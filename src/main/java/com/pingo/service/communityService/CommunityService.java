package com.pingo.service.communityService;

import com.pingo.dto.ResponseDTO;
import com.pingo.dto.community.DatingGuideDTO;
import com.pingo.dto.community.DatingGuideSearchDTO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<String, DatingGuideSearchDTO> guideMap = new HashMap<>();
        for (DatingGuideDTO each : datingGuideList) {
            if (!guideMap.containsKey(each.getCateName())) {//
                guideMap.put(each.getCateName(), new DatingGuideSearchDTO(each.getCateName(), each.getCateNo()));
            }
            DatingGuideSearchDTO dgsDTO = guideMap.get(each.getCateName());
            dgsDTO.addDatingGuideList(each);
        }
        return ResponseEntity.ok().body(ResponseDTO.of("1","성공",guideMap));
    }

    // 개별 DatingGuide 정렬로 조회
    public ResponseEntity<?> selectDatingGuideWithSort(String cate, String sort) {
        List<DatingGuideDTO> datingGuideList = communityMapper.selectDatingGuideWithSort(cate, sort);

        return ResponseEntity.ok().body(ResponseDTO.of("1", "성공", datingGuideList));
    }

}
