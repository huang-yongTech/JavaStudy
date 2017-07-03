package com.hy.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by huangyong on 2017/6/29.
 * 测试redis的数据类型操作
 */
public class JedisClient {

    public static void main(String[] args) {
        Jedis jedis = JedisUtil.getJedis();
        jedis.watch("k1");
        int k1 = Integer.parseInt(jedis.get("k1"));
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (k1 < 10) {
            jedis.unwatch();
            System.out.println("已被其他客户端修改");
            System.out.println(k1);
        }else {
            System.out.println("未被修改");
            System.out.println(k1);
        }
    }
}
