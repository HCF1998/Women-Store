package com.womenstore.hcf.controller.admin.user;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.womenstore.hcf.dao.user.UserMapper;
import com.womenstore.hcf.entity.user.User;
import com.womenstore.hcf.service.impl.UserServiceImpl;
import com.womenstore.hcf.util.Result;
import com.womenstore.hcf.util.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api
@RestController
@RequestMapping("/admin/user")
@Slf4j
public class AdminUserController {

  @Autowired private UserMapper userMapper;

  @Autowired private UserServiceImpl userServiceImpl;

  /**
   * 遍历用户
   *
   * @return 用户列表
   */
  @ApiOperation("遍历用户")
  @GetMapping("/findAll")
  public Result finfAll() {
    List<User> userList = userServiceImpl.list();
    log.info("userList:{}", userList);
    return new Result(userList);
  }

  /**
   * 用户权限修改
   *
   * @param jsonObject
   * @return
   */
  @ApiOperation("修改用户权限")
  @PostMapping("/changeUserPriority")
  public Result changeUserPriority(@RequestBody JSONObject jsonObject) {
    log.info("jsonObject:{}", jsonObject);
    String changPriorityId = (String) jsonObject.get("userId");
    Integer changPriorityValue = (Integer) jsonObject.get("priorityValue");
    User changPriorityUser = userServiceImpl.getById(changPriorityId);
    changPriorityUser.setUserPriority(changPriorityValue);
    userServiceImpl.updateById(changPriorityUser);
    if (changPriorityValue == 1) {
      return new Result(200, "修改用户:" + changPriorityId + "权限为管理员", null);
    } else if (changPriorityValue == 0) {
      return new Result(200, "修改用户:" + changPriorityId + "权限为普通用户", null);
    }
    return new Result(null);
  }

  /**
   * 删除用户
   * @param deleteUserId
   * @return
   */
  @ApiOperation("删除用户")
  @GetMapping("/deleteUser/{deleteUserId}")
  public Result deleteUser(
      @RequestBody @PathVariable(value = "deleteUserId") Integer deleteUserId) {
    log.info("deleteUserId:{}", deleteUserId);
    userServiceImpl.removeById(deleteUserId);
    return new Result(200, "删除用户id：[" + deleteUserId + "]成功", null);
  }

  /**
   * 设置用户账号状态
   *
   * @param jsonObject
   * @return
   */
  @ApiOperation("设置用户账号状态")
  @PostMapping("/banOrUnbanUser")
  private Result banOrUnbanUser(@RequestBody JSONObject jsonObject) {
    log.info("jsonObject:[{}]", jsonObject);
    Integer userId = (Integer) jsonObject.get("userId");
    Integer status = (Integer) jsonObject.get("status");
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq("user_Id", userId);
    User user = userServiceImpl.getOne(queryWrapper);
    user.setUserStatus(status);
    userServiceImpl.updateById(user);
    if (status == 0) {
      return new Result(ResultCode.SUCCESS, "成功封禁账号：" + user.getUserAcount());
    } else if (status == 1) {
      return new Result(ResultCode.SUCCESS, "成功解禁账号：" + user.getUserAcount());
    }
    return null;
  }
}
