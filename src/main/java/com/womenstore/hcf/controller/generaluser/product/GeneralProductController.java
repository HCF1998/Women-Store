package com.womenstore.hcf.controller.generaluser.product;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.womenstore.hcf.dao.product.ProductMapper;
import com.womenstore.hcf.dao.user.UserMapper;
import com.womenstore.hcf.entity.product.Product;
import com.womenstore.hcf.util.Result;
import com.womenstore.hcf.util.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api
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
    @ApiOperation("商品模糊查询")
    @PostMapping("/searchProduct")
    public Result searchProduct(@ApiParam("商品模糊字段") @RequestBody JSONObject jsonObject){
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

    /**
     * 商品详情
     * @param productId
     * @return
     */
    @ApiOperation("商品详情")
    @GetMapping("/detail/{productId}")
    public Result detailProduct(@PathVariable(value = "productId")Integer productId){
        log.info("productId:{}",productId);
        Product product = productMapper.selectById(productId);
        String[] productSizes = product.getProductSize().split(",");
        String[] productInventories = product.getProductInventory().split(",");
        if (!(productSizes.length ==productInventories.length)){
            return new Result(ResultCode.ERROR,"商品库存及尺码对应错误");
        }
        Map<String,Integer> sizeAndInventory = new HashMap<>();
        for (int i=0;i<productSizes.length;i++){
            sizeAndInventory.put(productSizes[i],Integer.valueOf(productInventories[i]));
        }
        JSONObject productDetailJson = new JSONObject();
        productDetailJson.put("product",product);
        productDetailJson.put("sizeAndInventory",sizeAndInventory);
        return new Result(ResultCode.SUCCESS,productDetailJson);
    }
}
