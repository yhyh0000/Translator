package com.yinggg.translator.Dao;

import com.yinggg.translator.Pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface UserMapper {
    public ArrayList<User> queryAllUserInfo();
}
