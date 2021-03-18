package com.zq.consumer;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zq.constant.RabbitMQConstant;
import com.zq.dao.OBalanceDao;
import com.zq.dao.OOrderDao;
import com.zq.entity.OBalance;
import com.zq.entity.OOrder;
import com.zq.exception.MyException;
import com.zq.exception.MyExceptionEnum;
import com.zq.response.MyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

@Component
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class CommentListener {
    @Resource
    private OBalanceDao oBalanceDao;
    @Resource
    private OOrderDao oOrderDao;

    @RabbitHandler
    @RabbitListener(queues = RabbitMQConstant.ORDER_QUEUE,containerFactory = "containerFactory")
    public void comment(Map<String, Object> message) throws MyException {
        log.info("接收消息,map:" + message);
        Integer uid = (Integer) message.get("uid");
        BigDecimal money = (BigDecimal) message.get("money");
        String orderNo = (String) message.get("orderNo");
        //扣除余额
        LambdaQueryWrapper<OBalance> balanceQuery = Wrappers.lambdaQuery(OBalance.class);
        balanceQuery.eq(OBalance::getUid, uid)
                .last("for update");
        OBalance balance = oBalanceDao.getOne(balanceQuery);
        if (balance == null) {
            log.error("用户余额信息不存在:userid" + uid);
            return;
        }
        if (balance.getBalance().compareTo(money) == -1) {
            log.error("用户余额不足,userId:" + uid + ",余额:" + balance.getBalance() + ",应需:" + money);
            MyResponse.returnFail(MyExceptionEnum.PARAMS_ERROR, "用户余额不足");
        }
        BigDecimal subtract = balance.getBalance().subtract(money);

        OBalance balanceNew = new OBalance();
        balanceNew.setId(balance.getId());
        balanceNew.setBalance(subtract);
        oBalanceDao.updateById(balanceNew);

        //增加订单
        OOrder order = new OOrder();
        order.setOrderNo(orderNo);
        order.setOrderMoney(money);
        oOrderDao.save(order);
    }
}
