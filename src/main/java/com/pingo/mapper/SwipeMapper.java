package com.pingo.mapper;

import com.pingo.entity.swipe.Swipe;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SwipeMapper {
    void insertUserSwipe(Swipe swipe);
}
