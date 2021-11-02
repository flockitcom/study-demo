package com.zq.service;

/**
 * @author zqian
 * @date 2020/12/15
 */
public interface DeptService {
    void create(String name);

    /**
     * 自定义bean交给spring管理
     */
    void bean();
}
