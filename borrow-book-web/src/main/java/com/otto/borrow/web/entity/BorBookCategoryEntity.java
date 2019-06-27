/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * hui.zhang BorBookCategoryEntity
 *
 * @author hui.zhang
 * @since 2019-05-13 14:30
 */
@Data
@Builder
@Table(name = "bor_book_category")
@AllArgsConstructor
@NoArgsConstructor
public class BorBookCategoryEntity {

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Long id;

    private String dicName;

    private Integer dicOrder;

    private Integer status;

    private Long createAt;
}