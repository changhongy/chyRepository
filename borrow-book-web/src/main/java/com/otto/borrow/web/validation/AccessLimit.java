package com.otto.borrow.web.validation;
/**
 * Project Name：seckilling
 * File Name：AccessLimit
 * Package Name：com.seckilling.validation
 * Date：2018/8/15 23:20
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.validation
 * @ClassName AccessLimit
 * @date 2018/8/15 23:20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AccessLimit {
    int seconds();

    int maxCount();

    boolean needLogin() default true;
}
