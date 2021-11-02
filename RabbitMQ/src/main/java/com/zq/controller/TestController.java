package com.zq.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zqian
 * @date 2021/3/29
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping(path = "/order/test")
    public void getPost() {
        ThreadPoolExecutor service = new ThreadPoolExecutor(5, 10,
                1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        ) {
            @Override
            protected void terminated() {
            }

        };

        for (int i = 0; i < 100; i++) {
            for (int i1 = 1; i1 < 6; i1++) {
                service.execute(new MyThread(i1));
            }
        }
        service.shutdown();
    }
}

class MyThread extends Thread {
    private final Integer id;
    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");
    private final String url = "http://127.0.0.1:8900/order/place2";

    OkHttpClient client = new OkHttpClient.Builder()
            .retryOnConnectionFailure(false)
            .callTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
//            .proxy(proxy)
            .build();


    public MyThread(Integer id) {
        this.id = id;
    }

    @Override
    public void run() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("uid", id);
        map.put("money", new BigDecimal("1"));
        RequestBody body = RequestBody.create(JSON.toJSONString(map), JSONTYPE);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {
            Response execute = client.newCall(request).execute();
            System.out.println(execute.body().string());
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }
}
