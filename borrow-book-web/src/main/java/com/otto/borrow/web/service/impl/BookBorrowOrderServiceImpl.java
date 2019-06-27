/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.service.impl;

import com.otto.borrow.web.dto.BookBorrowDto;
import com.otto.borrow.web.dto.BookBorrowNumberDto;
import com.otto.borrow.web.entity.AddressEntity;
import com.otto.borrow.web.entity.BorBookBorrowOrderEntity;
import com.otto.borrow.web.entity.BorBookEntity;
import com.otto.borrow.web.entity.UserEntity;
import com.otto.borrow.web.exception.GlobalException;
import com.otto.borrow.web.mapper.BookBorrowOrderMapper;
import com.otto.borrow.web.result.CodeMsg;
import com.otto.borrow.web.service.AddressService;
import com.otto.borrow.web.service.BookBorrowOrderService;
import com.otto.borrow.web.service.BookService;
import com.otto.borrow.web.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * hui.zhang BookBorrowOrderServiceImpl
 *
 * @author hui.zhang
 * @since 2019-05-27 23:27
 */
@Service
public class BookBorrowOrderServiceImpl implements BookBorrowOrderService {

    private final BookBorrowOrderMapper bookBorrowOrderMapper;

    private final UserService userService;

    private final BookService bookService;

    private final AddressService addressService;

    public BookBorrowOrderServiceImpl(BookBorrowOrderMapper bookBorrowOrderMapper,
                                      UserService userService, BookService bookService,
                                      AddressService addressService) {
        this.bookBorrowOrderMapper = bookBorrowOrderMapper;
        this.userService = userService;
        this.bookService = bookService;
        this.addressService = addressService;
    }

    @Override
    public List<BookBorrowNumberDto> findBookNumList() {
        return this.bookBorrowOrderMapper.selectBookNumList();
    }

    @Override
    public List<BookBorrowDto> findBorrowOrderByUserId(Long userId) {
        return this.bookBorrowOrderMapper.selectAllList(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bookBorrow(Long userId, Long bookId) {
        AddressEntity addressEntity = this.addressService.findDefault(userId);
        if (addressEntity == null) {
            throw new GlobalException(CodeMsg.BORROW_ADDRESS_DEFAULT_ERROR);
        }
        List<BookBorrowDto> bookBorrowList = this.bookBorrowOrderMapper.selectByUserIdAndBookId(userId, bookId);
        if (bookBorrowList != null && bookBorrowList.size() >= 4) {
            throw new GlobalException(CodeMsg.BORROW_ONLY_FOUR);
        }
        //图书减库存
        int res = this.bookService.destockBook(bookId);
        if (res == 0) {
            throw new GlobalException(CodeMsg.BORROW_BOOK_NOT_ENOUGH);
        }
        UserEntity userEntity = this.userService.selectById(userId);
        BorBookEntity bookEntity = this.bookService.getById(bookId);
        BorBookBorrowOrderEntity bookBorrowOrderEntity = new BorBookBorrowOrderEntity();
        bookBorrowOrderEntity.setAddressId(addressEntity.getId());
        bookBorrowOrderEntity.setBookId(bookEntity.getId());
        bookBorrowOrderEntity.setBookName(bookEntity.getBookName());
        bookBorrowOrderEntity.setNickName(userEntity.getNickName());
        bookBorrowOrderEntity.setCreateAt(System.currentTimeMillis());
        bookBorrowOrderEntity.setStartTime(System.currentTimeMillis());
        bookBorrowOrderEntity.setStatus(3);
        bookBorrowOrderEntity.setUserId(userId);
        bookBorrowOrderEntity.setUpdateAt(System.currentTimeMillis());
        this.bookBorrowOrderMapper.insert(bookBorrowOrderEntity);
    }

    @Override
    public List<BookBorrowDto> findByStatus(List<Integer> status, Long userId) {
        List<BookBorrowDto> list =  this.bookBorrowOrderMapper.findByStatus(status, userId);
        list.forEach(b-> {
            if (b.getStatus() == 0) {
                b.setStatusName("借阅中");
            } else if (b.getStatus() == 1) {
                b.setStatusName("已归还");
            } else if (b.getStatus() == 2) {
                b.setStatusName("丢失");
            } else if (b.getStatus() == 3) {
                b.setStatusName("待审核");
            } else if (b.getStatus() == 4) {
                b.setStatusName("驳回");
            }
        });
        return list;
    }

    @Override
    public BookBorrowDto findById(Long id) {
        return this.bookBorrowOrderMapper.selectById(id);
    }
}