package com.pingo.mapper;
import com.pingo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {

    public List<User> selectUser();
}
