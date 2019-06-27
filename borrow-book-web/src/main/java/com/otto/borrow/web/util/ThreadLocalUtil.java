package com.otto.borrow.web.util;
/**
 * Project Name：seckilling
 * File Name：ThreadLocalUtil
 * Package Name：com.seckilling.util
 * Date：2018/8/15 23:37
 */

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.util
 * @ClassName ThreadLocalUtil
 * @date 2018/8/15 23:37
 */
public class ThreadLocalUtil {

    private static ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    public static void set(Object obj) {
        threadLocal.set(obj);
    }

    public static Object get() {
        return threadLocal.get();
    }
}
