package com.otto.borrow.web.entity;
/**
 * Project Name：seckilling
 * File Name：UserEntity
 * Package Name：com.seckilling.entity
 * Date：2018/8/4 22:26
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 张辉
 * @Title：
 * @Description： t_user
 * @Package com.seckilling.entity
 * @ClassName UserEntity
 * @date 2018/8/4 22:26
 */
@Data
@Table(name = "bor_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1688311920787154734L;

    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Long id;

    private String nickName;

    private String mobile;

    private String email;

    private String openid;

    /**
     * 头像
     */
    private String avatarUrl;

    private String country;

    private String city;

    /**
     * 性别 1：男 2：女 0：未知
     */
    private Integer gender;

    /**
     * 省
     */
    private String province;

    /**
     * 用户语言
     */
    private String language;

    /**
     * 地址
     */
    private String address;

    /**
     * 是否是VIP
     */
    private Integer isVip;

    private Long createAt;
}
