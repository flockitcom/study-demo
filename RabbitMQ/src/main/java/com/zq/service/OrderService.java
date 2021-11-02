package com.zq.service;

import com.zq.exception.MyException;

import java.math.BigDecimal;

/**
 * fasfdasfasf
 * @author zqian
 * @date 2021/3/17
 */
public interface OrderService {
    void placeOrder(Integer uid, BigDecimal money) throws MyException;
}
