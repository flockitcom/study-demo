package com.zq.constant;

public class RabbitMQConstant {

    //队列
    /***
     * 正常队列
     */
    public static final String ORDER_QUEUE = "order_queue";


    //交换机
    /***
     * 产品交换机 fanout 直接转发
     */
    public static final String ORDER_EXCHANGE = "order_exchange";


    /***
     * 7天后 默认好评
     */
//    public static final int COMMENT_DEFAULT_TIME = 7 * 24 * 60 * 60;
    public static final int COMMENT_DEFAULT_TIME = 30;

    public static final int DEFAULT_CONCURRENT = 8;

    public static final int MAX_DEFAULT_CONCURRENT = 8;

}
