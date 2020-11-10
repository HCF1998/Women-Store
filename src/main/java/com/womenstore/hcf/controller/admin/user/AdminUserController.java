package com.womenstore.hcf.controller.admin.user;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.womenstore.hcf.dao.user.UserMapper;
import com.womenstore.hcf.entity.product.Product;
import com.womenstore.hcf.entity.user.User;
import com.womenstore.hcf.util.Result;
import com.womenstore.hcf.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
@Slf4j
public class AdminUserController {

    @Autowired
    private UserMapper userMapper;

    /**
     * 遍历用户
     * @return
     */
    @GetMapping("/findAll")
    public Result finfAll(){
        List<User> userList = userMapper.selectList(null);
        log.info("userList:{}",userList);
        return new Result(userList);
    }

    /**
     * 用户权限修改
     * @param jsonObject
     * @return
     */
    @GetMapping("/changeUserPriority")
    public Result changeUserPriority(@RequestBody JSONObject jsonObject) {
        log.info("jsonObject:{}", jsonObject);
        String changPriorityId = (String) jsonObject.get("userId");
        Integer changPriorityValue = (Integer) jsonObject.get("priorityValue");
        User changPriorityUser = userMapper.selectById(changPriorityId);
        changPriorityUser.setUserPriority(changPriorityValue);
        if (changPriorityValue == 1) {
            return new Result(200, "修改用户:" + changPriorityId + "权限为管理员", null);
        } else if (changPriorityValue == 0) {
            return new Result(200, "修改用户:" + changPriorityId + "权限为普通用户", null);
        }
        return new Result(null);
    }

    /**
     * 删除用户
     * @param jsonObject
     * @return
     */
    @GetMapping("/deleteUser")
    public Result deleteUser(@RequestBody JSONObject jsonObject) {
        log.info("jsonObject:{}", jsonObject);
        String deletedId = (String) jsonObject.get("userId");
        userMapper.deleteById(deletedId);
        return new Result(200, "删除用户id：" + deletedId + "成功", null);
    }

    /**
     * 封禁用户
     * @param jsonObject
     * @return
     */
    @GetMapping("/banUsers")
    public Result banUsers(@RequestBody JSONObject jsonObject) {
        log.info("jsonObject:[{}]", jsonObject.toJSONString());
        getBanUser(jsonObject);
        return new Result(ResultCode.SUCCESS,
                "成功封禁用户Id:"+jsonObject.get("userAcount"));
    }

    /**
     * 解禁用户
     * @param jsonObject
     * @return
     */
    @GetMapping("/unbanUsers")
    public Result unbanUsers(@RequestBody JSONObject jsonObject){
        log.info("jsonObject:[{}]", jsonObject.toJSONString());
        getBanUser(jsonObject);
        return new Result(ResultCode.SUCCESS,
                "成功解禁用户Id:"+jsonObject.get("userAcount"));
    }

    private void getBanUser(@RequestBody JSONObject jsonObject) {
        String userId = (String) jsonObject.get("userAcount");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_Acount",userId);
        User user  = userMapper.selectOne(queryWrapper);
        Integer status =(Integer)jsonObject.get("userStatus");
        if (status==1){
            user.setUserStatus(1);
            userMapper.updateById(user);
        }else if (status==0){
            user.setUserStatus(0);
            userMapper.updateById(user);
        }
    }
}
