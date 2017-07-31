package com.hy.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by huangyong on 2017/6/29.
 * 连接池工具类
 */
public class JedisUtil {
    private JedisUtil() {
    }

    static Jedis getJedis() {
        return JedisPoolHolder.jedisPool.getResource();
    }

    private static class JedisPoolHolder {
        private static final JedisPool jedisPool = new JedisPool(new JedisPoolConfig() {
            @Override
            public void setMaxTotal(int maxTotal) {
                super.setMaxTotal(1000);
            }

            @Override
            public void setMaxIdle(int maxIdle) {
                super.setMaxIdle(100);
            }

            @Override
            public void setMaxWaitMillis(long maxWaitMillis) {
                super.setMaxWaitMillis(1000000);
            }

            @Override
            public void setTestOnBorrow(boolean testOnBorrow) {
                super.setTestOnBorrow(true);
            }
        }, "10.10.10.67", 6379);
    }

    public static void closeJedis(Jedis jedis) {
        if (null != jedis)
            jedis.close();
    }
}
