package com.zq.dao.impl;

import com.zq.dao.OOrderDao;
import com.zq.entity.OOrder;
import com.zq.mapper.OOrderMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-17
 */
@Service
public class OOrderDaoImpl extends ServiceImpl<OOrderMapper, OOrder> implements OOrderDao {

}
