package com.pingo.service.keywordServices;

import com.pingo.dto.ResponseDTO;
import com.pingo.entity.keywords.Keyword;
import com.pingo.entity.keywords.KeywordGroup;
import com.pingo.mapper.KeywordMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class KeywordService {

    final private KeywordMapper keywordMapper;

    // 2차 키워드 카테고리까지 조회 for Keyword_Page
    public ResponseEntity<?> selectKeywordListFor2ndCategory() {
        List<Keyword> keywordList = keywordMapper.selectKeywordListFor2ndCategory();

        Map<String, KeywordGroup> keywordGroup = transformKeywordListToGroupMap(keywordList);

        return ResponseEntity.ok().body(ResponseDTO.of("1", "성공", keywordGroup));
    }

    // KeywordList 를 Map<String, KeywordGroup> 구조로 변경
    private Map<String, KeywordGroup> transformKeywordListToGroupMap(List<Keyword> keywordList) {
        Map<String, KeywordGroup> keywordGroup = new HashMap<>();
        for (Keyword item : keywordList) {
            if (item.getKwParentId() == null) {
                keywordGroup.put(item.getKwId(), new KeywordGroup(item.getKwId(), item.getKwName(), item.getKwMessage()));
            }else {
                KeywordGroup getKG = keywordGroup.get(item.getKwParentId());
                getKG.addChildKeyword(item);
            }
        }
        log.info("keywordGroup.toString() : " + keywordGroup.toString());
        return keywordGroup;
    }

}
