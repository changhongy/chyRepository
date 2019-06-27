/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.service;

import com.otto.borrow.web.dto.BookBorrowDto;
import com.otto.borrow.web.dto.BookBorrowNumberDto;

import java.util.List;

/**
 * hui.zhang BookBorrowOrderService
 *
 * @author hui.zhang
 * @since 2019-05-27 23:27
 */
public interface BookBorrowOrderService {

    List<BookBorrowNumberDto> findBookNumList();

    List<BookBorrowDto> findBorrowOrderByUserId(Long userId);

    void bookBorrow(Long userId, Long bookId);


    List<BookBorrowDto> findByStatus(List<Integer> status, Long userId);

    BookBorrowDto findById(Long id);
}