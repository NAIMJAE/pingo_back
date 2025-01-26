package com.pingo.controller;

import com.pingo.service.swipeStateService.SwipeStateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class SwipeStateController {

    final private SwipeStateService swipeStateService;

    @GetMapping("/swipeState")
    public ResponseEntity<?> swipeState() {

        String userNo = "US12345678";


        return swipeStateService.selectSwipeUserList(userNo);

    }
}
