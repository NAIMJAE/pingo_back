package com.pingo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LocationMapper {

    // 유저 위치 저장 (있으면 UPDATE, 없으면 INSERT)
    void updateUserLocation(@Param("userNo") String userNo,
                            @Param("latitude") double latitude,
                            @Param("longitude") double longitude);

    // 반경 내 유저 검색
    List<String> getNearbyUsers(@Param("latitude") double latitude,
                                @Param("longitude") double longitude,
                                @Param("radiusKm") double radiusKm);

}
