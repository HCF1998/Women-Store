package com.womenstore.hcf.controller.generaluser.user;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.womenstore.hcf.dao.user.UserMapper;
import com.womenstore.hcf.entity.user.User;
import com.womenstore.hcf.requestentity.user.UserLoginReq;
import com.womenstore.hcf.requestentity.user.UserRegisterReq;
import com.womenstore.hcf.util.Result;
import com.womenstore.hcf.util.ResultCode;
import com.womenstore.hcf.util.UuidCode;
import com.womenstore.hcf.util.entity.Login;
import com.womenstore.hcf.util.utilMapper.LoginMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/generalUser/user")
@Slf4j
public class GeneralUserController {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private LoginMapper loginMapper;
    /**
     * 注册用户
     * @param userRegisterReq
     * @return
     */
  public Result addUser(@RequestBody UserRegisterReq userRegisterReq) {
    log.info("userRegisterReq:[{}]", userRegisterReq);
    String userRegisterAcount = userRegisterReq.getUserRegisterAcount();
    QueryWrapper<User> qwUser = new QueryWrapper<>();
    qwUser.eq("user_Acount", userRegisterAcount);
    User hadUserRegister = userMapper.selectOne(qwUser);
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
        userMapper.insert(user);
        return new Result(ResultCode.SUCCESS, "已成功注册该账号:" + userRegisterAcount);
      }
    }
  }


  /**
   * 用户登录
   * @param userLoginReq 登录实体
   * @return
   */
  @PostMapping("/userLogin")
  public Result userLogin(@RequestBody UserLoginReq userLoginReq) {
    log.info("userLoginReq:[{}]", userLoginReq);
    String userLoginAcount = userLoginReq.getUserAcount();
    String userLoginPassword = userLoginReq.getUserPassword();
    QueryWrapper<User> qw = new QueryWrapper<>();
    qw.eq("user_Acount", userLoginAcount);
    User hadUser = userMapper.selectOne(qw);
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
    String loginUuid = UuidCode.generateUuid();
    Login loginUser = new Login();
    loginUser.setUserAcount(userLoginAcount);
    loginUser.setLoginUuid(loginUuid);
    loginMapper.insert(loginUser);
    return new Result(200,"普通用户登陆","index.jsp");
  }

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
