package com.womenstore.hcf.controller.admin.product;

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

import java.math.BigDecimal;
import java.util.List;

/** 商品controller */
@Api
@RestController
@RequestMapping("/admin/product")
@Slf4j
public class AdminProductController {

  @Autowired private ProductServiceImpl productServiceImpl;

  @Autowired private ProductInventoryServiceImpl productInventoryServiceImpl;

  /**
   * 查询所有商品
   *
   * @return 统一返回体和数据结果集
   */
  @ApiOperation("查询所有商品")
  @GetMapping("/findAll")
  public Result findAll() {
    List<Product> productList = productServiceImpl.list();
    if (productList != null) {
      return new Result(ResultCode.SUCCESS, productList);
    } else {
      return new Result(ResultCode.SUCCESS, "当前未有商品");
    }
  }

  /**
   * 条件查询商品
   *
   * @param product
   * @return
   */
  //    @PostMapping("/searchProduct")

  /**
   * 添加商品
   *
   * @param
   * @return 统一返回体
   */
  @ApiOperation("添加商品")
  @PostMapping("/addProduct")
  public Result addProduct(@RequestBody JSONObject jsonObject) {
    log.info("jsonObject:[{}]", jsonObject);
    Product product =
        new Product()
            .builder()
            .productName((String) jsonObject.get("productName"))
            .productPrice(new BigDecimal((Double)jsonObject.get("productPrice")))
            .productStatus((Integer) jsonObject.get("productStatus"))
            .productImage((String) jsonObject.get("productImage"))
            .productCategory((Integer) jsonObject.get("productCategory"))
            .productDetail((String) jsonObject.get("productDetail"))
            .build();
    productServiceImpl.save(product);
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq("product_Name", (String) jsonObject.get("productName"));
    product = productServiceImpl.getOne(queryWrapper);

    /** 处理尺码和库存 */
    String sizes = (String) jsonObject.get("size");
    String productinventorys = (String) jsonObject.get("productinventory");
    String[] sizeLists = sizes.split(",");
    String[] productinventoryLists = productinventorys.split(",");

    if (sizeLists.length == productinventoryLists.length) {
      for (int i = 0; i < sizeLists.length; i++) {
        Productinventorys productinventory =
            new Productinventorys()
                .builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productSize(sizeLists[i])
                .productInventory(Integer.valueOf(productinventoryLists[i]))
                .build();
        productInventoryServiceImpl.save(productinventory);
      }
    }
    return new Result(ResultCode.SUCCESS, "添加商品成功");
  }

  /**
   * 删除商品
   *
   * @param jsonObject
   * @return
   */
  @ApiOperation("删除商品")
  @PostMapping("/deleteProduct")
  public Result deleteProduct(@RequestBody JSONObject jsonObject) {
    Integer productId = (Integer) jsonObject.get("productId");
    String productName = (String) jsonObject.get("productName");
    try {
      productServiceImpl.removeById(productId);
    } catch (Exception e) {
      log.info("exception:[{}]", e);
    }
    return new Result(
        ResultCode.SUCCESS, "成功删除商品【Id:" + productId + ", productName：" + productName);
  }

  /**
   * 编辑商品信息
   *
   * @param product
   * @return
   */
  @ApiOperation("编辑商品信息")
  @PostMapping("/editProduct")
  public Result editProduct(@RequestBody Product product) {
    if (product.getProductId() != null) {
      productServiceImpl.updateById(product);
      return new Result(ResultCode.SUCCESS, product);
    }
    return null;
  }

  /**
   * 上架商品
   *
   * @param jsonObject
   * @return 统一返回体
   */
  @ApiOperation("上架商品")
  @GetMapping("/onSafeProduct")
  public Result onSafeProduct(@RequestBody JSONObject jsonObject) {
    log.info("jsonObject:[{}]", jsonObject.toJSONString());
    return getResult(jsonObject);
  }

  /**
   * 下架商品
   *
   * @param jsonObject
   * @return 统一返回体
   */
  @ApiOperation("下架商品")
  @GetMapping("/unSafeProduct")
  public Result unSafeProduct(@RequestBody JSONObject jsonObject) {
    log.info("jsonObject:[{}]", jsonObject.toJSONString());
    return getResult(jsonObject);
  }

  /**
   * 商品上下架抽成方法
   *
   * @param jsonObject
   * @return
   */
  private Result getResult(@RequestBody JSONObject jsonObject) {
    Integer productId = (Integer) jsonObject.get("productId");
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq("product_Id", productId);
    Product onSafeProduct = productServiceImpl.getOne(queryWrapper);
    Integer status = (Integer) jsonObject.get("productStatus");
    if (status == 1) {
      onSafeProduct.setProductStatus(1);
    } else if (status == 0) {
      onSafeProduct.setProductStatus(0);
      return new Result(ResultCode.SUCCESS, "成功下架商品：" + onSafeProduct.getProductName() + "");
    }
    productServiceImpl.updateById(onSafeProduct);
    return new Result(ResultCode.SUCCESS, "成功上架商品：" + onSafeProduct.getProductName() + "");
  }
}
