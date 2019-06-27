/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.mapper;

import com.otto.borrow.web.entity.AddressEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * hui.zhang AddressMapper
 *
 * @author hui.zhang
 * @since 2019-05-25 21:58
 */
@Mapper
@Repository
public interface AddressMapper extends BaseMapper<AddressEntity> {


    List<AddressEntity> findAll(@Param("userId") Long userId);

    void updateIsDefault(@Param("isDefault") Integer isDefault,
                         @Param("userId") Long userId);

    AddressEntity findDefault(@Param("userId") Long userId);

    int deleteById(@Param("id") Long id);
}