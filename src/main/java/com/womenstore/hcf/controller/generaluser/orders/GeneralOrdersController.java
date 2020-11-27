package com.womenstore.hcf.controller.generaluser.orders;

import com.alibaba.fastjson.JSONObject;
import com.womenstore.hcf.entity.orders.Orders;
import com.womenstore.hcf.entity.product.Product;
import com.womenstore.hcf.service.impl.OrdersServiceImpl;
import com.womenstore.hcf.service.impl.ProductServiceImpl;
import com.womenstore.hcf.util.Result;
import com.womenstore.hcf.util.ResultCode;
import com.womenstore.hcf.util.UuidCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@Api
@RestController
@RequestMapping("/generalUser/orders")
@Slf4j
public class GeneralOrdersController {
  @Autowired private OrdersServiceImpl ordersServiceImpl;

  @Autowired private ProductServiceImpl productServiceImpl;

  /**
   * 创建新订单
   *
   * @param jsonObject
   * @return
   */
  @ApiOperation("创建新订单")
  @PostMapping("/createOrder")
  public Result createOrder(@RequestBody JSONObject jsonObject) {

    log.info("jsonObject:[{}]", jsonObject);
    String orderNo = UuidCode.generateOrderUuid();
    Date orderCreatetime = new Date(System.currentTimeMillis());
    Integer orderProductNum = (Integer) jsonObject.get("orderProductNum");

    /** 获取商品信息 */
    Integer productId = (Integer) jsonObject.get("productId");
    Product product = productServiceImpl.getById(productId);

    /** 计算订单金额 */
    BigDecimal orderSum = product.getProductPrice().multiply(new BigDecimal(orderProductNum));

    Orders newOrder =
        new Orders()
            .builder()
            .orderNo(orderNo)
            .orderCreatetime(orderCreatetime)
            .orderStatus(0)
            .orderPaytime(orderCreatetime)
            .orderSum(orderSum)
            .orderProductNum(orderProductNum)
            .userId((Integer) jsonObject.get("userId"))
            .userPhone((String) jsonObject.get("userPhone"))
            .userAddress((String) jsonObject.get("userAddress"))
            .productId(productId)
            .productName(product.getProductName())
            .productPrice(product.getProductPrice())
            .build();

    ordersServiceImpl.save(newOrder);
    return new Result(ResultCode.SUCCESS, "成功创建新订单，请在规定时间内进行支付，否则将会自动取消");
  }
}
