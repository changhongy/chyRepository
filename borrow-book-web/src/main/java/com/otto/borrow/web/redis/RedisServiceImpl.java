package com.otto.borrow.web.redis;
/**
 * Project Name：seckilling
 * File Name：RedisService
 * Package Name：com.seckilling.jedis
 * Date：2018/8/5 20:19
 */

import com.alibaba.fastjson.JSON;
import com.otto.borrow.web.redis.key.base.KeyPrefix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;
import java.util.List;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.jedis
 * @ClassName RedisService
 * @date 2018/8/5 20:19
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private static final String UNLOCK_LUA;

    private static final Long RELEASE_SUCCESS = 1L;

    static {
        //拼接LUA脚本
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }

    @Autowired
    JedisPool jedisPool;

    /**
     * 获取value
     *
     * @param keyPrefix
     * @param key
     * @param clazz
     * @return
     */
    @Override
    public <T> T get(KeyPrefix keyPrefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            String str = jedis.get(realKey);
            T t = stringToObj(str, clazz);
            return t;
        } finally {
            returnRedisPool(jedis);
        }
    }

    /**
     * 设置对象
     *
     * @param keyPrefix
     * @param key
     * @param value
     * @return
     */
    @Override
    public <T> boolean set(KeyPrefix keyPrefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String v = objToString(value);
            if (null == value) {
                return false;
            }
            String realKey = keyPrefix.getPrefix() + key;
            int expire = keyPrefix.expireSeconds();
            if (expire <= 0) {
                jedis.set(realKey, v);
            } else {
                jedis.setex(realKey, expire, v);
            }
            return true;
        } finally {
            returnRedisPool(jedis);
        }
    }

    /**
     * 判断key是否存在
     *
     * @param keyPrefix
     * @param key
     * @return
     */
    @Override
    public boolean exists(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returnRedisPool(jedis);
        }
    }

    /**
     * 增加值
     *
     * @param keyPrefix
     * @param key
     * @return
     */
    @Override
    public Long incr(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returnRedisPool(jedis);
        }
    }

    /**
     * 减少值
     *
     * @param keyPrefix
     * @param key
     * @return
     */
    @Override
    public Long decr(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returnRedisPool(jedis);
        }
    }

    /**
     * 删除key
     *
     * @param keyPrefix
     * @param key
     */
    @Override
    public Long del(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.del(realKey);
        } finally {
            returnRedisPool(jedis);
        }
    }

    /**
     * 对象转字符串
     *
     * @param value
     * @param <T>
     * @return
     */
    private <T> String objToString(T value) {
        if (null == value) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return String.valueOf(value);
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return String.valueOf(value);
        } else {
            return JSON.toJSONString(value);
        }
    }

    /**
     * 字符串转对象
     *
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    private <T> T stringToObj(String str, Class<T> clazz) {
        if (null == str || str.length() < 0 || null == clazz) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    /**
     * 释放redis链接到连接池
     *
     * @param jedis
     */
    private void returnRedisPool(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }

    /**
     * 加锁
     *
     * @param lockKey
     * @param value
     * @param expireTime
     * @return
     */
    @Override
    public boolean setLock(String lockKey, String value, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return LOCK_SUCCESS.equals(jedis.set(lockKey, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime));
        } finally {
            returnRedisPool(jedis);
        }
    }

    /**
     * 释放锁
     *
     * @param lockKey
     */
    @Override
    public boolean unLock(String lockKey, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //执行脚本，释放锁，防止删除别人的锁
            Object result = jedis.eval(UNLOCK_LUA, Collections.singletonList(lockKey), Collections.singletonList(value));
            return RELEASE_SUCCESS.equals(result);
        } finally {
            returnRedisPool(jedis);
        }
    }

    /**
     * 修剪列表
     *
     * @param keyPrefix
     * @param key
     * @param start
     * @param end
     */
    @Override
    public void ltrim(KeyPrefix keyPrefix, String key, Long start, Long end) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String k = keyPrefix.getPrefix() + key;
            jedis.ltrim(k, start, end);
        } finally {
            returnRedisPool(jedis);
        }
    }

    /**
     * 列表左端插入
     *
     * @param keyPrefix
     * @param key
     * @param value
     */
    @Override
    public void lpush(KeyPrefix keyPrefix, String key, String... value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String k = keyPrefix.getPrefix() + key;
            jedis.lpush(k, value);
        } finally {
            returnRedisPool(jedis);
        }
    }

    /**
     * 获取列表数据
     *
     * @param keyPrefix
     * @param key
     * @param start
     * @param end
     * @return
     */
    @Override
    public List<String> lrange(KeyPrefix keyPrefix, String key, Long start, Long end) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String k = keyPrefix.getPrefix() + key;
            return jedis.lrange(k, start, end);
        } finally {
            returnRedisPool(jedis);
        }
    }
}
