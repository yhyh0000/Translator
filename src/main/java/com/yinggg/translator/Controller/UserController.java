package com.yinggg.translator.Controller;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.Preconditions;
import com.yinggg.translator.entity.TUser;
import com.yinggg.translator.service.TUserService;
import com.yinggg.translator.utils.JwtUtils;
import com.yinggg.translator.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
public class UserController {
    @Resource
    private TUserService tUserService;

    @PostMapping ("/login")
    public Result login(@RequestBody TUser tuser) {
        log.info(JSON.toJSONString(tuser));
        //guava参数校验
        Preconditions.checkNotNull(tuser.getUsername(), "账号不能为空");
        Preconditions.checkNotNull(tuser.getPassword(), "密码不能为空");
        TUser user = tUserService.login(tuser);
        //JWT令牌生成
        if (user != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("account", user.getUsername());

            String jwt = JwtUtils.generateJwt(claims); //jwt包含了当前登录的信息
            return Result.success(jwt);
        } else {
            return Result.error("登录失败");
        }
    }
}





