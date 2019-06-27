package com.otto.borrow.web.util;
/**
 * Project Name：seckilling
 * File Name：PasswordUtil
 * Package Name：com.seckilling.util
 * Date：2018/8/6 21:29
 */

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.util
 * @ClassName PasswordUtil
 * @date 2018/8/6 21:29
 */
public class PasswordUtil {

    private static final String PASSWORD_KEY = "abcdefghijklmn";

    public static String inputToPassword(String password, String salt) {
        String pass = formToPassword(password);
        return dbToPassword(pass, salt);
    }

    public static String formToPassword(String password) {
        StringBuilder sb = new StringBuilder();
        sb.append(PASSWORD_KEY.charAt(1))
                .append(PASSWORD_KEY.charAt(3))
                .append(password)
                .append(PASSWORD_KEY.charAt(5));
        return MD5Util.md5(sb.toString());
    }

    public static String dbToPassword(String password, String key) {
        StringBuilder sb = new StringBuilder();
        sb.append(key.charAt(1))
                .append(key.charAt(3))
                .append(password)
                .append(key.charAt(5));
        return MD5Util.md5(sb.toString());
    }
}
