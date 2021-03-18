package com.zq.consumer;

import com.zq.util.RedisClient;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * redis失效监听
 * @author zqian
 * @date 2021/1/7
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Resource
    private RedisClient redisClient;

    /**
     * 针对redis数据失效事件，进行数据处理
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 用户做自己的业务处理即可,注意message.toString()可以获取失效的key
        String expiredKey = message.toString();
        System.out.println(expiredKey);
        /*if (expiredKey.endsWith(FileConstant.REDIS_FILE_UPLOAD)) {
            System.out.println(FileConstant.REDIS_FILE_UPLOAD);
        }*/
    }
}
