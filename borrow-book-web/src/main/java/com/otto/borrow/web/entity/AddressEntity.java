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

/**
 * hui.zhang AddressEntity
 *
 * @author hui.zhang
 * @since 2019-05-25 21:34
 */
@Data
@Entity
@Table(name = "bor_address")
public class AddressEntity {

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Long id;

    private Long userId;

    /**
     * 联系人
     */
    private String linkMan;

    private String mobile;

    private String address;

    private Integer isDefault;

    /**
     * 邮政编码
     */
    private String code;

    /**
     * 0:已删除 1：未删除
     */
    private Integer isDelete;

    private Long createAt;

    private Long updateAt;

    public AddressEntity() {
    }
}