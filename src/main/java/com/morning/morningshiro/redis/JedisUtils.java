package com.morning.morningshiro.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {
    private static final JedisPool JEDIS_POOL;
    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JEDIS_POOL = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
    }

    public static Jedis getJedis() {
        Jedis resource = JEDIS_POOL.getResource();
        return resource;
    }
}
