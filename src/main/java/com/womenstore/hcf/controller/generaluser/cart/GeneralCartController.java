package com.womenstore.hcf.controller.generaluser.cart;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.womenstore.hcf.entity.cart.Cart;
import com.womenstore.hcf.entity.product.Product;
import com.womenstore.hcf.service.impl.CartServiceImpl;
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
 * 购物车类Controller
 */
@Slf4j
@Api
@RestController
@RequestMapping("/generalUser/cart")
public class GeneralCartController {

  @Autowired private CartServiceImpl cartServiceImpl;

  @Autowired private ProductServiceImpl productServiceImpl;
  /**
   * 获取购物车商品列表
   *
   * @param userId
   * @return
   */
  @ApiOperation("获取购物车列表")
  @GetMapping("/getCartList/{userId}")
  public Result getCartList(@RequestBody @PathVariable(value = "userId") Integer userId) {
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq("user_Id", userId);
    List<Product> cartProducts = cartServiceImpl.list(queryWrapper);
    log.info("cartProducts:[{}]", cartProducts);
    if (cartProducts.size() <= 0) {
      return new Result(ResultCode.SUCCESS, "购物车中暂未保存商品");
    }
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("cartProducts", cartProducts);
    return new Result(ResultCode.SUCCESS, jsonObject);
  }

  @ApiOperation("添加购物车")
  @PostMapping("/addCart")
  public Result addCart(@RequestBody JSONObject jsonObject) {
    log.info("jsonObject:[{}]", jsonObject.toString());
    Product product = productServiceImpl.getById((Integer) jsonObject.get("productId"));
    if (product != null) {
      Cart addCart =
          new Cart()
              .builder()
              .userId((Integer) jsonObject.get("userId"))
              .productId(product.getProductId())
              .productNum((Integer) jsonObject.get("productNum"))
              .productPrice(product.getProductPrice())
              .build();
      if (addCart != null) {
        cartServiceImpl.save(addCart);
      }
      return new Result(ResultCode.SUCCESS, "成功添加购物车:[" + product.getProductName() + "]");
    }
    return null;
  }

  @ApiOperation("取消存放购物车")
  @GetMapping("/deleteCart/{cartId}")
  public Result delete(@RequestBody @PathVariable(value = "cartId") Integer cartId) {
    if (cartId != null) {
      cartServiceImpl.removeById(cartId);
    }
    return new Result(ResultCode.SUCCESS, "已从购物车中移除该商品");
  }
}
