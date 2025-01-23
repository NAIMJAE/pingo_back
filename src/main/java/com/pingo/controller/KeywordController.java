package com.pingo.controller;

import com.pingo.service.keywordServices.KeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class KeywordController {

    final private KeywordService keywordService;

    // 2차 키워드 카테고리까지 조회 for Keyword_Page
    @GetMapping("/keyword")
    public ResponseEntity<?> selectKeyword() {
        return keywordService.selectKeywordListFor2ndCategory();
    }
}
