package com.otto.borrow.web.result;
/**
 * Project Name：seckilling
 * File Name：CodeMsg
 * Package Name：com.seckilling.result
 * Date：2018/8/5 00:45
 */

import lombok.Data;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.result
 * @ClassName CodeMsg
 * @date 2018/8/5 00:45
 */
@Data
public class CodeMsg {
    private String msg;
    private int code;

    /**
     * 通用
     */
    public static CodeMsg SUCCESS = new CodeMsg(200, "success");

    /**
     * 通用错误码
     */
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "%s");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg SERVER_REQUEST_TOO_OFTEN = new CodeMsg(500102, "请求太频繁，请稍后再试");
    public static CodeMsg SERVER_ILLEGAL_PARAM = new CodeMsg(500103, "非法的参数");

    /**
     * 登录模块 5002xxx
     */
    public static CodeMsg WECHAT_GET_OPENID_ERROR = new CodeMsg(500200, "获取openID异常");
    public static CodeMsg WECHAT_USER_NOT_EXIST = new CodeMsg(500201, "用户不存在");
    public static CodeMsg SESSION_ERROR = new CodeMsg(500203, "重新登录");

    /**
     * 借阅
     */
    public static CodeMsg BORROW_BOOK_NOT_ENOUGH = new CodeMsg(500300, "本书库存不足");
    public static CodeMsg BORROW_ADDRESS_DEFAULT_ERROR = new CodeMsg(500301, "请设置默认地址");
    public static CodeMsg BORROW_ONLY_FOUR = new CodeMsg(500302, "该书最多能借阅4本");



    public CodeMsg(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String msg = String.format(this.msg, args);
        return new CodeMsg(code, msg);
    }

    @Override
    public String toString() {
        return "CodeMsg{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }
}
