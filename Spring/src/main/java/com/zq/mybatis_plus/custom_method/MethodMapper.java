package com.zq.mybatis_plus.custom_method;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface MethodMapper<T> extends BaseMapper<T> {
    /**
     * 物理删除
     */
    int deleteRealById();

    /**
     * 逻辑删除并填充
     * @param t
     */
    int deleteByIdWithFill(T t);
}
