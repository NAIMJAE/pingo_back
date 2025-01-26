package com.pingo.mapper;

import com.pingo.entity.swipeState.UserSwipeDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SwipeStateMapper {

    List<UserSwipeDetails> getUserSwipeDetails(@Param("standardColumn") String standardColumn,
                                               @Param("standardUserNo") String standardUserNo,
                                               @Param("swipeState") String swipeState,
                                               @Param("imageProfile") String imageProfile);

}
