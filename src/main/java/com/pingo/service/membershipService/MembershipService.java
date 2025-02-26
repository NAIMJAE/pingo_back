package com.pingo.service.membershipService;

import com.pingo.dto.ResponseDTO;
import com.pingo.entity.membership.Membership;
import com.pingo.entity.membership.UserMembership;
import com.pingo.mapper.MembershipMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class MembershipService {

    final private MembershipMapper membershipMapper;

    // 멤버쉽 조회
    public ResponseEntity<?> getMembership(String userNo) {
        Optional<UserMembership> userMembership = membershipMapper.selectUserMembership(userNo);
        List<Membership> membershipList = membershipMapper.selectMembership();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("membership", membershipList);

        if (userMembership.isEmpty()) {
            resultMap.put("userMembership", null);
        }else {
            resultMap.put("userMembership", userMembership.get());
        }

        return ResponseEntity.ok().body(ResponseDTO.of("1", "성공", resultMap));
    }

}
