package com.womenstore.hcf.pageTest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.womenstore.hcf.dao.user.UserMapper;
import com.womenstore.hcf.entity.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;


@SpringBootTest
public class UserPageTest {
    @Resource
    private UserMapper userMapper;
    @Test
    public void queryUserForPage(){
        //参数：当前页，每页个数
        IPage<User> userPage = new Page<>(1,3);
        userPage = userMapper.selectPage(userPage,null);
        List<User> list = userPage.getRecords();
        for (User user:list){
            System.out.println(user);
        }
    }
}
