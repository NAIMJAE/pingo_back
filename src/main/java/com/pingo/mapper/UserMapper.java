package com.pingo.mapper;

import com.pingo.entity.keywords.Keyword;
import com.pingo.entity.match.MatchUser;
import com.pingo.entity.users.UserImage;
import com.pingo.entity.users.UserKeyword;
import com.pingo.entity.users.UserMypageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    // 유저 마이페이지 상세 정보 조회
    UserMypageInfo getUserMypageInfo(@Param("userNo") String userNo);

    // 매칭 유저 정보 조회
    MatchUser getMatchUser(@Param("userNo") String userNo);

    // 유저 이미지 조회
    List<UserImage> getUserImages(@Param("userNo") String userNo);

    // 유저 대표이미지를 서브이미지로 설정
    void setMainImageAsSubImage(@Param("currentMainImageNo") String currentMainImageNo);

    // 선택한 서브이미지를 대표이미지로 설정
    void setSubImageAsMainImage(@Param("newMainImageNo") String newMainImageNo);

    // 유저 이미지 추가
    void addUserImage(@Param("imageNo") String imageNo, @Param("imageUrl") String imageUrl, @Param("imageProfile") String bool, @Param("userNo") String userNo);

    // 유저 이미지 삭제
    void deleteUserImage(@Param("imageNoForDelete") String imageNoForDelete);

    // 유저 키워드 조회
    UserKeyword getUserKeyword(@Param("userNo") String userNo);

    // 유저 키워드 디테일 조회
    List<Keyword> getUserKeywordDetail(@Param("myKeywords") String[] myKeywords);

    // 유저 소개 조회
    String selectUserIntroduction(@Param("userNo") String userNo);

    // 유저 키워드 수정
    void updateUserKeyword(@Param("userNo") String userNo,
                           @Param("myKeyword") String myKeyword,
                           @Param("favoriteKeyword") String favoriteKeyword);
}
