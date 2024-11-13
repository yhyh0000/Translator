package com.yinggg.translator.Controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONUtil;
import com.yinggg.translator.Service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/queryAllUserInfo")
    public Dict queryAllUserInfo() {
        Dict data = new Dict();
        data.set("code", 200);
        data.set("data", userService.queryAllUserInfo());
        data.set("message","请求成功");
        return data;
    }
}
