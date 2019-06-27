package com.otto.borrow.web.exception;
/**
 * Project Name：seckilling
 * File Name：GlobalExceptionHandle
 * Package Name：com.seckilling.exception
 * Date：2018/8/6 23:37
 */

import com.otto.borrow.web.result.CodeMsg;
import com.otto.borrow.web.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.exception
 * @ClassName GlobalExceptionHandle
 * @date 2018/8/6 23:37
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        if (e instanceof GlobalException) {
            GlobalException globalException = (GlobalException) e;
            return Result.error(globalException.getCm());
        } else if (e instanceof BindException) {
            BindException bindException = (BindException) e;
            List<ObjectError> errors = bindException.getAllErrors();
            ObjectError oe = errors.get(0);
            String msg = oe.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
