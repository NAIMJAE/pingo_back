package com.pingo.service.communityService;

import com.pingo.dto.ResponseDTO;
import com.pingo.dto.community.PlaceReviewDTO;
import com.pingo.mapper.CommunityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommunityService {

    final private CommunityMapper communityMapper;

    public ResponseEntity<?> searchPlaceReview(String cateSort, String searchSort, String keyword) {

        if (keyword == null || keyword.isEmpty()) {
            log.info("★★ 정렬로 검색 ★★");
            List<PlaceReviewDTO> placeReviewList = communityMapper.selectPlaceReviewWithSort(cateSort, searchSort);

            log.info("placeReviewList : " + placeReviewList);

            return ResponseEntity.ok().body(ResponseDTO.of("1","성공",placeReviewList));
        }else {
            List<PlaceReviewDTO> placeReviewList = communityMapper.selectPlaceReviewWithKeyword(keyword);

            log.info("placeReviewList : " + placeReviewList);

            return ResponseEntity.ok().body(ResponseDTO.of("1","성공",placeReviewList));
        }

    }


}
