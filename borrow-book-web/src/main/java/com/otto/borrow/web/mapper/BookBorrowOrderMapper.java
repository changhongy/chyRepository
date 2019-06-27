/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.mapper;

import com.otto.borrow.web.dto.BookBorrowDto;
import com.otto.borrow.web.dto.BookBorrowNumberDto;
import com.otto.borrow.web.entity.BorBookBorrowOrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * hui.zhang BookBorrowOrderMapper
 *
 * @author hui.zhang
 * @since 2019-05-27 22:42
 */
@Mapper
@Repository
public interface BookBorrowOrderMapper extends BaseMapper<BorBookBorrowOrderEntity> {

    List<BookBorrowNumberDto> selectBookNumList();

    List<BookBorrowDto> selectAllList(Long userId);

    /**
     * 获取正在借阅书籍列表
     * @param userId
     * @return
     */
    List<BookBorrowDto> selectBorrowed(@Param("userId") Long userId);

    List<BookBorrowDto> selectByUserIdAndBookId(@Param("userId") Long userId,
                                          @Param("bookId") Long bookId);

    List<BookBorrowDto> findByStatus(@Param("status") List<Integer> status,
                                     @Param("userId") Long userId);

    BookBorrowDto selectById(@Param("id") Long id);
}