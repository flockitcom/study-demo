package com.zq.test;

import org.springframework.util.StopWatch;

/**
 * @author zqian
 * @date 2021/3/25
 */
public class T_StopWatch {
    public static void main(String[] args) throws InterruptedException {
        StopWatch sw = new StopWatch("test");
        sw.start("task1");
        // do something
        Thread.sleep(100);
        sw.stop();
        sw.start("task2");
        // do something
        Thread.sleep(200);
        sw.stop();
        System.out.println(sw.prettyPrint());
    }
}
