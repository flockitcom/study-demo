package com.zq.dao.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zq.dao.TouristDao;
import com.zq.entity.Tourist;
import com.zq.mapper.TouristMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 游客表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-30
 */
@Service
public class TouristDaoImpl extends ServiceImpl<TouristMapper, Tourist> implements TouristDao {

}
