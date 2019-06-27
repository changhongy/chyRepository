/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.dto.req;

import lombok.*;

/**
 * hui.zhang BookListReqDto
 *
 * @author hui.zhang
 * @since 2019-05-12 16:48
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BookListReqDto extends CommonReqDto {

    public BookListReqDto() {
    }

    private Long category;

    private String bookName;

    private Integer isCycle;
}