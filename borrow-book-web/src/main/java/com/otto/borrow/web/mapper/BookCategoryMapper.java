/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.mapper;

import com.otto.borrow.web.entity.BorBookCategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * hui.zhang BookCategoryMapper
 *
 * @author hui.zhang
 * @since 2019-05-13 14:33
 */
@Mapper
@Repository
public interface BookCategoryMapper extends BaseMapper<BorBookCategoryEntity> {

    /**
     * 获取字典列表
     * @param dicName 名称
     * @param status 状态
     * @return
     */
    List<BorBookCategoryEntity> selectByParam(@Param("dicName") String dicName,
                                              @Param("status") Integer status);

    /**
     * 根据ID获取信息
     * @param id
     * @return
     */
    BorBookCategoryEntity selectById(@Param("id") Long id);
}