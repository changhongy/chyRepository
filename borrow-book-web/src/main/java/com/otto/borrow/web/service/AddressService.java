/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.service;

import com.otto.borrow.web.entity.AddressEntity;

import java.util.List;

/**
 * hui.zhang AddressService
 *
 * @author hui.zhang
 * @since 2019-05-25 21:59
 */
public interface AddressService {

    void insert(AddressEntity address, Long userId);

    List<AddressEntity> finaAll(Long userId);

    AddressEntity findById(Long id);

    AddressEntity findDefault(Long id);

    void update(AddressEntity address, Long userId);

    int deleteById(Long id);
}