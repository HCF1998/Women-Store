package com.womenstore.hcf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.womenstore.hcf.dao.user.UserMapper;
import com.womenstore.hcf.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @GetMapping("/findAll")
    public JSONObject finfAll(){
        List<User> userList = userMapper.selectList(null);
        JSONArray userListJson = new JSONArray();
        userListJson.add(userList);
        userList.forEach(user -> System.out.println("user:"+user));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userListJson",userListJson);
        return jsonObject;
    }
    @PostMapping("/addUser")
    public JSONObject addUser(){
        User user = new User().builder()
                .userName("addUser2")
                .userAcount("add1")
                .userAddress("华软")
                .userPassword("123")
                .userPhone("156")
                .userPriority(1)
                .userStatus(1).build();
        userMapper.insert(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("note","添加成功");
        jsonObject.put("status","11");
        return jsonObject;
    }

  @PostMapping("/jsonAddUser")
  public String  jsonAddUser(@RequestBody JSONObject jsonObject) {
      JSONArray userJson = jsonObject.getJSONArray("user");
      List<User> jsonUser = JSON.parseArray(userJson.toJSONString(),User.class);
      for (User user:jsonUser){
          userMapper.insert(user);
      }
      return "";
  }
}
