package com.otto.borrow.web.validation;
/**
 * Project Name：seckilling
 * File Name：IsMobile
 * Package Name：com.seckilling.validation
 * Date：2018/8/7 22:37
 */

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.validation
 * @ClassName IsMobile
 * @date 2018/8/7 22:37
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsMobileValidation.class})
public @interface IsMobile {

    boolean required() default true;

    String message() default "手机号格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
