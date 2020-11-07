package com.womenstore.hcf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.womenstore.hcf.dao.user.UserMapper;
import com.womenstore.hcf.entity.user.User;
import com.womenstore.hcf.util.Result;
import com.womenstore.hcf.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

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
//        JSONArray userListJson = new JSONArray();
//        userListJson.add(userList);
//        userList.forEach(user -> System.out.println("user:"+user));
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("userListJson",userListJson);
//        return jsonObject;
        return new Result(userList);
    }
//    @PostMapping("/addUser")
//    public JSONObject addUser(){
//        User user = new User().builder()
//                .userName("addUser2")
//                .userAcount("add1")
//                .userAddress("华软")
//                .userPassword("123")
//                .userPhone("156")
//                .userPriority(1)
//                .userStatus(1).build();
//        userMapper.insert(user);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("note","添加成功");
//        jsonObject.put("status","11");
//        return jsonObject;
//    }

    /**
     * 注册用户
     * @param jsonObject
     * @return
     */
  @PostMapping("/addUser")
  public Result addUser(@RequestBody JSONObject jsonObject) {
      log.info("jsonObject:{}",jsonObject);
      JSONArray userJson = jsonObject.getJSONArray("user");
      List<User> jsonUser = JSON.parseArray(userJson.toJSONString(),User.class);
      for (User user:jsonUser){
          userMapper.insert(user);
      }
      return new Result(userJson);
  }


    /**
     * 用户登录
     */
    @PostMapping("/userLogin")
    public Result userLogin(@RequestBody JSONObject jsonObject){
        log.info("jsonObject:{}",jsonObject);
        String jsonToString = jsonObject.toString();
        String newS = jsonToString.replace("{",":");
        System.out.println(newS);
        String loginAcount = (String) jsonObject.get("userAcount");
        String loginPassword =(String)jsonObject.get("userPassword");
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("user_Acount",loginAcount);
        User hadUser = userMapper.selectOne(qw);
        if (hadUser!=null){
            if (hadUser.getUserPriority()==1){
                return new Result(200,"该用户为管理员","admin.jsp");
            }else if (hadUser.getUserPriority()==0){
                return new Result(200,"该用户为普通用户","index.jsp");
            }
        }
        return new Result(404,"无此账号信息",null);
    }

    /**
     * 删除用户
     */
  @GetMapping("/deleteUser")
    public Result deleteUser(@RequestBody JSONObject jsonObject){
      log.info("jsonObject:{}",jsonObject);
      String deletedId = (String)jsonObject.get("userId");
      userMapper.deleteById(deletedId);
      return new Result(200,"删除用户id："+deletedId+"成功",null);
  }


    /**
     * 用户权限修改
     */
    @GetMapping("/changeUserPriority")
    public Result changeUserPriority(@RequestBody JSONObject jsonObject){
        log.info("jsonObject:{}",jsonObject);
        String changPriorityId = (String)jsonObject.get("userId");
        Integer changPriorityValue = (Integer)jsonObject.get("priorityValue");
        User changPriorityUser = userMapper.selectById(changPriorityId);
        changPriorityUser.setUserPriority(changPriorityValue);
        if (changPriorityValue==1){
            return new Result(200,"修改用户:"+changPriorityId+"权限为管理员",null);
        }else if (changPriorityValue==0){
            return new Result(200,"修改用户:"+changPriorityId+"权限为普通用户",null);
        }
        return new Result(null);
    }


    /**
     * 用户状态封禁
     */
    @GetMapping("/changUserStatus")
    public Result changUserStatus(@RequestBody JSONObject jsonObject){
        log.info("jsonObject:{}",jsonObject);
        String changUserStatusId = (String)jsonObject.get("userId");
        Integer changUserStatusValue = (Integer)jsonObject.get("statusValue");
        User changStatusUser = userMapper.selectById(changUserStatusId);
        changStatusUser.setUserStatus(changUserStatusValue);
        if (changUserStatusValue==1){
            return new Result(200,"解禁用户:"+changUserStatusId+"，可正常使用",null);
        }else if(changUserStatusValue==0){
           return new Result(200,"封禁用户："+changUserStatusId+"，不可使用",null);
        }
        return new Result(null);
    }

    /**
     * 用户状态解封
     */
}
