package com.pingo.service.membershipService;

import com.pingo.dto.ResponseDTO;
import com.pingo.entity.membership.Membership;
import com.pingo.mapper.MembershipMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MembershipService {

    final private MembershipMapper membershipMapper;

    // 멤버쉽 조회
    public ResponseEntity<?> getMembership() {
        List<Membership> membershipList = membershipMapper.selectMembership();

        return ResponseEntity.ok().body(ResponseDTO.of("1", "성공", membershipList));
    }

}
