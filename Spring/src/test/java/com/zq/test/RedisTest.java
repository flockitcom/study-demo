package com.zq.test;

import com.zq.constant.FileConstant;
import com.zq.util.RedisClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Resource
    private RedisClient redisClient;


    @Test
    public void test01() {
        for (int i = 0; i < 100; i++) {
            redisClient.set(FileConstant.REDIS_FILE_UPLOAD_PREFIX + "/g1/M0" + i + "/00/83/euJUJV_hUJ-ADvNaAAALO0gVtd8438.svg", 1, 5 + i);
        }
    }

    @Test
    public void test02() {
        String s = (String) redisClient.get(FileConstant.REDIS_FILE_UPLOAD_PREFIX);
        System.out.println(s);
    }


}
