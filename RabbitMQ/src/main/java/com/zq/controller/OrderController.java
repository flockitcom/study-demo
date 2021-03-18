package com.zq.controller;


import com.zq.controller.base.BaseController;
import com.zq.exception.MyException;
import com.zq.exception.MyExceptionEnum;
import com.zq.response.MyResponse;
import com.zq.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@Slf4j
public class OrderController extends BaseController {

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

}
