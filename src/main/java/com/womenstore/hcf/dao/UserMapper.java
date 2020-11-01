package com.womenstore.hcf.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.womenstore.hcf.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


//@Mapper
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
