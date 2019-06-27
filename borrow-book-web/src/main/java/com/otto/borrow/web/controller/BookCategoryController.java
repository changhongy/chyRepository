/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.otto.borrow.web.dto.UserDTO;
import com.otto.borrow.web.dto.req.CategoryDto;
import com.otto.borrow.web.dto.rsp.ResponseResult;
import com.otto.borrow.web.entity.BorBookCategoryEntity;
import com.otto.borrow.web.exception.GlobalException;
import com.otto.borrow.web.result.CodeMsg;
import com.otto.borrow.web.result.Result;
import com.otto.borrow.web.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * hui.zhang BookCategoryController
 *
 * @author hui.zhang
 * @since 2019-05-13 14:51
 */
@RestController
@RequestMapping(value = "/borrow/admin/category")
public class BookCategoryController {

    private final BookCategoryService bookCategoryService;

    @Autowired
    public BookCategoryController(BookCategoryService bookCategoryService) {
        this.bookCategoryService = bookCategoryService;
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<BorBookCategoryEntity> findAll(UserDTO userDTO,
                                                         @RequestBody CategoryDto categoryDto) {
        this.userExist(userDTO);
        Page<BorBookCategoryEntity> page = PageHelper.startPage(categoryDto.getPageNum(), categoryDto.getPageSize())
                .doSelectPage(() -> this.bookCategoryService.findListByParam(categoryDto.getDicName(), categoryDto.getStatus()));
        ResponseResult<BorBookCategoryEntity> res = new ResponseResult<>();
        res.setTotal(page.getTotal());
        res.setRows(page.getResult());
        return res;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<BorBookCategoryEntity>> getAll(UserDTO userDTO){
        this.userExist(userDTO);
        List<BorBookCategoryEntity> list = this.bookCategoryService.findListByParam(null, 0);
        return Result.success(list);
    }

    private void userExist(UserDTO user) {
        if (null == user) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
    }
}