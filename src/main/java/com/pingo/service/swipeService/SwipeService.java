package com.pingo.service.swipeService;

import com.pingo.dto.ResponseDTO;
import com.pingo.dto.TransferDtoToEntity;
import com.pingo.dto.swipe.SwipeRequest;
import com.pingo.entity.swipe.Swipe;
import com.pingo.mapper.SwipeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SwipeService {

    private final SwipeMapper swipeMapper;

    // 스와이프 저장
    public ResponseEntity<?> saveSwipe(SwipeRequest swipeRequest) {
        try{
             Swipe swipe = new Swipe().toInsertEntity(swipeRequest);

             swipeMapper.insertUserSwipe(swipe);

            // 성공 응답 반환
            return ResponseEntity.ok().body(ResponseDTO.of("1", "스와이프가 저장되었습니다.", true));
        } catch (Exception e) {
            throw new RuntimeException(e); // 리팩토리에서 공통 Exception 변경
        }
    }
}
