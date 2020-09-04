package com.zq.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zq.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    int deleteReal(@Param(Constants.WRAPPER) Wrapper wrapper);

    List<User> getAll(@Param(Constants.WRAPPER) Wrapper wrapper);
}
