package com.womenstore.hcf.controller.admin.product;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.womenstore.hcf.dao.product.ProductMapper;
import com.womenstore.hcf.entity.product.Product;
import com.womenstore.hcf.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/findAll")
    public Result findAll(){
        List<Product> productList = productMapper.selectList(null);
        return new Result(productList);
    }

}
