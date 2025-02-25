package com.pingo.mapper;

import com.pingo.entity.membership.Membership;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MembershipMapper {
    // 멤버쉽 조회
    public List<Membership> selectMembership();

}
