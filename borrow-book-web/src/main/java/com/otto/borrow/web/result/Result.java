package com.otto.borrow.web.result;
/**
 * Project Name：seckilling
 * File Name：Result
 * Package Name：com.seckilling.result
 * Date：2018/8/5 00:42
 */

import lombok.Data;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.result
 * @ClassName Result
 * @date 2018/8/5 00:42
 */
@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    public static <T> Result<T> error(CodeMsg cm) {
        return new Result<>(cm);
    }

    private Result(T data) {
        this.data = data;
        this.code = 200;
        this.msg = "success";
    }

    private Result(CodeMsg cm) {
        if (null == cm) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }
}
