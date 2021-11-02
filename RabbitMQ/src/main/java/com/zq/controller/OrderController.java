package com.zq.controller;


import com.rabbitmq.client.*;
import com.zq.exception.MyException;
import com.zq.exception.MyExceptionEnum;
import com.zq.response.MyResponse;
import com.zq.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;


    @PostMapping(path = "/order/place")
    public MyResponse placeOrder(Integer uid, BigDecimal money) throws MyException {
        if (uid == null) {
            MyResponse.returnFail(MyExceptionEnum.PARAMS_ERROR, "用户id不能为空");
        }
        if (money == null || money.compareTo(BigDecimal.ZERO) == -1) {
            MyResponse.returnFail(MyExceptionEnum.PARAMS_ERROR, "金额错误");
        }
        orderService.placeOrder(uid, money);
        return MyResponse.returnSuccess();
    }

    @PostMapping(path = "/order/place2")
    public MyResponse placeOrder2(@RequestBody Map<String, Object> map) throws MyException {
        Integer uid = (Integer) map.get("uid");
        if (uid == null) {
            MyResponse.returnFail(MyExceptionEnum.PARAMS_ERROR, "用户id不能为空");
        }

        if (map.get("money") == null) {
            MyResponse.returnFail(MyExceptionEnum.PARAMS_ERROR, "金额错误");
        }
        System.out.println(map.get("money"));
        BigDecimal money = new BigDecimal(String.valueOf(map.get("money")));
        if (money.compareTo(BigDecimal.ZERO) == -1) {
            MyResponse.returnFail(MyExceptionEnum.PARAMS_ERROR, "金额错误");
        }
        orderService.placeOrder(uid, money);
        return MyResponse.returnSuccess();
    }

    @PostMapping(path = "/order/place3")
    public MyResponse placeOrder3() {
        Channel channel = null;
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
            }
        };
        return null;
    }
}
