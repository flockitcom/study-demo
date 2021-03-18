package com.zq;

import cn.hutool.http.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zqian
 * @date 2021/3/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitMQApplication.class)
public class TestHttp {
    public static int code = 0;
    @Test
    public void getPost() {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 300; i++) {
            for (int i1 = 1; i1 < 6; i1++) {
                service.execute(new MyThread(i1));
                code++;
            }
        }
        service.shutdown();
        System.out.println(code);
    }
}

class MyThread extends Thread{

    private final Integer id;

    public MyThread(Integer id) {
        this.id = id;
    }

    @Override
    public void run() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("uid", id);
        map.put("money", new BigDecimal(1));
        HttpUtil.post("http://127.0.0.1:8900/order/place", map);
    }
}
