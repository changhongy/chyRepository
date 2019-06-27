package com.otto.borrow.web.service.impl;
/**
 * Project Name：seckilling
 * File Name：UserServiceImpl
 * Package Name：com.seckilling.service.impl
 * Date：2018/8/4 22:30
 */

import com.alibaba.fastjson.JSON;
import com.otto.borrow.web.dto.LoginDTO;
import com.otto.borrow.web.dto.SessionDto;
import com.otto.borrow.web.dto.UserDTO;
import com.otto.borrow.web.dto.req.WechatLoginDto;
import com.otto.borrow.web.entity.UserEntity;
import com.otto.borrow.web.exception.GlobalException;
import com.otto.borrow.web.mapper.UserMapper;
import com.otto.borrow.web.redis.RedisService;
import com.otto.borrow.web.redis.key.user.UserKey;
import com.otto.borrow.web.result.CodeMsg;
import com.otto.borrow.web.service.UserService;
import com.otto.borrow.web.util.PasswordUtil;
import com.otto.borrow.web.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.service.impl
 * @ClassName UserServiceImpl
 * @date 2018/8/4 22:30
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final RedisService redisService;

    private final RestTemplate restTemplate;

    private static final String COOKIE_NAME = "token";

    private static final String WECHAT_APPID = "wx8ccef37dd7e159fc";
    private static final String WECHAT_SECRET = "dded56473f723ab7dfd61e94a07aad10";

    @Autowired
    public UserServiceImpl(UserMapper userMapper, RedisService redisService, RestTemplate restTemplate) {
        this.userMapper = userMapper;
        this.redisService = redisService;
        this.restTemplate = restTemplate;
    }

    /**
     * 登录
     *
     * @param loginVo
     */
    @Override
    public void login(LoginDTO loginVo, HttpServletResponse response) {
        if (null == loginVo) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
//            throw new GlobalException(CodeMsg.PASSWORD_MOBILE_ERROR);
        }
//        UserEntity userEntity = userMapper.findByMobile(mobile);
        UserEntity userEntity = null;
        if (null == userEntity) {
//            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
//        String dbPass = PasswordUtil.dbToPassword(password, userEntity.getSalt());
//        if (!dbPass.equals(userEntity.getPassword())) {
//            throw new GlobalException(CodeMsg.PASSWORD_MOBILE_ERROR);
//        }
        String token = UuidUtil.uuid();
        UserDTO userVo = new UserDTO();
        BeanUtils.copyProperties(userEntity, userVo);
        addCookies(response, userVo, token, false);
    }

    /**
     * 注册
     *
     * @param user
     */
    @Override
    public void register(UserEntity user, HttpServletResponse response) {
//        UserEntity userEntity = userMapper.findByMobile(user.getMobile());
        UserEntity userEntity = null;
        if (null != userEntity) {
//            throw new GlobalException(CodeMsg.MOBILE_EXIST);
        }
        String salt = UuidUtil.uuid();
//        String dbPass = PasswordUtil.dbToPassword(user.getPassword(), salt);
//        user.setPassword(dbPass);
//        userMapper.add(user);
        String token = UuidUtil.uuid();
        UserDTO userVo = new UserDTO();
        BeanUtils.copyProperties(user, userVo);
        userVo.setId(user.getId());
        addCookies(response, userVo, token);
    }

    /**
     * 根据token获取用户信息
     *
     * @param response
     * @param token
     * @return
     */
    @Override
    public UserDTO getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        UserDTO user = redisService.get(UserKey.token, token, UserDTO.class);
        if (null != user) {
            addCookies(response, user, token);
        }
        return user;
    }

    /**
     * 根据token获取用户信息
     *
     * @param response
     * @param token
     * @return
     */
    @Override
    public Long delByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return redisService.del(UserKey.token, token);
    }

    /**
     * 微信登录
     *
     * @param code 唯一码
     */
    @Override
    public SessionDto wechatLogin(HttpServletResponse response, String code, UserEntity userEntity) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" +
                WECHAT_APPID + "&secret=" + WECHAT_SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        WechatLoginDto wechatLogin = restTemplate.getForObject(url, WechatLoginDto.class);
        if (null == wechatLogin || StringUtils.isBlank(wechatLogin.getOpenid())) {
            throw new GlobalException(CodeMsg.WECHAT_GET_OPENID_ERROR);
        }
        UserDTO userDTO = this.userMapper.selectByOpenid(wechatLogin.getOpenid());
        if (null == userDTO) {
            userDTO = new UserDTO();
            userEntity.setCreateAt(System.currentTimeMillis());
            userEntity.setOpenid(wechatLogin.getOpenid());
            this.userMapper.insert(userEntity);
            BeanUtils.copyProperties(userEntity, userDTO);
        }
        String token = UuidUtil.uuid();
        addCookies(response, userDTO, token, false);
        return SessionDto.builder().token(token).build();
    }

    @Override
    public UserEntity selectById(Long id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    private void addCookies(HttpServletResponse response, UserDTO user, String token) {
        addCookies(response, user, token, false);
    }

    private void addCookies(HttpServletResponse response, UserDTO user, String token, boolean b) {
        if (b) {
            redisService.set(UserKey.tokenSeven, token, user);
        }
        redisService.set(UserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME, token);
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }


}
