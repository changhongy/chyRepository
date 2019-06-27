package com.otto.borrow.web.dto;
/**
 * Project Name：seckilling
 * File Name：LoginDTO
 * Package Name：com.seckilling.vo
 * Date：2018/8/7 23:25
 */

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.vo
 * @ClassName LoginDTO
 * @date 2018/8/7 23:25
 */
@Data
public class LoginDTO {

    @NotNull
    private String mobile;

    @NotNull
    private String password;

    private Boolean remember;
}
