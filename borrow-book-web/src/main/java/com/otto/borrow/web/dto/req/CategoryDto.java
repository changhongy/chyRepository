/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.dto.req;

import lombok.*;

/**
 * hui.zhang CategoryDto
 *
 * @author hui.zhang
 * @since 2019-05-13 20:19
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CategoryDto extends CommonReqDto {
    public CategoryDto() {
    }

    private String dicName;

    private Integer status;
}