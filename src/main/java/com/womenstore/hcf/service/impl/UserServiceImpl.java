package com.womenstore.hcf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.womenstore.hcf.dao.user.UserMapper;
import com.womenstore.hcf.entity.user.User;
import com.womenstore.hcf.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
