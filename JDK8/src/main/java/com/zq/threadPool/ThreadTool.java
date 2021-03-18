package com.zq.threadPool;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 线程池测试类
 * @author zqian
 * @date 2021/1/14
 */
public class ThreadTool {

    @Test
    public void threadTest() {
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10000; i++) {
            service.execute(new MyThread());
        }
        service.shutdown();
    }

    @Test
    public void callableTest() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Integer> future1 = service.submit(new MyCallable());
        Future<Integer> future2 = service.submit(new MyCallable());
        Future<Integer> future3 = service.submit(new MyCallable());
        Future<Integer> future4 = service.submit(new MyCallable());

        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());
        System.out.println(future4.get());
        service.shutdown();
    }
}

class MyThread implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() {
        double v = Math.random() * 100;
        return (int) v;
    }
}