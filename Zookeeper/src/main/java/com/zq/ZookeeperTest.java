package com.zq;

import org.I0Itec.zkclient.ZkClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Zookeeper连接测试
 * @author zqian
 * @date 2020/11/17
 */
public class ZookeeperTest {

    private static String ip = "129.211.28.148:2181";

    private static int session_timeout = 40000;

    //连接超时时间
    private static final int CONNECTION_TIMEOUT = 5000;

    private static CountDownLatch latch = new CountDownLatch(1);

    /**
     * baseSleepTimeMs：初始的重试等待时间
     * maxRetries：最多重试次数
     *
     *
     * ExponentialBackoffRetry：重试一定次数，每次重试时间依次递增
     * RetryNTimes：重试N次
     * RetryOneTime：重试一次
     * RetryUntilElapsed：重试一定时间
     */
    private static final RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);


    /**
     * 使用zookeeper原始api
     * @param
     */
    @Test
    public void test() throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper(ip, session_timeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    //确认已经连接完毕后再进行操作
                    latch.countDown();
                    System.out.println("已经获得了连接");
                }
            }
        });
        //连接完成之前先等待
        latch.await();
        ZooKeeper.States states = zooKeeper.getState();
        System.out.println(states);
    }

    /**
     * 使用ZkClient客户端连接，这种连接比较简单
     * @param
     */
    @Test
    public void test2(){
        ZkClient zkClient = new ZkClient(ip,session_timeout);
        System.out.println(zkClient.getChildren("/"));
        System.out.println("连接成功！");
    }

    /**
     * 使用curator连接：
     * @param
     */
    @Test
    public void test3() throws Exception {
        //创建 CuratorFrameworkImpl实例
        CuratorFramework client = CuratorFrameworkFactory.newClient(ip, session_timeout, CONNECTION_TIMEOUT, retryPolicy);

        //启动
        client.start();

        System.out.println("连接成功！");
        Object o = client.getChildren().forPath("/");

    }
}
