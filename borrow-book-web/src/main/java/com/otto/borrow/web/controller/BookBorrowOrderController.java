/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.otto.borrow.web.dto.BookBorrowDto;
import com.otto.borrow.web.dto.UserDTO;
import com.otto.borrow.web.dto.req.CommonReqDto;
import com.otto.borrow.web.dto.rsp.ResponseResult;
import com.otto.borrow.web.exception.GlobalException;
import com.otto.borrow.web.result.CodeMsg;
import com.otto.borrow.web.result.Result;
import com.otto.borrow.web.service.BookBorrowOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * hui.zhang BookBorrowOrderController
 *
 * @author hui.zhang
 * @since 2019-05-27 23:32
 */
@RestController//带有responsebody的注解
@RequestMapping(value = "/borrow/order")
public class BookBorrowOrderController {

    private final BookBorrowOrderService bookBorrowOrderService;

    //构造器注入 带有final提高安全性
    public BookBorrowOrderController(BookBorrowOrderService bookBorrowOrderService) {
        this.bookBorrowOrderService = bookBorrowOrderService;
    }
//    @Autowired 注解注入
//    private BookBorrowOrderService bookBorrowOrderService;

//     书的分页
    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public ResponseResult<BookBorrowDto> findAll(UserDTO userDTO, CommonReqDto commonReqDto) {

//        this.userExist(userDTO);

        Page<BookBorrowDto> page = PageHelper.startPage(commonReqDto.getPageNum(), commonReqDto.getPageSize())
                .doSelectPage(()->this.bookBorrowOrderService.findBorrowOrderByUserId(userDTO.getId()));
        ResponseResult<BookBorrowDto> result = new ResponseResult<>();
        result.setRows(page.getResult());
        return result;
    }

//    为什么要使用post方法？？
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    public Result<Boolean> bookBorrow(UserDTO userDTO, @PathVariable("id") Long id) {
//        this.userExist(userDTO);
        this.bookBorrowOrderService.bookBorrow(userDTO.getId(), id);
        return Result.success(true);
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public Result<BookBorrowDto> findById(UserDTO userDTO, @PathVariable("id") Long id) {
//        this.userExist(userDTO);
        BookBorrowDto bookBorrowDto = this.bookBorrowOrderService.findById(id);
        return Result.success(bookBorrowDto);
    }

    /**
     * 历史记录
     * @param userDTO
     * @param commonReqDto
     * @return
     */
    @RequestMapping(value = "/borrowed", method = RequestMethod.GET)
    public Result<List<BookBorrowDto>> bookBorrowed(UserDTO userDTO, CommonReqDto commonReqDto){
        this.userExist(userDTO);
        Page<BookBorrowDto> page = PageHelper.startPage(commonReqDto.getPageNum(), commonReqDto.getPageSize())
                .doSelectPage(()->this.bookBorrowOrderService.findByStatus(Arrays.asList(1,2,4), userDTO.getId()));
        return Result.success(page.getResult());
    }

    /**
     * 借阅中记录
     * @param userDTO
     * @param commonReqDto
     * @return
     */
    @RequestMapping(value = "/borrowing", method = RequestMethod.GET)
    public Result<List<BookBorrowDto>> bookBorrowing(UserDTO userDTO, CommonReqDto commonReqDto){
//        this.userExist(userDTO);
        Page<BookBorrowDto> page = PageHelper.startPage(commonReqDto.getPageNum(), commonReqDto.getPageSize())
                .doSelectPage(()->this.bookBorrowOrderService.findByStatus(Arrays.asList(0,3), userDTO.getId()));
        return Result.success(page.getResult());
    }

    private void userExist(UserDTO user) {
        if (null == user) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
    }
}