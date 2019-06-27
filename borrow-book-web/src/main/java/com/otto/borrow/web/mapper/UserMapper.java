package com.otto.borrow.web.mapper;
/**
 * Project Name：seckilling
 * File Name：UserMapper
 * Package Name：com.seckilling.dao
 * Date：2018/8/4 22:31
 */
import com.otto.borrow.web.dto.UserDTO;
import com.otto.borrow.web.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.dao
 * @ClassName UserMapper
 * @date 2018/8/4 22:31
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    UserDTO selectByOpenid(String openid);
}
