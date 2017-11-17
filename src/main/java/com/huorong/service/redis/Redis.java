package com.huorong.service.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Redis {
    static Logger log = LoggerFactory.getLogger(Redis.class);
    static JedisPool pool;

    static {

        String[] redis = new String[] { "127.0.0.1", "6379" };

        log.debug("redis的链接地址为: {}", redis[0]);

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        String host = redis[0];
        int port = Integer.parseInt(redis[1]);
        int timeout = 2000;
        String password = null;
        if (redis.length == 3) {
            password = redis[2];
        }
        int database = 0;
        int maxClients = 50;
        jedisPoolConfig.setMaxTotal(maxClients);
        jedisPoolConfig.setTestOnBorrow(true);

        pool = new JedisPool(jedisPoolConfig, host, port, timeout, password, database);
    }

    public static void destroy() {
        pool.destroy();
    }

    public static Jedis jedis() {
        return pool.getResource();
    }

    public static Object op(JedisOp jedisOp) {
        Jedis jedis = jedis();
        try {
            return jedisOp.exec(jedis);
        } finally {
            jedis.close();
        }

    }

    public Long ttl(final String key) {
        Jedis jedis = jedis();
        try {
            return jedis.ttl(key);
        } finally {
            jedis.close();
        }
    }

    public static String setex(String key, String value, long expire, TimeUnit timeUnit) {
        Jedis jedis = jedis();
        try {
            return jedis.setex(key, (int) timeUnit.toSeconds(expire), value);
        } finally {
            jedis.close();
        }
    }

    public static String hmsetex(String key, Map value, long expire, TimeUnit timeUnit) {
        Jedis jedis = jedis();
        try {
            String result = jedis.hmset(key, value);
            jedis.expire(key, (int) timeUnit.toSeconds(expire));
            return result;
        } finally {
            jedis.close();
        }
    }

    public static String set(String key, String value) {
        Jedis jedis = jedis();
        try {
            return jedis.set(key, value);
        } finally {
            jedis.close();
        }
    }

    public static long incr(String key) {
        Jedis jedis = jedis();
        try {
            return jedis.incr(key);
        } finally {
            jedis.close();
        }
    }

    public static long incrBy(String key, long incrBy) {
        Jedis jedis = jedis();
        try {
            return jedis.incrBy(key, incrBy);
        } finally {
            jedis.close();
        }
    }

    public static String hmset(String key, Map value) {
        Jedis jedis = jedis();
        try {
            return jedis.hmset(key, value);
        } finally {
            jedis.close();
        }
    }

    public static String get(String key, String defaultValue) {
        String value = get(key);
        return value == null ? defaultValue : value;
    }

    public static String get(String key) {
        Jedis jedis = jedis();
        try {
            return jedis.get(key);
        } finally {
            jedis.close();
        }
    }

    public static String hget(String key, String field) {
        Jedis jedis = jedis();
        try {
            return jedis.hget(key, field);
        } finally {
            jedis.close();
        }
    }

    public static Long lpush(String key, String... names) {
        Jedis jedis = jedis();
        try {
            return jedis.lpush(key, names);
        } finally {
            jedis.close();
        }
    }

    public static String lpop(String key) {
        Jedis jedis = jedis();
        try {
            return jedis.lpop(key);
        } finally {
            jedis.close();
        }
    }

    public static List lget(String key) {
        Jedis jedis = jedis();
        try {
            return jedis.lrange(key, 0, -1);
        } finally {
            jedis.close();
        }
    }

    public static List lrange(String key, long start, long end) {
        Jedis jedis = jedis();
        try {
            return jedis.lrange(key, start, end);
        } finally {
            jedis.close();
        }
    }

    public static String getAndDel(String key) {
        Jedis jedis = jedis();
        try {
            Transaction multi = jedis.multi();
            multi.get(key);
            multi.del(key);
            List<Object> result = multi.exec();
            return (String) result.get(0);
        } finally {
            jedis.close();
        }
    }

    public static Long del(String key) {
        Jedis jedis = jedis();
        try {
            return jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    public static void expire(String key, long expireValue, TimeUnit timeUnit) {
        Jedis jedis = jedis();
        try {
            jedis.expire(key, (int) timeUnit.toSeconds(expireValue));
        } finally {
            jedis.close();
        }
    }

    public static interface JedisOp {
        Object exec(Jedis jedis);
    }

    public static abstract class JedisVoidOp implements JedisOp {

        public Object exec(Jedis jedis) {
            call(jedis);
            return null;
        }

        protected abstract void call(Jedis jedis);
    }

}
