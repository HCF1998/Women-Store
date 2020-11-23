package com.womenstore.hcf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.womenstore.hcf.dao.product.ProductMapper;
import com.womenstore.hcf.entity.product.Product;
import com.womenstore.hcf.service.IProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
        implements IProductService {

}
