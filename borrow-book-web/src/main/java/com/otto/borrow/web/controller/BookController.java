/*
 * Copyright (c) 2016, BITMAIN and/or its affiliates. All rights reserved.
 * BITMAIN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.otto.borrow.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.otto.borrow.web.dto.BookBorrowDto;
import com.otto.borrow.web.dto.BookInfoDto;
import com.otto.borrow.web.dto.BookListDto;
import com.otto.borrow.web.dto.UserDTO;
import com.otto.borrow.web.dto.req.BookListReqDto;
import com.otto.borrow.web.dto.req.CommonReqDto;
import com.otto.borrow.web.dto.rsp.DataHomeRspDTO;
import com.otto.borrow.web.dto.rsp.ResponseResult;
import com.otto.borrow.web.entity.BorBookEntity;
import com.otto.borrow.web.exception.GlobalException;
import com.otto.borrow.web.result.CodeMsg;
import com.otto.borrow.web.result.Result;
import com.otto.borrow.web.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;

/**
 * hui.zhang BookController
 *
 * @author hui.zhang
 * @since 2019-05-12 12:20
 */
@RestController
@RequestMapping(value = "/borrow/book")
public class BookController {

    @Value("${pic.root.path}")
    private String rootPath;

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 获取列表
     * @param bookListReq 参数
     * @return
     */
    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public ResponseResult<BookListDto> books(BookListReqDto bookListReq, UserDTO userDTO){
//        this.userExist(userDTO);
        Page<BookListDto> page = PageHelper.startPage(bookListReq.getPageNum(),bookListReq.getPageSize())
                .doSelectPage(() -> this.bookService.getListByParam(bookListReq));
        ResponseResult<BookListDto> result = new ResponseResult<>();
        result.setRows(page.getResult());
        result.setTotal(page.getTotal());
        result.setLimit(bookListReq.getLimit());
        result.setOffset(bookListReq.getOffset());
        return result;
    }

    @RequestMapping(value = "/hot/category", method = RequestMethod.GET)
    public Result<List<DataHomeRspDTO>> booksHome(CommonReqDto pages, UserDTO userDTO) {
        this.userExist(userDTO);
        List<DataHomeRspDTO> list = this.bookService.dataHome(pages);
        return Result.success(list);
    }

    @RequestMapping(value = "/cycle/img", method = RequestMethod.GET)
    public Result<List<BookListDto>> cycleImg(UserDTO userDTO) {
        this.userExist(userDTO);
        List<BookListDto> list = this.bookService.cycleImgs();
        return Result.success(list);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<BookInfoDto> bookInfo(@PathVariable("id") Long id, UserDTO userDTO) {
        this.userExist(userDTO);
        BorBookEntity book = this.bookService.getById(id);
        BookInfoDto bookInfoDto = new BookInfoDto();
        BeanUtils.copyProperties(book, bookInfoDto);
        List<BookBorrowDto> bookBorrowList = this.bookService.getByUserIdAndBookId(userDTO.getId(), id);
        if (null != bookBorrowList && bookBorrowList.size() >= 4) {
            bookInfoDto.setIsBorrowed(true);
        } else {
            bookInfoDto.setIsBorrowed(false);
        }
        return Result.success(bookInfoDto);
    }

    @RequestMapping("/picture/show/{path}/{catalog}/{filename:.+}")
    public void show(@PathVariable("path") String path,
                     @PathVariable("catalog") String catalog,
                     @PathVariable("filename") String filename,
                     HttpServletRequest request,
                     HttpServletResponse response) {

        String fileId = catalog + "/" + filename;
        String prefix = fileId.substring(fileId.lastIndexOf(".") + 1)
                .toLowerCase();

        response.reset();

        if("jpg".equals(prefix)) {
            response.setContentType("image/jpeg");
        } else {
            response.setContentType("image/" + prefix);
        }

        String picPath = rootPath + "/" + path +"/" + fileId;


        File file = new File(picPath);

        OutputStream out = null;
        FileInputStream in = null;
        try {
            out = response.getOutputStream();
            in = new FileInputStream(file);
            response.setContentLength(in.available());

            byte[] b = new byte[1024];
            int i = 0;

            while ((i = in.read(b)) > 0) {
                out.write(b, 0, i);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
        }
    }

    private void userExist(UserDTO user) {
        if (null == user) {
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
    }
}