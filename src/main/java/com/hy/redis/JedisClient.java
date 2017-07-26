package com.hy.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by huangyong on 2017/6/29.
 * 测试redis的数据类型操作
 */
public class JedisClient {

    public static void main(String[] args) {
        Jedis jedis = JedisUtil.getJedis();
        jedis.set("k1", "测试");
    }
}
