package com.otto.borrow.web.service;
/**
 * Project Name：seckilling
 * File Name：UserService
 * Package Name：com.seckilling.service
 * Date：2018/8/4 22:30
 */
import com.otto.borrow.web.dto.LoginDTO;
import com.otto.borrow.web.dto.SessionDto;
import com.otto.borrow.web.dto.UserDTO;
import com.otto.borrow.web.dto.req.WechatLoginDto;
import com.otto.borrow.web.entity.UserEntity;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.service
 * @ClassName UserService
 * @date 2018/8/4 22:30
 */
public interface UserService {

    /**
     * 登录
     *
     * @param loginDTO
     * @param response
     */
    void login(LoginDTO loginDTO, HttpServletResponse response);

    /**
     * 注册
     *
     * @param user
     */
    void register(UserEntity user, HttpServletResponse response);

    /**
     * 根据token获取用户信息
     *
     * @param response
     * @param token
     * @return
     */
    UserDTO getByToken(HttpServletResponse response, String token);

    /**
     * 删除用户登录信息
     *
     * @param response
     * @param token
     * @return
     */
    Long delByToken(HttpServletResponse response, String token);

    /**
     * 微信登录
     * @param code 唯一码
     */
    SessionDto wechatLogin(HttpServletResponse response, String code,UserEntity userEntity);

    UserEntity selectById(Long id);
}
