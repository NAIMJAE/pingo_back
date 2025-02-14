package com.pingo.mapper;

import com.pingo.entity.swipe.Swipe;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SwipeMapper {

    // 스와이프 저장
    void insertUserSwipe(Swipe swipe);

    // 상대방 PING 조사여부
    boolean isSwipeMatched(@Param("fromUserNo") String fromUserNo, @Param("toUserNo") String toUserNo);
}
