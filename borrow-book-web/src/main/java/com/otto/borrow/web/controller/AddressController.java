/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.controller;

import com.otto.borrow.web.dto.UserDTO;
import com.otto.borrow.web.entity.AddressEntity;
import com.otto.borrow.web.exception.GlobalException;
import com.otto.borrow.web.result.CodeMsg;
import com.otto.borrow.web.result.Result;
import com.otto.borrow.web.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * hui.zhang AddressController
 *
 * @author hui.zhang
 * @since 2019-05-25 21:33
 */
@RestController
@RequestMapping(value = "/borrow/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result<Boolean> create(@RequestBody AddressEntity address, UserDTO userDTO) {
//        this.userExist(userDTO);
//        address.setUserId(userDTO.getId());
        address.setUserId(4L);
        this.addressService.insert(address, userDTO.getId());
        return Result.success(true);
    }

    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public Result<List<AddressEntity>> findAll(UserDTO userDTO) {
        this.userExist(userDTO);
        List<AddressEntity> list = this.addressService.finaAll(userDTO.getId());
        return Result.success(list);
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public Result<AddressEntity> findById(@PathVariable("id") Long id, UserDTO userDTO) {
        this.userExist(userDTO);
        AddressEntity addressEntity = this.addressService.findById(id);
        return Result.success(addressEntity);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Result<Boolean> update(@PathVariable("id") Long id, @RequestBody AddressEntity address,
                                  UserDTO userDTO) {
        this.userExist(userDTO);
        address.setUserId(userDTO.getId());
        address.setId(id);
        this.addressService.update(address, userDTO.getId());
        return Result.success(true);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public Result<Boolean> delete(UserDTO userDTO, @PathVariable("id") Long id) {
        this.userExist(userDTO);
        this.addressService.deleteById(id);
        return Result.success(true);
    }

    private void userExist(UserDTO user) {
        if (null == user) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
    }
}