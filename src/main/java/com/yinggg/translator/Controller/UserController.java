package com.yinggg.translator.Controller;


import com.yinggg.translator.utils.ResponseCode;
import com.yinggg.translator.utils.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/login")
    public Result<String> login() {
        // 返回带有数据的 Result，其中数据是字符串
        return ResponseCode.SUCCESS.toResult("Some data here");
    }

}
