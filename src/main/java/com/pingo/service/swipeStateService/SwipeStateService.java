package com.pingo.service.swipeStateService;

import com.pingo.entity.swipeState.UserSwipeDetails;
import com.pingo.mapper.SwipeStateMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SwipeStateService {

    private final SwipeStateMapper swipeStateMapper;

    // [1] swipe 목록 조회 for match_state_page
    public void selectSwipeUserList() {
        // 내가 swipe 한 유저 조회
        List<UserSwipeDetails> aa = swipeStateMapper.getUserSwipeDetails("US12341234", "wait", "T");
        // 조회 안되는중
        log.info("aa : " + aa);
        // 나를 swipe 한 유저 조회
        
        // 데이터 포맷팅 Map<String, SwipeUserData>
        
        // 리턴
    }
}
