package com.zq.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zq.constant.RabbitMQConstant;
import com.zq.dao.OBalanceDao;
import com.zq.dao.OOrderDao;
import com.zq.dao.OUserDao;
import com.zq.entity.OBalance;
import com.zq.entity.OUser;
import com.zq.exception.MyException;
import com.zq.exception.MyExceptionEnum;
import com.zq.response.MyResponse;
import com.zq.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zqian
 * @date 2021/3/17
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private OUserDao oUserDao;
    @Resource
    private OBalanceDao oBalanceDao;
    @Resource
    private OOrderDao oOrderDao;

    @Override
    public void placeOrder(Integer uid, BigDecimal money) throws MyException {
        //查询用户是否存在
        OUser user = oUserDao.getById(uid);
        if (user == null) {
            log.info("用户不存在,userId:" + uid);
            MyResponse.returnFail(MyExceptionEnum.PARAMS_ERROR, "用户不存在");
        }
        LambdaQueryWrapper<OBalance> balanceQuery = Wrappers.lambdaQuery(OBalance.class);
        balanceQuery.eq(OBalance::getUid, user.getId());
        OBalance balance = oBalanceDao.getOne(balanceQuery);
        if (balance == null) {
            log.error("用户余额信息不存在,userId:" + user.getId());
            MyResponse.returnFail(MyExceptionEnum.PARAMS_ERROR, "用户余额信息不存在");
        }
        if (balance.getBalance().compareTo(money) == -1) {
            log.error("用户余额不足,userId:" + user.getId() + ",余额:" + balance.getBalance() + ",应需:" + money);
            MyResponse.returnFail(MyExceptionEnum.PARAMS_ERROR, "用户余额不足");
        }
        //生成订单号
        String orderNo = IdUtil.getSnowflake(1, 1).nextIdStr();

        //发送MQ
        Map<String, Object> map = new HashMap<>(4);
        map.put("uid", uid);
        map.put("money", money);
        map.put("orderNo", orderNo);
        rabbitTemplate.convertAndSend(RabbitMQConstant.ORDER_EXCHANGE, "", map);
        log.info("发送消息,map=" + map);
    }
}
