/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * hui.zhang BorBookBorrowOrderEntity
 *
 * @author hui.zhang
 * @since 2019-05-27 22:53
 */
@Entity
@Data
@Table(name = "bor_book_borrow_order")
public class BorBookBorrowOrderEntity implements Serializable {
    private static final long serialVersionUID = 1962804505126622621L;

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Long id;

    private Long bookId;

    private String bookName;

    private Long userId;

    private String nickName;

    private Long addressId;

    private Long endTime;

    private Long startTime;

    /**
     * 借阅状态: 0:借阅中 1：已归还 2：丢失
     */
    private Integer status;

    private Long createAt;

    private Long updateAt;

    public BorBookBorrowOrderEntity() {
    }
}