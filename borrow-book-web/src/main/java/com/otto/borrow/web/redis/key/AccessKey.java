package com.otto.borrow.web.redis.key;
/**
 * Project Name：seckilling
 * File Name：AccessKey
 * Package Name：com.seckilling.jedis.key
 * Date：2018/8/15 23:51
 */


import com.otto.borrow.web.redis.key.base.BasePrefix;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.jedis.key
 * @ClassName AccessKey
 * @date 2018/8/15 23:51
 */
public class AccessKey extends BasePrefix {

    public AccessKey(String prefix) {
        super(prefix);
    }

    public AccessKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static AccessKey withAccess(int expireSeconds) {
        return new AccessKey(expireSeconds, "access");
    }
}
