package com.zq.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zq.dao.LogsDao;
import com.zq.entity.Logs;
import com.zq.mapper.LogsMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-30
 */
@Service
public class LogsDaoImpl extends ServiceImpl<LogsMapper, Logs> implements LogsDao {

}
