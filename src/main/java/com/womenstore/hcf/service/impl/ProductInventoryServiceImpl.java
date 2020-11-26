package com.womenstore.hcf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.womenstore.hcf.dao.product.ProductinventorysMapper;
import com.womenstore.hcf.entity.product.Productinventorys;
import com.womenstore.hcf.service.IProductInventoryService;
import org.springframework.stereotype.Service;

@Service
public class ProductInventoryServiceImpl extends ServiceImpl<ProductinventorysMapper,
        Productinventorys> implements IProductInventoryService {
}
