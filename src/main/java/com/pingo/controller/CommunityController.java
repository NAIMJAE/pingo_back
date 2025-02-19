package com.pingo.controller;

import com.pingo.entity.community.DatingGuide;
import com.pingo.entity.community.PlaceReview;
import com.pingo.service.communityService.CommunityService;
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
public class CommunityController {

    final private CommunityService communityService;

    // 정렬로 PlaceReview 조회
    @GetMapping("/community/place")
    public ResponseEntity<?> searchPlaceReview(@RequestParam("cateSort") String cateSort,
                                               @RequestParam("searchSort") String searchSort,
                                               @RequestParam("keyword") String keyword) {

        return communityService.searchPlaceReview(cateSort, searchSort, keyword);
    }

    // PlaceReview 삽입
    @PostMapping("/community/place")
    public ResponseEntity<?> insertPlaceReview(@RequestPart("placeReview") PlaceReview placeReview,
                                               @RequestPart("placeImage") MultipartFile placeImage) {

        return communityService.insertPlaceReview(placeReview, placeImage);
    }

    // DatingGuide 최초 조회
    @GetMapping("/community/guide/init")
    public ResponseEntity<?> selectDatingGuideForInit() {
        return communityService.selectDatingGuideForInit();
    }

    // 개별 DatingGuide 정렬로 조회
    @GetMapping("/community/guide/sort")
    public ResponseEntity<?> selectDatingGuideWithSort(@RequestParam("cate") String cate,
                                                       @RequestParam("sort") String sort) {

        return communityService.selectDatingGuideWithSort(cate, sort);
    }

    // DatingGuide 작성
    @PostMapping("/community/guide")
    public ResponseEntity<?> insertDatingGuide(@RequestPart("datingGuide") DatingGuide datingGuide,
                                               @RequestPart("guideImage") MultipartFile guideImage) {

        return communityService.insertDatingGuide(datingGuide, guideImage);
    }

    // DatingGuide 좋아요
    @PostMapping("/community/guide/heart")
    public ResponseEntity<?> checkDatingGuideHeart(@RequestBody Map<String, String> reqData) {

        String userNo = reqData.get("userNo");
        String dgNo = reqData.get("dgNo");

        return communityService.checkDatingGuideHeart(userNo, dgNo);
    }
}