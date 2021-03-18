package com.zq.dao.impl;

import com.zq.dao.OUserDao;
import com.zq.entity.OUser;
import com.zq.mapper.OUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-17
 */
@Service
public class OUserDaoImpl extends ServiceImpl<OUserMapper, OUser> implements OUserDao {

}
