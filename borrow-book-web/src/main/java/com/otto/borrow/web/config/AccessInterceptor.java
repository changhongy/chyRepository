package com.otto.borrow.web.config;
/**
 * Project Name：seckilling
 * File Name：AccessInterceptor
 * Package Name：com.seckilling.validation
 * Date：2018/8/15 23:23
 */

import com.alibaba.fastjson.JSON;
import com.otto.borrow.web.constant.GlobalConstant;
import com.otto.borrow.web.dto.UserDTO;
import com.otto.borrow.web.redis.RedisService;
import com.otto.borrow.web.redis.key.AccessKey;
import com.otto.borrow.web.result.CodeMsg;
import com.otto.borrow.web.result.Result;
import com.otto.borrow.web.service.UserService;
import com.otto.borrow.web.util.ThreadLocalUtil;
import com.otto.borrow.web.validation.AccessLimit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

/**
 * @author 张辉
 * @Title：
 * @Description： 拦截器
 * @Package com.seckilling.validation
 * @ClassName AccessInterceptor
 * @date 2018/8/15 23:23
 */
@Service
public class AccessInterceptor extends HandlerInterceptorAdapter {

    private final UserService userService;

    private final RedisService redisService;

    @Autowired
    public AccessInterceptor(UserService userService, RedisService redisService) {
        this.userService = userService;
        this.redisService = redisService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {

            HandlerMethod method = (HandlerMethod) handler;
            UserDTO user = getUser(request, response);
            ThreadLocalUtil.set(user);
            AccessLimit accessLimit = method.getMethodAnnotation(AccessLimit.class);
            if (accessLimit == null) {
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            boolean needLogin = accessLimit.needLogin();
            String key = request.getRequestURI();
            if (needLogin) {
                if (null == user) {
                    render(response, CodeMsg.SESSION_ERROR);
                    return false;
                }
            }
            if (null != user) {
                key += ":" + user.getId();
                AccessKey ak = AccessKey.withAccess(seconds);
                Integer count = redisService.get(ak, key, Integer.class);
                if (count == null) {
                    redisService.set(ak, key, 1);
                } else if (count < maxCount) {
                    redisService.incr(ak, key);
                } else {
                    render(response, CodeMsg.SERVER_REQUEST_TOO_OFTEN);
                    return false;
                }
            }
        }
        return true;
    }

    private void render(HttpServletResponse response, CodeMsg cm) throws Exception {
        OutputStream os = null;
        try {
            response.setContentType("application/json;charset=UTF-8");
            os = response.getOutputStream();
            String json = JSON.toJSONString(Result.error(cm));
            os.write(json.getBytes("UTF-8"));
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            os.close();
        }
    }

    private UserDTO getUser(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("token");
//        String paramToken = request.getParameter(GlobalConstant.COOKIE_NAME);
//        String cookieToken = getCookieValue(request, GlobalConstant.COOKIE_NAME);
        if (StringUtils.isBlank(token)) {
            return null;
        }
//        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        UserDTO userDTO = userService.getByToken(response, token);
        if (null == userDTO) {
            return null;
        }
        return userDTO;
    }

    private String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (null == cookies || cookies.length <= 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
