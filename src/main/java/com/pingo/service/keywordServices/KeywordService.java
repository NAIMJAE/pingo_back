package com.pingo.service.keywordServices;

import com.pingo.dto.ResponseDTO;
import com.pingo.entity.keywords.Keyword;
import com.pingo.mapper.KeywordMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class KeywordService {

    final private KeywordMapper keywordMapper;

    public ResponseEntity<?> selectKeywordListForCategory() {
        List<Keyword> keywordList = keywordMapper.selectKeywordList();
        return ResponseEntity.ok().body(ResponseDTO.of("1", "성공", keywordList));
    }

}
