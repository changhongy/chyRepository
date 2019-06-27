/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * hui.zhang SessionDto
 *
 * @author hui.zhang
 * @since 2019-05-25 18:02
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionDto {

    private String token;
}