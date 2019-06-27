package com.otto.borrow.web.config;
/**
 * Project Name：seckilling
 * File Name：UserArgumentResolvers
 * Package Name：com.seckilling.config
 * Date：2018/8/10 00:02
 */

import com.otto.borrow.web.dto.UserDTO;
import com.otto.borrow.web.service.UserService;
import com.otto.borrow.web.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.config
 * @ClassName UserArgumentResolvers
 * @date 2018/8/10 00:02
 */
@Service
public class UserArgumentResolvers implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> clazz = methodParameter.getParameterType();
        return clazz == UserDTO.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Object obj = ThreadLocalUtil.get();
        if (obj instanceof UserDTO) {
            return (UserDTO) ThreadLocalUtil.get();
        } else {
            return null;
        }
    }

}
