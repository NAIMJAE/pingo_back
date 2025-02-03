package com.pingo.mapper;

import com.pingo.entity.keywords.Keyword;
import com.pingo.entity.users.SignIn;
import com.pingo.entity.users.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SignMapper {
    // userId로 사용자 정보 조회
    Optional<Users> findByUserIdForSignIn(@Param("userId") String userId);

    // 회원가입시 아이디 중복 체크를 위한 회원 아이디 조회
    int selectUserIdForValidateId(@Param("inputId") String inputId);

    // 회원가입시 키워드 선택을 위한 3차 키워드 조회
    List<Keyword> select3ndKeyword();
}
