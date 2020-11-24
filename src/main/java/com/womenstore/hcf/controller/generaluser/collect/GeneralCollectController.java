package com.womenstore.hcf.controller.generaluser.collect;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.womenstore.hcf.entity.collect.Collect;
import com.womenstore.hcf.entity.product.Product;
import com.womenstore.hcf.service.impl.CollectServiceImpl;
import com.womenstore.hcf.service.impl.ProductServiceImpl;
import com.womenstore.hcf.util.Result;
import com.womenstore.hcf.util.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 用户商品收藏类
 */

@Api
@RestController
@Slf4j
@RequestMapping("/generalUser/collect")
public class GeneralCollectController {
    @Autowired
    private CollectServiceImpl collectServiceImpl;
    @Autowired
    private ProductServiceImpl productServiceImpl;

  /**
   * 添加商品收藏
   * @param jsonObject
   * @return
   */
  @ApiOperation("添加商品收藏")
  @PostMapping("/addCollect")
  public Result addCollect(@RequestBody JSONObject jsonObject) {
        log.info("jsonObject:[{}]");
        Integer userId = (Integer)jsonObject.get("userId");
        Integer productId = (Integer)jsonObject.get("productId");
        Date addCollectDate = new Date();
        Collect addCollect = new Collect();
        Product product = productServiceImpl.getById(productId);

        addCollect.setCollectTime(addCollectDate);
        addCollect.setUserId(userId);
        addCollect.setProductId(productId);
        addCollect.setProductName(product.getProductName());
        addCollect.setProductPrice(product.getProductPrice());
        collectServiceImpl.save(addCollect);

        return new Result(ResultCode.SUCCESS,"收藏商品成功");
    }

    /**
     * 查询商品收藏
     * @param userId
     * @return
     */
    @ApiOperation("查询商品收藏")
    @GetMapping("/findAllCollect/{userId}")
    public Result findAllCollect(@PathVariable("userId")Integer userId){
        log.info("userId:[{}]",userId);
        QueryWrapper qwCollect = new QueryWrapper();
        qwCollect.eq("user_Id",userId);
        List<Collect> collectList = collectServiceImpl.list(qwCollect);
        if (collectList!=null){
            return new Result(ResultCode.SUCCESS,collectList);
        }else {
            return new Result(ResultCode.SUCCESS,"还未收藏任何商品");
        }
    }

    /**
     * 取消收藏
     * @param collectId
     * @return
     */
    @ApiOperation("取消收藏")
    @GetMapping("deleteCollect/{collectId}")
    public Result deleteCollect(@PathVariable("collectId")Integer collectId){
        collectServiceImpl.removeById(collectId);
        return new Result(ResultCode.SUCCESS,"取消成功");
    }

    /**
     * 模糊查询所收藏的商品
     * @param jsonObject
     * @return
     */
    @ApiOperation("模糊查询所收藏的商品")
    @PostMapping("/searchCollect")
    public Result searchCollect(@RequestBody JSONObject jsonObject){
        String searchProductName = (String)jsonObject.get("searchProductName");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("product_Name",searchProductName);
        List<Product> searchLists = collectServiceImpl.list(queryWrapper);
        if (searchLists.size()==0){
            return new Result(ResultCode.SUCCESS,"暂未收藏相关商品");
        }else {
            JSONObject returnSearchList = new JSONObject();
            returnSearchList.put("searchLists",searchLists);
            return new Result(ResultCode.SUCCESS,returnSearchList);
        }
    }

}
