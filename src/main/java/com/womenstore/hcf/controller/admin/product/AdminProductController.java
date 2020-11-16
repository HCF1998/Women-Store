package com.womenstore.hcf.controller.admin.product;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.womenstore.hcf.dao.product.ProductMapper;
import com.womenstore.hcf.entity.product.Product;
import com.womenstore.hcf.util.Result;
import com.womenstore.hcf.util.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/admin/product")
@Slf4j
public class AdminProductController {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询所有商品
     * @return 统一返回体和数据结果集
     */
    @ApiOperation("查询所有商品")
    @GetMapping("/findAll")
    public Result findAll(){
        List<Product> productList = productMapper.selectList(null);
        return new Result(ResultCode.SUCCESS,productList);
    }

    /**
     * 条件查询商品
     * @param product
     * @return
     */
//    @PostMapping("/searchProduct")


    /**
     * 添加商品
     * @param product 商品实体类
     * @return 统一返回体
     */
    @ApiOperation("添加商品")
    @PostMapping("/addProduct")
    public Result addProduct(@RequestBody @Validated Product product){
      log.info("product:[{}]",product);
      if (product!=null){
          productMapper.insert(product);
          return new Result(ResultCode.SUCCESS,"添加商品成功");
      }
      return null;
    }

    /**
     * 删除商品
     * @param jsonObject
     * @return
     */
    @ApiOperation("删除商品")
    @PostMapping("/deleteProduct")
    public Result deleteProduct(@RequestBody JSONObject jsonObject){
        Integer productId = (Integer)jsonObject.get("productId");
        String productName = (String)jsonObject.get("productName");
        try{
            productMapper.deleteById(productId);
        }catch (Exception e){
            log.info("exception:[{}]",e);
        }
        return new Result(ResultCode.SUCCESS,"成功删除商品【Id:"+productId+", productName："+productName);
    }

    /**
     * 编辑商品信息
     * @param product
     * @return
     */
    @ApiOperation("编辑商品信息")
    @PostMapping("/editProduct")
    public Result editProduct(@RequestBody Product product){
        if (product.getProductId()!=null){
            productMapper.updateById(product);
            return new Result(ResultCode.SUCCESS,product);
        }
        return null;
    }

    /**
     * 上架商品
     * @param jsonObject
     * @return 统一返回体
     */
    @ApiOperation("上架商品")
    @GetMapping("/onSafeProduct")
    public Result onSafeProduct(@RequestBody JSONObject jsonObject){
        log.info("jsonObject:[{}]", jsonObject.toJSONString());
        return getResult(jsonObject);
    }

    /**
     * 下架商品
     * @param jsonObject
     * @return 统一返回体
     */
    @ApiOperation("下架商品")
    @GetMapping("/unSafeProduct")
    public Result unSafeProduct(@RequestBody JSONObject jsonObject){
        log.info("jsonObject:[{}]", jsonObject.toJSONString());
        return getResult(jsonObject);
    }

    /**
     * 商品上下架抽成方法
     * @param jsonObject
     * @return
     */
    private Result getResult(@RequestBody JSONObject jsonObject) {
        Integer productId = (Integer) jsonObject.get("productId");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_Id",productId);
        Product onSafeProduct = productMapper.selectOne(queryWrapper);
        Integer status =(Integer)jsonObject.get("productStatus");
        if (status==1){
                onSafeProduct.setProductStatus(1);
        }else if (status==0){
                onSafeProduct.setProductStatus(0);
            return new Result(ResultCode.SUCCESS, "成功下架商品："+onSafeProduct.getProductName()+"");
        }
        productMapper.updateById(onSafeProduct);
        return new Result(ResultCode.SUCCESS,"成功上架商品："+onSafeProduct.getProductName()+"");
    }

}
