package com.womenstore.hcf.dao.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.womenstore.hcf.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}