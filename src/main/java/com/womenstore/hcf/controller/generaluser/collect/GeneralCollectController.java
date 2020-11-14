package com.womenstore.hcf.controller.generaluser.collect;


import com.alibaba.fastjson.JSONObject;
import com.womenstore.hcf.dao.collect.CollectMapper;
import com.womenstore.hcf.dao.user.UserMapper;
import com.womenstore.hcf.entity.collect.Collect;
import com.womenstore.hcf.util.Result;
import com.womenstore.hcf.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 用户商品收藏类
 */
@RestController
@Slf4j
@RequestMapping("/generalUser/collect")
public class GeneralCollectController {
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 收藏商品
     * @param jsonObject
     * @return
     */
    @PostMapping("/addCollect")
    public Result addCollect(@RequestBody JSONObject jsonObject){
        log.info("jsonObject:[{}]");
        Integer userId = (Integer)jsonObject.get("userId");
        Integer productId = (Integer)jsonObject.get("productId");
        Date addCollectDate = new Date();
        Collect addCollect = new Collect();
        addCollect.setCollectTime(addCollectDate);
        addCollect.setUserId(userId);
        addCollect.setProductId(productId);
        collectMapper.insert(addCollect);
        return new Result(ResultCode.SUCCESS,"收藏商品成功");
    }

    @GetMapping("/findAllCollect/{userId}")
    public Result findAllCollect(@PathVariable("userId")Integer userId){
        log.info("userId:[{}]",userId);
        return null;
    }

}
