/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.controller;

import com.otto.borrow.web.dto.SessionDto;
import com.otto.borrow.web.dto.UserDTO;
import com.otto.borrow.web.dto.req.WechatLoginDto;
import com.otto.borrow.web.entity.UserEntity;
import com.otto.borrow.web.result.Result;
import com.otto.borrow.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * hui.zhang LoginController
 *
 * @author hui.zhang
 * @since 2019-05-25 15:05
 */
@RestController
@RequestMapping(value = "/borrow")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/wechat/login", method = RequestMethod.GET)
    public Result<SessionDto> wechatLogin(HttpServletResponse response, @RequestParam String code,
                                          @RequestParam String avatarUrl,
                                          @RequestParam String city,
                                          @RequestParam String country,
                                          @RequestParam Integer gender,
                                          @RequestParam String language,
                                          @RequestParam String nickName,
                                          @RequestParam String province) {
        UserEntity userEntity = UserEntity.builder()
                .avatarUrl(avatarUrl)
                .city(city)
                .gender(gender)
                .language(language)
                .nickName(nickName)
                .province(province)
                .country(country)
                .isVip(0)
                .build();
        SessionDto session =  this.userService.wechatLogin(response, code, userEntity);
        return Result.success(session);
    }
}