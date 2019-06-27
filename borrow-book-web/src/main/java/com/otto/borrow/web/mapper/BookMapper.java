/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.mapper;

import com.otto.borrow.web.dto.BookListDto;
import com.otto.borrow.web.dto.req.BookListReqDto;
import com.otto.borrow.web.entity.BorBookEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * hui.zhang BookMapper
 *
 * @author hui.zhang
 * @since 2019-05-12 15:39
 */
@Mapper
@Repository
public interface BookMapper extends BaseMapper<BorBookEntity> {

    /**
     * 获取列表
     * @param bookListReq
     * @return
     */
    List<BookListDto> selectByList(@Param("book") BookListReqDto bookListReq);


    /**
     * 根据主键获取信息
     * @param id
     * @return
     */
    BorBookEntity selectById(@Param("id") Long id);

    Integer updateNum(Long bookId);

}