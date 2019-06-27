package com.otto.borrow.web.redis.key.base;
/**
 * Project Name：seckilling
 * File Name：KeyPrefix
 * Package Name：com.seckilling.jedis.key
 * Date：2018/8/5 20:22
 */

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.jedis.key
 * @ClassName KeyPrefix
 * @date 2018/8/5 20:22
 */
public interface KeyPrefix {
    /**
     * 超时时间
     *
     * @return
     */
    int expireSeconds();

    /**
     * 获取KEY前缀
     *
     * @return
     */
    String getPrefix();
}
