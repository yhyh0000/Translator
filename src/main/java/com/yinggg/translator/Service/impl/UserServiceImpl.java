package com.yinggg.translator.Service.impl;

import com.yinggg.translator.Dao.UserMapper;
import com.yinggg.translator.Pojo.User;
import com.yinggg.translator.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public ArrayList<User> queryAllUserInfo() {
        return userMapper.queryAllUserInfo();
    }
}
