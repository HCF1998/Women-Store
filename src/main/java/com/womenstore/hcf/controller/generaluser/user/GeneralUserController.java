package com.womenstore.hcf.controller.generaluser.user;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.womenstore.hcf.entity.user.User;
import com.womenstore.hcf.requestentity.user.UserLoginReq;
import com.womenstore.hcf.requestentity.user.UserRegisterReq;
import com.womenstore.hcf.service.impl.UserServiceImpl;
import com.womenstore.hcf.util.Result;
import com.womenstore.hcf.util.ResultCode;
import com.womenstore.hcf.util.UuidCode;
import com.womenstore.hcf.util.entity.Login;
import com.womenstore.hcf.util.utilMapper.LoginMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 普通用户类controller
 */
@Api
@RestController
@RequestMapping("/generalUser/user")
@Slf4j
public class GeneralUserController {

  @Autowired
  private UserServiceImpl userServiceImpl;

  @Autowired
  private LoginMapper loginMapper;
    /**
     * 注册用户
     * @param userRegisterReq
     * @return
     */
    @ApiOperation("注册用户接口")
    @PostMapping("/addUser")
  public Result addUser(@Valid UserRegisterReq userRegisterReq) {
    log.info("userRegisterReq:[{}]", userRegisterReq);
    String userRegisterAcount = userRegisterReq.getUserRegisterAcount();
    QueryWrapper<User> qwUser = new QueryWrapper<>();
    qwUser.eq("user_Acount", userRegisterAcount);
    User hadUserRegister = userServiceImpl.getOne(qwUser);
    if (hadUserRegister != null) {
      return new Result(ResultCode.BAD_REQUEST, "该账号已被注册");
    } else {
      if (!StringUtils.equals(userRegisterReq.getUserRegisterPassword(),
              userRegisterReq.getUserRegisterPasswordConfirm())) {
        return new Result(ResultCode.BAD_REQUEST, "密码和确认密码不一致，请重新填写");
      } else {
        User user = new User();
        user.setUserAcount(userRegisterAcount);
        user.setUserPassword(userRegisterReq.getUserRegisterPassword());
        user.setUserPriority(0);
        userServiceImpl.save(user);
        return new Result(ResultCode.SUCCESS, "已成功注册该账号:" + userRegisterAcount);
      }
    }
  }


  /**
   * 用户登录
   * @param userLoginReq 登录实体
   * @return
   */
  @ApiOperation("普通用户登录接口")
  @PostMapping("/userLogin")
  public Result userLogin(@RequestBody @Valid UserLoginReq userLoginReq) {
    log.info("userLoginReq:[{}]", userLoginReq);
    String userLoginAcount = userLoginReq.getUserAcount();
    String userLoginPassword = userLoginReq.getUserPassword();
    QueryWrapper<User> qw = new QueryWrapper<>();
    qw.eq("user_Acount", userLoginAcount);
    User hadUser = userServiceImpl.getOne(qw);
    if (hadUser != null && StringUtils.equals(hadUser.getUserPassword(), userLoginPassword)) {
      if (hadUser.getUserStatus()==0){
        return new Result(405,"该账号已被封禁，禁止登陆",null);
      }
    } else {
      return new Result(404, "无此账号信息或密码不正确", null);
    }

    /**
     * 用户登录存入uuid
     */
    String loginUuid = UuidCode.generateLoginUuid();
    Login loginUser = new Login();
    loginUser.setUserAcount(userLoginAcount);
    loginUser.setLoginUuid(loginUuid);
    loginMapper.insert(loginUser);
    return new Result(200,"普通用户登陆","index.jsp");
  }

  @ApiOperation("用户信息修改")
  @GetMapping("/editUser")
  public Result editUser(@RequestBody JSONObject jsonObject){
    log.info("jsonObject:[{}]",jsonObject.toString());
    String userAcount = (String) jsonObject.get("userAcount");
    /**
     * 用户登录校验
     */
    Boolean isLogin = checkLogin(userAcount);
    if (!isLogin){
      return new Result(ResultCode.FAILED,"请先登录");
    }
    return new Result(ResultCode.SUCCESS,"更新信息成功");
  }

  @ApiOperation("用户密码修改")
  @PostMapping("/editPassword")
  public Result editPassword(@RequestBody JSONObject jsonObject){
    log.info("jsonObject:[{}]");
    User user = userServiceImpl.getById((String)jsonObject.get("userId"));
    String oldPassword = (String)jsonObject.get("oldPassword");
    String newPassword = (String)jsonObject.get("newPassword");
    String confirmPassword = (String)jsonObject.get("confirmPassword");
    if (!StringUtils.equals(user.getUserPassword(),oldPassword)){
      return new Result(ResultCode.ERROR,"账户当前密码不正确");
    }else {
      if (!StringUtils.equals(newPassword,confirmPassword)){
        return new Result(ResultCode.ERROR,"新密码和确认密码不一致");
      }
      user.setUserPassword(confirmPassword);
      return new Result(ResultCode.SUCCESS,"修改密码成功");
    }
  }

  /**
   * 校验用户是否登录
   * @param userAcount 用户登录账号
   * @return boolean
   */
  public Boolean checkLogin(String userAcount){
    QueryWrapper queryWrapper = new QueryWrapper();
    queryWrapper.eq("user_Acount",userAcount);
    Login isLogin = loginMapper.selectOne(queryWrapper);
    if (isLogin==null){
      return false;
    }
    return true;
  }


}
