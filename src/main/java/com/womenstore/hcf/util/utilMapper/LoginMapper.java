package com.womenstore.hcf.util.utilMapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.womenstore.hcf.util.entity.Login;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginMapper extends BaseMapper<Login> {
}
