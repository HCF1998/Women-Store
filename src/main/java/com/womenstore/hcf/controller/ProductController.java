package com.womenstore.hcf.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.womenstore.hcf.dao.product.ProductMapper;
import com.womenstore.hcf.entity.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @RequestMapping("/findAll")
    public JSONObject findAll(){
        List<Product> productList = productMapper.selectList(null);
        productList.forEach(product -> System.out.println());
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(productList);
        jsonObject.put("productList",productList);
        return jsonObject;
    }


}
