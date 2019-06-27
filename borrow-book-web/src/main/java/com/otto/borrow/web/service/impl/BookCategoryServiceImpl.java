/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.service.impl;

import com.otto.borrow.web.entity.BorBookCategoryEntity;
import com.otto.borrow.web.mapper.BookCategoryMapper;
import com.otto.borrow.web.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * hui.zhang BookCategoryServiceImpl
 *
 * @author hui.zhang
 * @since 2019-05-13 14:43
 */
@Service
public class BookCategoryServiceImpl implements BookCategoryService {

    private final BookCategoryMapper bookCategoryMapper;

    @Autowired
    public BookCategoryServiceImpl(BookCategoryMapper bookCategoryMapper) {
        this.bookCategoryMapper = bookCategoryMapper;
    }

    /**
     * 获取列表
     *
     * @param dicName 字典名
     * @param status  状态 0：启用  1：禁用
     * @return
     */
    @Override
    public List<BorBookCategoryEntity> findListByParam(String dicName, Integer status) {
        return this.bookCategoryMapper.selectByParam(dicName, status);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public BorBookCategoryEntity findById(Long id) {
        return this.bookCategoryMapper.selectByPrimaryKey(id);
//        return this.bookCategoryMapper.selectById(id);
    }
}