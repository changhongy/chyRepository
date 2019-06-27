/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.dto;

import com.otto.borrow.web.validation.IsMobile;
import lombok.Data;

/**
 * hui.zhang BookBorrowDto
 *
 * @author hui.zhang
 * @since 2019-05-27 23:01
 */
@Data
public class BookBorrowDto {

    private Long id;

    private Long bookId;

    private String bookName;

    private Long userId;

    private String nickName;

    private String address;

    private Long startTime;

    private Long endTime;

    private Integer status;

    private String statusName;

    private String mobile;

    private String linkMan;

    public BookBorrowDto() {
    }
}