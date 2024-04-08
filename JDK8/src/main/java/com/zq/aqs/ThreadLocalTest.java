package com.zq.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author zqian
 * @date 2021/4/1
 */
public class ThreadLocalTest {
    public static ThreadLocal<Integer> integerThreadLocal = ThreadLocal.withInitial(() -> ThreadLocalRandom.current().nextInt(100));

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 1000; i++) {
            service.execute(() -> {
                System.out.println(integerThreadLocal.get());
            });
        }
        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);

    }
}
