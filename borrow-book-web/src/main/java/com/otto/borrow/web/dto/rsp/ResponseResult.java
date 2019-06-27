/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.dto.rsp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * hui.zhang ResponseResult
 *
 * @author hui.zhang
 * @since 2019-05-12 16:50
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {

    private int offset;

    private int limit;

    private String order;

    private long total;

    private List<T> rows;
}