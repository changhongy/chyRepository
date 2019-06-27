/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.service.impl;

import com.otto.borrow.web.entity.AddressEntity;
import com.otto.borrow.web.mapper.AddressMapper;
import com.otto.borrow.web.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * hui.zhang AddressServiceImpl
 *
 * @author hui.zhang
 * @since 2019-05-25 21:59
 */
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public void insert(AddressEntity address, Long userId) {
        address.setIsDefault(0);
        address.setIsDelete(0);
        address.setCreateAt(System.currentTimeMillis());
        address.setUpdateAt(System.currentTimeMillis());
        if (address.getIsDefault() == 1) {
            this.addressMapper.updateIsDefault(0, userId);
        }
        this.addressMapper.insert(address);
    }

    @Override
    public List<AddressEntity> finaAll(Long userId) {
        return this.addressMapper.findAll(userId);
    }

    @Override
    public AddressEntity findById(Long id) {
        return this.addressMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(AddressEntity address, Long userId) {
        AddressEntity old = this.addressMapper.selectByPrimaryKey(address.getId());
        address.setCreateAt(old.getCreateAt());
        address.setUpdateAt(System.currentTimeMillis());
        address.setIsDelete(old.getIsDelete());
        if (address.getIsDefault() == 1) {
            this.addressMapper.updateIsDefault(0, userId);
        }
        this.addressMapper.updateByPrimaryKey(address);
    }

    @Override
    public AddressEntity findDefault(Long id) {
        return this.addressMapper.findDefault(id);
    }

    @Override
    public int deleteById(Long id) {
        return this.addressMapper.deleteById(id);
    }
}