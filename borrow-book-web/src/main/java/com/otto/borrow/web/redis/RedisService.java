package com.otto.borrow.web.redis;
/**
 * Project Name：seckilling
 * File Name：RedisService
 * Package Name：com.seckilling.jedis
 * Date：2018/8/5 20:20
 */

import com.otto.borrow.web.redis.key.base.KeyPrefix;

import java.util.List;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.jedis
 * @ClassName RedisService
 * @date 2018/8/5 20:20
 */
public interface RedisService {

    /**
     * 获取value
     *
     * @param keyPrefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T get(KeyPrefix keyPrefix, String key, Class<T> clazz);

    /**
     * 设置对象
     *
     * @param keyPrefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    <T> boolean set(KeyPrefix keyPrefix, String key, T value);

    /**
     * 判断key是否存在
     *
     * @param keyPrefix
     * @param key
     * @return
     */
    boolean exists(KeyPrefix keyPrefix, String key);

    /**
     * 增加值
     *
     * @param keyPrefix
     * @param key
     * @return
     */
    Long incr(KeyPrefix keyPrefix, String key);

    /**
     * 减少值
     *
     * @param keyPrefix
     * @param key
     * @return
     */
    Long decr(KeyPrefix keyPrefix, String key);

    /**
     * 删除key
     *
     * @param keyPrefix
     * @param key
     */
    Long del(KeyPrefix keyPrefix, String key);

    /**
     * 加锁
     *
     * @param lockKey
     * @param value
     * @param expireTime
     * @return
     */
    boolean setLock(String lockKey, String value, int expireTime);

    /**
     * 释放锁
     *
     * @param lockKey
     * @param value
     * @return
     */
    boolean unLock(String lockKey, String value);

    /**
     * 修剪列表
     *
     * @param keyPrefix
     * @param key
     * @param start
     * @param end
     */
    void ltrim(KeyPrefix keyPrefix, String key, Long start, Long end);

    /**
     * 列表左端插入
     *
     * @param keyPrefix
     * @param key
     * @param value
     */
    void lpush(KeyPrefix keyPrefix, String key, String... value);

    /**
     * 获取列表数据
     *
     * @param keyPrefix
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<String> lrange(KeyPrefix keyPrefix, String key, Long start, Long end);
}
