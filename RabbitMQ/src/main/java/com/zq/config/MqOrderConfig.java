package com.zq.config;

import com.zq.constant.RabbitMQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 订单MQ配置类
 * @author zqian
 * @date 2021/3/16
 */
@Configuration
public class MqOrderConfig {

//    @Resource
//    private Queue orderQueue;
//
//    @Resource
//    private FanoutExchange orderExchange;

    /**
     * 创建正常队列
     */
    @Bean
    public Queue createOrderQueue() {
        return new Queue(RabbitMQConstant.ORDER_QUEUE);
    }

    /***
     * 创建fanout交换机  直接转发
     */
    @Bean
    public FanoutExchange createOrderExchange() {
        return new FanoutExchange(RabbitMQConstant.ORDER_EXCHANGE);
    }


    /**
     * 绑定队列和交换机
     */
    @Bean
    public Binding bindOrder(FanoutExchange createOrderExchange, Queue createOrderQueue) {
        return BindingBuilder.bind(createOrderQueue).to(createOrderExchange);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory containerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
                                                                 ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConcurrentConsumers(RabbitMQConstant.DEFAULT_CONCURRENT);//设置当前并发数量
        factory.setMaxConcurrentConsumers(RabbitMQConstant.MAX_DEFAULT_CONCURRENT);//设置最大并发数量
        configurer.configure(factory, connectionFactory);
        return factory;
    }
}
