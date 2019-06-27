/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * hui.zhang BookListDto
 *
 * @author hui.zhang
 * @since 2019-05-12 15:41
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookListDto {

    private Long id;

    private String bookName;

    private String headImgUrl;

    private String authorName;

    private Integer category;

    private String categoryName;

    private String content;

    private Integer totalNum;

    @JsonIgnore
    private Integer isCycle;

    @JsonIgnore
    private Long createAt;
}