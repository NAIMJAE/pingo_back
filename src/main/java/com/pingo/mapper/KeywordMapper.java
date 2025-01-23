package com.pingo.mapper;

import com.pingo.entity.keywords.Keyword;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KeywordMapper {
    // 키워드 카테고리 목록 조회
    public List<Keyword> selectKeywordListFor2ndCategory();
}
