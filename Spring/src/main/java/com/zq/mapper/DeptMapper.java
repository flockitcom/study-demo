package com.zq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zq.annotation.PrintSql;
import com.zq.entity.Dept;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-12-15
 */
public interface DeptMapper extends BaseMapper<Dept> {
    @Override
    @PrintSql
    Dept selectById(Serializable id);

    @PrintSql
    Dept getByIdAge(@Param("id") Integer id, @Param("age")Integer age);
}
