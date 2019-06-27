/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.dto.req;

import lombok.Data;

/**
 * hui.zhang WechatLoginDto
 *
 * @author hui.zhang
 * @since 2019-05-25 15:36
 */
@Data
public class WechatLoginDto {

    private String openid;

    private String session_key;

    private String unionid;

    private Integer errcode;

    private String errmsg;

    public WechatLoginDto() {
    }
}