/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.dto.rsp;

import com.otto.borrow.web.dto.BookListDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * hui.zhang DataHomeRspDTO
 *
 * @author hui.zhang
 * @since 2019-05-16 21:20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataHomeRspDTO implements Serializable {

    private static final long serialVersionUID = 5859862237597077988L;
    private Long categoryId;

    private String categoryName;

    private List<BookListDto> books;
}