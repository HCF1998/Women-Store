package com.womenstore.hcf.dao.category;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.womenstore.hcf.entity.category.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}