package com.pingo.mapper;

import com.pingo.entity.swipeState.UserSwipeDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SwipeStateMapper {

    List<UserSwipeDetails> getUserSwipeDetails(
            @Param("fromUserNo") String fromUserNo,
            @Param("swipeState") String swipeState,
            @Param("imageProfile") String imageProfile
    );

}
