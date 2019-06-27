/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.dto;

import lombok.Data;

/**
 * hui.zhang BookInfoDto
 *
 * @author hui.zhang
 * @since 2019-05-30 01:02
 */
@Data
public class BookInfoDto {
    private Long id;

    private String bookName;

    private String headImgUrl;

    private String authorName;

    private String categoryName;

    private String content;

    private Integer totalNum;

    private Boolean isBorrowed;

    public BookInfoDto() {
    }
}