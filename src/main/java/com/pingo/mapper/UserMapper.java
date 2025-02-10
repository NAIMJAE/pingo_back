package com.pingo.mapper;

import com.pingo.entity.users.UserImage;
import com.pingo.entity.users.UserMypageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    // 유저 마이페이지 상세 정보 조회
    UserMypageInfo getUserMypageInfo(@Param("userNo") String userNo);

    // 유저 이미지 조회
    List<UserImage> getUserImages(@Param("userNo") String userNo);
}
