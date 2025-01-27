package com.pingo.service.swipeStateService;

import com.pingo.dto.ResponseDTO;
import com.pingo.entity.swipeState.UserSwipeDetails;
import com.pingo.mapper.SwipeStateMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class SwipeStateService {

    private final SwipeStateMapper swipeStateMapper;

    // [1] swipe 목록 조회 for match_state_page
    public ResponseEntity<?> selectSwipeUserList(String userNo) {
        // 내가 swipe 한 유저 조회
        List<UserSwipeDetails> mySwipe = swipeStateMapper.getUserSwipeDetails("fromUserNo",userNo, "wait", "T");

        // 나를 swipe 한 유저 조회
        List<UserSwipeDetails> getSwipe = swipeStateMapper.getUserSwipeDetails("toUserNo",userNo, "wait", "T");

        // 데이터 포맷팅 Map<String, List<UserSwipeDetails>>
        Map<String, List<UserSwipeDetails>> resultMap = new HashMap<>();
        resultMap.put("mySwipe", mySwipe);
        resultMap.put("getSwipe", getSwipe);

        return ResponseEntity.ok().body(ResponseDTO.of("1", "성공", resultMap));
    }
}
