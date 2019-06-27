package com.otto.borrow.web.util;
/**
 * Project Name：seckilling
 * File Name：ValidationUtil
 * Package Name：com.seckilling.util
 * Date：2018/8/7 23:02
 */

import com.alibaba.druid.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.util
 * @ClassName ValidationUtil
 * @date 2018/8/7 23:02
 */
public class ValidationUtil {

    private static final Pattern p = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;

        }
        Matcher m = p.matcher(mobile);
        return m.matches();
    }
}
