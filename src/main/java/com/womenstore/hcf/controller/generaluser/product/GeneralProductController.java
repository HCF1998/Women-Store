package com.womenstore.hcf.controller.generaluser.product;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.womenstore.hcf.dao.product.ProductMapper;
import com.womenstore.hcf.dao.user.UserMapper;
import com.womenstore.hcf.entity.product.Product;
import com.womenstore.hcf.util.Result;
import com.womenstore.hcf.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/generalUser/product")
@Slf4j
public class GeneralProductController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    /**
     * 商品模糊查询
     * @param jsonObject
     * @return
     */
    @PostMapping("/searchProduct")
    public Result searchProduct(@RequestBody JSONObject jsonObject){
        log.info("jsonObject:[{}]",jsonObject.toString());
        String searchProductName =(String)jsonObject.get("searchProductName");
        QueryWrapper<Product> qwSearchProductName = new QueryWrapper<>();
        qwSearchProductName.like("product_Name",searchProductName);
        List<Product> findProducts = productMapper.selectList(qwSearchProductName);
        if (findProducts.size()==0){
            String noneProduct = "所查询商品不存在";
            return new Result(ResultCode.BAD_REQUEST,noneProduct);
        }
        return new Result(ResultCode.SUCCESS,findProducts);
    }
}
