/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import java.util.Arrays;

/**
 * hui.zhang CustomConnectionKeepAliveStrategy
 *
 * RestTemplate 配置
 *
 * @author hui.zhang
 * @since 2019-03-25 19:51
 */
public class CustomConnectionKeepAliveStrategy implements ConnectionKeepAliveStrategy {

    private static final long DEFAULT_SECONDS = 30;

    @Override
    public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        return Arrays.stream(httpResponse.getHeaders(HTTP.CONN_KEEP_ALIVE))
                .filter(h -> StringUtils.equalsIgnoreCase(h.getName(), "timeout")
                && StringUtils.isNumeric(h.getValue()))
                .findFirst()
                .map(h -> NumberUtils.toLong(h.getValue(), DEFAULT_SECONDS))
                .orElse(DEFAULT_SECONDS) * 1000;
    }
}