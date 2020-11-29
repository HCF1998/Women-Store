package com.womenstore.hcf.controller.generaluser.product;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.womenstore.hcf.entity.product.Product;
import com.womenstore.hcf.entity.product.Productinventorys;
import com.womenstore.hcf.service.impl.ProductInventoryServiceImpl;
import com.womenstore.hcf.service.impl.ProductServiceImpl;
import com.womenstore.hcf.util.Result;
import com.womenstore.hcf.util.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 普通用户商品controller
 */
@Api
@RestController
@RequestMapping("/generalUser/product")
@Slf4j
public class GeneralProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private ProductInventoryServiceImpl productInventoryServiceImpl;

    /**
     * 商品模糊查询
     * @param jsonObject
     * @return
     */
    @ApiOperation("商品模糊查询")
    @PostMapping("/searchProduct")
    public Result searchProduct(@RequestBody JSONObject jsonObject){
        log.info("jsonObject:[{}]",jsonObject.toString());
        String searchProductName =(String)jsonObject.get("searchProductName");
        QueryWrapper<Product> qwSearchProductName = new QueryWrapper<>();
        qwSearchProductName.like("product_Name",searchProductName);
        List<Product> findProducts = productServiceImpl.list(qwSearchProductName);
        if (findProducts.size()==0){
            String noneProduct = "所查询商品不存在";
            log.error("模糊查询商品:["+searchProductName+"]不存在");
            return new Result(ResultCode.BAD_REQUEST,noneProduct);
        }
        return new Result(ResultCode.SUCCESS,findProducts);
    }

    /**
     * 商品详情
     * @param productId
     * @return
     */
    @ApiOperation("商品详情")
    @GetMapping("/detail/{productId}")
    public Result detailProduct(@PathVariable(value = "productId")Integer productId){
        log.info("productId:{}",productId);
        Product product = productServiceImpl.getById(productId);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_Id",productId);
        List<Productinventorys> getInventorys =
                productInventoryServiceImpl.list(queryWrapper);
        JSONObject productDetailJson = new JSONObject();
        productDetailJson.put("product",product);
        productDetailJson.put("inventory",getInventorys);
        return new Result(ResultCode.SUCCESS,productDetailJson);
    }
}
