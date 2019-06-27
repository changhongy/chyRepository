package com.otto.borrow.web.dto;
/**
 * Project Name：seckilling
 * File Name：UserDTO
 * Package Name：com.seckilling.vo
 * Date：2018/8/10 23:02
 */

import com.otto.borrow.web.entity.UserEntity;
import lombok.Data;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.vo
 * @ClassName UserDTO
 * @date 2018/8/10 23:02
 */
@Data
public class UserDTO {

    private Long id;

    private String email;

    private String nickName;

    private String mobile;

    private String address;

    public UserDTO() {
    }

    public UserDTO(UserEntity user) {
        this.id = user.getId();
        this.nickName = user.getNickName();
        this.email = user.getEmail();
        this.mobile = user.getMobile();
    }
}
