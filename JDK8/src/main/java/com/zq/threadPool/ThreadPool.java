package com.zq.threadPool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class ThreadPool {


    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> deque = new ConcurrentLinkedDeque<>();
        for (int i = 0; i < 10; i++) {
            deque.add("tast:" + i);
        }

        //工作队列
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1,
                new ThreadFactoryBuilder()
                        .setNameFormat("inp-charge-execute-pool-%d").build());

        executorService.scheduleAtFixedRate(
                () -> {
                    // 任务处理完成
                    String pop = deque.pop();
                    if (StringUtils.isEmpty(pop)) {
                        return;
                    }
                    System.out.println("循环任务:" + System.currentTimeMillis() / 1000);
                },
                1,
                2,
                TimeUnit.SECONDS
        );
    }
}
