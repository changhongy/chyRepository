/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.service;

import com.otto.borrow.web.entity.BorBookCategoryEntity;

import java.util.List;

/**
 * hui.zhang BookCategoryService
 *
 * @author hui.zhang
 * @since 2019-05-13 14:43
 */
public interface BookCategoryService {

    /**
     * 获取列表
     * @param dicName 字典名
     * @param status 状态 0：启用  1：禁用
     * @return
     */
    List<BorBookCategoryEntity> findListByParam(String dicName, Integer status);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    BorBookCategoryEntity findById(Long id);
}