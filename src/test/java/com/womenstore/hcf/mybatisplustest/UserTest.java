package com.womenstore.hcf.mybatisplustest;

import com.womenstore.hcf.dao.user.UserMapper;
import com.womenstore.hcf.entity.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserTest {
    @Autowired
    private UserMapper mapper;
    @Test
    public void findAll(){
       List<User> users = mapper.selectList(null);
    users.forEach(user -> System.out.println("user="+user));
    }
}
