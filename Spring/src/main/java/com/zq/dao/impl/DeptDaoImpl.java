package com.zq.dao.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zq.annotation.PrintSql;
import com.zq.dao.DeptDao;
import com.zq.entity.Dept;
import com.zq.mapper.DeptMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-01-06
 */
@Service
public class DeptDaoImpl extends ServiceImpl<DeptMapper, Dept> implements DeptDao {
    @Resource
    private DeptMapper deptMapper;
}
