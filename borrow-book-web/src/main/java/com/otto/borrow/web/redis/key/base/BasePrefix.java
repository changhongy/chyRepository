package com.otto.borrow.web.redis.key.base;
/**
 * Project Name：seckilling
 * File Name：BasePrefix
 * Package Name：com.seckilling.jedis.key
 * Date：2018/8/5 20:24
 */

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.jedis.key
 * @ClassName BasePrefix
 * @date 2018/8/5 20:24
 */
public class BasePrefix implements KeyPrefix {

    private int expireSeconds;

    private String prefix;

    public BasePrefix(String prefix) {
        this(0, prefix);
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    /**
     * 超时时间, 0代表永不过期
     *
     * @return
     */
    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    /**
     * 获取KEY前缀
     *
     * @return
     */
    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix + ":";
    }
}
