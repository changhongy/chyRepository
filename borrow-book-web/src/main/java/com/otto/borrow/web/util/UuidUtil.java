package com.otto.borrow.web.util;
/**
 * Project Name：seckilling
 * File Name：UuidUtil
 * Package Name：com.seckilling.util
 * Date：2018/8/9 22:44
 */

import java.util.UUID;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.util
 * @ClassName UuidUtil
 * @date 2018/8/9 22:44
 */
public class UuidUtil {

    /**
     * 生成UUID
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(uuid());
    }
}
