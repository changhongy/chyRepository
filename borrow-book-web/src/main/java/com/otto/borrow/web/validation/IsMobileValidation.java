package com.otto.borrow.web.validation;
/**
 * Project Name：seckilling
 * File Name：IsMobileValidation
 * Package Name：com.seckilling.validation
 * Date：2018/8/7 22:58
 */

import com.alibaba.druid.util.StringUtils;
import com.otto.borrow.web.util.ValidationUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.validation
 * @ClassName IsMobileValidation
 * @date 2018/8/7 22:58
 */
public class IsMobileValidation implements ConstraintValidator<IsMobile, String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (required) {
            return ValidationUtil.isMobile(s);
        } else {
            if (StringUtils.isEmpty(s)) {
                return true;
            } else {
                return ValidationUtil.isMobile(s);
            }
        }
    }
}
