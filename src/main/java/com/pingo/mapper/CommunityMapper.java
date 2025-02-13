package com.pingo.mapper;

import com.pingo.dto.community.PlaceReviewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommunityMapper {

    public List<PlaceReviewDTO> selectPlaceReviewWithSort(@Param("cateSort") String cateSort,
                                                  @Param("searchSort") String searchSort);

    public List<PlaceReviewDTO> selectPlaceReviewWithKeyword(@Param("keyword") String keyword);
}
