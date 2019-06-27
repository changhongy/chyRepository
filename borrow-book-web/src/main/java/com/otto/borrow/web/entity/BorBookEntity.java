/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * hui.zhang BorBookEntity
 *
 * @author hui.zhang
 * @since 2019-05-12 12:14
 */
@Data
@Builder
@Table(name = "bor_book")
@AllArgsConstructor
@NoArgsConstructor
public class BorBookEntity {

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Long id;

    private String bookName;

    private String headImgUrl;

    private String authorName;

    @JsonIgnore
    private Integer category;

    private String categoryName;

    private String content;

    private Integer totalNum;

    @JsonIgnore
    private Integer isCycle;

    @JsonIgnore
    private Long createAt;
}