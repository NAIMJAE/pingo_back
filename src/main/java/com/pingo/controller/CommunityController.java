package com.pingo.controller;

import com.pingo.service.communityService.CommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CommunityController {

    final private CommunityService communityService;

    @GetMapping("/community/place")
    public ResponseEntity<?> searchPlaceReview(@RequestParam("cateSort") String cateSort,
                                   @RequestParam("searchSort") String searchSort,
                                   @RequestParam("keyword") String keyword) {

        log.info("cateSort : "+ cateSort +" | searchSort : "+searchSort+" | keyword : " + keyword);

        return communityService.searchPlaceReview(cateSort, searchSort, keyword);

    }
}
