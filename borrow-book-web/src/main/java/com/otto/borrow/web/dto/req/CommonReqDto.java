/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.dto.req;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * hui.zhang CommonReqDto
 *
 * @author hui.zhang
 * @since 2019-05-12 16:52
 */
@Data
public class CommonReqDto {
//    总条数
    private int offset;
//    每页总条数
    private int limit;

    public int getPageNum() {
//        三目运算符
        return limit == 0 ? 0 : offset/limit + 1;
    }

    public int getPageSize() {
        return limit;
    }
}