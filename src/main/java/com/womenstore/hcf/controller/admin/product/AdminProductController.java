package com.womenstore.hcf.controller.admin.product;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.womenstore.hcf.dao.product.ProductMapper;
import com.womenstore.hcf.entity.product.Product;
import com.womenstore.hcf.util.Result;
import com.womenstore.hcf.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/findAll")
    public Result findAll(){
        List<Product> productList = productMapper.selectList(null);
        return new Result(productList);
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
     * 上架商品
     * @param jsonObject
     * @return 统一返回体
     */
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
    @GetMapping("/unSafeProduct")
    public Result unSafeProduct(@RequestBody JSONObject jsonObject){
        log.info("jsonObject:[{}]", jsonObject.toJSONString());
        return getResult(jsonObject);
    }

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
