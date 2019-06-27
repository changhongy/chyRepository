package com.otto.borrow.web.util;
/**
 * Project Name：seckilling
 * File Name：MD5Util
 * Package Name：com.seckilling.util
 * Date：2018/8/6 21:05
 */

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.util
 * @ClassName MD5Util
 * @date 2018/8/6 21:05
 */
public class MD5Util {

    /**
     * MD5加密
     *
     * @param src
     * @return
     */
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

}
