/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.otto.borrow.web.dto.BookBorrowDto;
import com.otto.borrow.web.dto.BookListDto;
import com.otto.borrow.web.dto.req.BookListReqDto;
import com.otto.borrow.web.dto.req.CommonReqDto;
import com.otto.borrow.web.dto.rsp.DataHomeRspDTO;
import com.otto.borrow.web.entity.BorBookCategoryEntity;
import com.otto.borrow.web.entity.BorBookEntity;
import com.otto.borrow.web.mapper.BookBorrowOrderMapper;
import com.otto.borrow.web.mapper.BookCategoryMapper;
import com.otto.borrow.web.mapper.BookMapper;
import com.otto.borrow.web.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * hui.zhang BookServiceImpl
 *
 * @author hui.zhang
 * @since 2019-05-12 15:39
 */
@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;

    private final BookCategoryMapper bookCategoryMapper;

    private final BookBorrowOrderMapper bookBorrowOrderMapper;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper, BookCategoryMapper bookCategoryMapper,
                           BookBorrowOrderMapper bookBorrowOrderMapper) {
        this.bookMapper = bookMapper;
        this.bookCategoryMapper = bookCategoryMapper;
        this.bookBorrowOrderMapper = bookBorrowOrderMapper;
    }


    /**
     * 获取图书列表
     *
     * @param bookListReq
     * @return
     */
    @Override
    public List<BookListDto> getListByParam(BookListReqDto bookListReq) {
        return this.bookMapper.selectByList(bookListReq);
    }

    @Override
    public BorBookEntity getById(Long id) {
        return bookMapper.selectById(id);
    }

    @Override
    public List<BookBorrowDto> getByUserIdAndBookId(Long userId, Long bookId) {
        return this.bookBorrowOrderMapper.selectByUserIdAndBookId(userId, bookId);
    }

    /**
     * 获取首页展示书籍列表
     *
     * @return
     */
    @Override
    public List<DataHomeRspDTO> dataHome(CommonReqDto pages) {
        List<DataHomeRspDTO> list = new ArrayList<>();
        List<BorBookCategoryEntity> categoryEntity = this.bookCategoryMapper.selectByParam(null, 0);
        if (null == categoryEntity || categoryEntity.isEmpty()) {
            return null;
        }
        categoryEntity.forEach(c -> {
            BookListReqDto bookListReqDto = new BookListReqDto();
            bookListReqDto.setLimit(pages.getLimit());
            bookListReqDto.setOffset(pages.getOffset());
            bookListReqDto.setCategory(c.getId());
            Page<BookListDto> page = PageHelper.startPage(pages.getPageNum(), pages.getPageSize())
                    .doSelectPage(()->this.bookMapper.selectByList(bookListReqDto));
            if (null != page && !page.getResult().isEmpty()) {
                DataHomeRspDTO rspDTO = DataHomeRspDTO.builder()
                        .categoryId(c.getId())
                        .categoryName(c.getDicName())
                        .books(page.getResult())
                        .build();
                list.add(rspDTO);
            }
        });
        return list.isEmpty() ? null : list;
    }

    /**
     * 获取轮播图
     *
     * @return
     */
    @Override
    public List<BookListDto> cycleImgs() {
        BookListReqDto bookListReqDto = new BookListReqDto();
        bookListReqDto.setIsCycle(1);
        return this.bookMapper.selectByList(bookListReqDto);
    }

    @Override
    public int destockBook(Long bookId) {
        return this.bookMapper.updateNum(bookId);
    }
}