/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.service;

import com.otto.borrow.web.dto.BookBorrowDto;
import com.otto.borrow.web.dto.BookListDto;
import com.otto.borrow.web.dto.req.BookListReqDto;
import com.otto.borrow.web.dto.req.CommonReqDto;
import com.otto.borrow.web.dto.rsp.DataHomeRspDTO;
import com.otto.borrow.web.entity.BorBookEntity;

import java.util.List;
import java.util.Set;

/**
 * hui.zhang BookService
 *
 * @author hui.zhang
 * @since 2019-05-12 15:39
 */
public interface BookService {

    /**
     * 获取图书列表
     * @param bookListReq
     * @return 结果集合
     */
    List<BookListDto> getListByParam(BookListReqDto bookListReq);

    /**
     * 通过主键ID获取信息
     * @param id
     * @return
     */
    BorBookEntity getById(Long id);

    List<BookBorrowDto> getByUserIdAndBookId(Long userId, Long bookId);

    /**
     * 获取首页展示书籍列表
     * @param pages
     * @return
     */
    List<DataHomeRspDTO> dataHome(CommonReqDto pages);

    /**
     * 获取轮播图
     * @return
     */
    List<BookListDto> cycleImgs();

    int destockBook(Long bookId);

}