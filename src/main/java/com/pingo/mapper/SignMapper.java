package com.pingo.mapper;

import com.pingo.entity.users.SignIn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SignMapper {
    // userId로 사용자 정보 조회
    SignIn findUserById(@Param("userId") String userId);
}
