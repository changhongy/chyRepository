/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.dto;

import lombok.Data;

/**
 * hui.zhang BookBorrowNumberDto
 *
 * @author hui.zhang
 * @since 2019-05-27 23:04
 */
@Data
public class BookBorrowNumberDto {

    private Long bookId;

    private Integer bookNum;

    public BookBorrowNumberDto() {
    }
}