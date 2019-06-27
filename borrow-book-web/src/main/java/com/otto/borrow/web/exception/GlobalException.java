package com.otto.borrow.web.exception;
/**
 * Project Name：seckilling
 * File Name：GlobalException
 * Package Name：com.seckilling.exception
 * Date：2018/8/6 23:36
 */


import com.otto.borrow.web.result.CodeMsg;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.exception
 * @ClassName GlobalException
 * @date 2018/8/6 23:36
 */
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 2576266617279879049L;

    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
