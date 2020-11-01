package com.womenstore.hcf.dao.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.womenstore.hcf.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
