package com.womenstore.hcf.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.womenstore.hcf.dao.user.UserMapper;
import com.womenstore.hcf.entity.user.User;
import com.womenstore.hcf.util.Result;
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
        String banUserId = (String) jsonObject.get("banUserId");
        Integer banUserStatus = (Integer) jsonObject.get("banStatusValue");
        User banUser = userMapper.selectById(banUserId);
        banUser.setUserStatus(banUserStatus);
        userMapper.updateById(banUser);
        return new Result(200, "封禁用户：" + banUserId + "，不可使用", null);
    }

    /**
     * 解禁用户
     * @param jsonObject
     * @return
     */
    @GetMapping("/unbanUsers")
    public Result unbanUsers(@RequestBody JSONObject jsonObject){
        log.info("jsonObject:[{}]", jsonObject.toJSONString());
        String unBanUserId = (String)jsonObject.get("unBanUserId");
        Integer unBanUserStatus = (Integer)jsonObject.get("unBanStatusValue");
        User unBanUser = userMapper.selectById(unBanUserId);
        unBanUser.setUserStatus(unBanUserStatus);
        userMapper.updateById(unBanUser);
        return new Result(200,"用户："+unBanUserId+",已解禁，可正常使用",null);
    }
}
