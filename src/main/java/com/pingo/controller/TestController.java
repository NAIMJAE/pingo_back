package com.pingo.controller;

import com.pingo.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class TestController {

    private final TestService testService;

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return testService.selectTest();
    }
}
