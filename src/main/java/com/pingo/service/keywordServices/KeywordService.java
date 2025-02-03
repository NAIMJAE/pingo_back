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

    // [1] 2차 키워드 카테고리까지 조회 for Keyword_Page
    public ResponseEntity<?> selectKeywordListFor2ndCategory() {
        List<Keyword> keywordList = keywordMapper.selectKeywordListFor2ndCategory();

        Map<String, KeywordGroup> keywordGroup = transformKeywordListToGroupMap(keywordList);

        return ResponseEntity.ok().body(ResponseDTO.of("1", "성공", keywordGroup));
    }

    // [1-1] KeywordList 를 Map<String, KeywordGroup> 구조로 변경
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

    // [2]
    public ResponseEntity<?> recommendBasedOnKeywords(String userNo, String sKwId) {
        // 1) 나의 키워드 정보 조회
        // UserKeyword myKeyword = SELECT * FROM "userKeyword" WHERE "userNo" = userNo;

        // 2) 내 주변 사람 목록 조회

        // 3) 내 주변 사람들의 키워드 조회
        // List<UserKeyword> otherKeyword

        // 4) 내가 선택한 키워드(sKwId) 의 하위 키워드 목록 조회
        // List<String> favoriteKeywordList = SELECT "kwId" FROM WHERE "kwParentId" = sKwId;

        // 5) 내 키워드와 주변 사람들 키워드 비교 (+알고리즘) 후 % 반환
        // 나의 키워드 Map<String, List<String>> myKeywordList = transformKeywordList(myKeyword);
        // 내가 선택한 키워드 = 4)에서 조회한 결과
        // for (UserKeyword other : otherKeyword) {
        //    Map<String, List<String>> otherKeywordList = transformKeywordList(other);
        //
        //    내가 선택한 키워드 vs 상대의 키워드
        //    otherKeywordList.get("my") 와 favoriteKeywordList가 몇개나 일치하는지 비교
        //    2개 이상 일치하지 않으면 return -> 이 사람은 추천하지 않음
        //
        //    상대가 선호하는 키워드 vs 나의 키워드
        //    otherKeywordList.get("favorite") 와 myKeywordList가 몇개나 일치하는지 비교
        //    일치하는 키워드 가지고 알고리즘 계산
        //    
        // }

        // private void transformKeywordList() {
        //
        // }

        // 시나리오
        // 나 - '나의 키워드'(3차) / '내가 선택한 키워드'(2차)
        // 상대 - '상대의 키워드'(3차) / '상대가 선호하는 키워드'(3차)

        // ex) 나 : 내향적이지만 외향적인 사람 선호 / 상대 : 외향적이지만 내향적이고 실내활동 하는 사람 선호

        // [1] '내가 선택한 키워드'를 '상대의 키워드'로 가지고 있는 사람 필터링 후 일치 여부 계산
        // '내가 선택한 키워드' = 외향적
        // '상대의 키워드' = 사교, 적극, 유쾌, 창의, 탐험, 음악, 상상, 팀워크, 목표, 모험
        // '상대의 키워드' 10개 중 외향적에 속하는 키워드는 [사교, 적극, 유쾌]로 3개
        // 총 10개 중 3개의 키워드 일치

        // [2] 필터링 한 사람들 중 '상대가 선호하는 키워드' 와 '나의 키워드' 의 일치 여부 계산
        // '상대가 선호하는 키워드' = 혼자, 사색, 조용, 탐험, 음악, 학습, 명상, 여행, 자유, 자연
        // '나의 키워드' = 혼자, 사색, 조용, 탐험, 음악, 그림, 글쓰기, 영감, 연구, 유대감
        // 서로 일치하는 키워드는 [혼자, 사색, 조용, 탐험, 음악]으로 5개
        // 총 15개 중 5개 키워드 일치

        // [3] 서로 일치하는 키워드들을 정해진 알고리즘에 따라 가중치를 부여하여 서로의 선호도 계산
        // 내가 keyword page에서 어떤 키워드(2차)를 선택하느냐에 따라 필터링 되는 사람과 선호도가 변함

        return null;
    }
}
