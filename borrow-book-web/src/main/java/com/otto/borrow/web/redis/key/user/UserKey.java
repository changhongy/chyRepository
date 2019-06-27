package com.otto.borrow.web.redis.key.user;
/**
 * File Name：UserKey
 * Date：2018/8/5 20:21
 */


import com.otto.borrow.web.redis.key.base.BasePrefix;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @ClassName UserKey
 * @date 2018/8/5 20:21
 */
public class UserKey extends BasePrefix {

    private static final int TOKEN_EXPIRE = 1800;

    private static final int TOKEN_EXPIRE_REMEMBER = 3600 * 24 * 7;

    private static final int VERIFICATION_MAIL = 15 * 60;

    private UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    private UserKey(String prefix) {
        super(prefix);
    }

    /**
     * 用户ID作为key，且永不过期
     */
    public static UserKey keyById = new UserKey("id");
    /**
     * token
     */
    public static UserKey token = new UserKey(TOKEN_EXPIRE, "token");

    public static UserKey tokenSeven = new UserKey(TOKEN_EXPIRE_REMEMBER, "token");

    public static UserKey resetMail = new UserKey(VERIFICATION_MAIL, "mail");
}
