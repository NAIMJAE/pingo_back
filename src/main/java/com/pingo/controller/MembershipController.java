package com.pingo.controller;

import com.pingo.service.membershipService.MembershipService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MembershipController {

    final private MembershipService membershipService;

    // 멤버쉽 조회
    @GetMapping("/membership")
    public ResponseEntity<?> getMembership() {

        return membershipService.getMembership();
    }
}
