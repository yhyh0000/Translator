package com.yinggg.translator.Controller;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.Preconditions;
import com.yinggg.translator.entity.TUser;
import com.yinggg.translator.service.TUserService;
import com.yinggg.translator.utils.JwtUtils;
import com.yinggg.translator.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping ("/login")
    public Result login(@RequestBody TUser tuser) {
        log.info(JSON.toJSONString(tuser));
        //guava参数校验
        Preconditions.checkNotNull(tuser.getUsername(), "账号不能为空");
        Preconditions.checkNotNull(tuser.getPassword(), "密码不能为空");
        TUser user = tUserService.login(tuser);
        //JWT令牌生成
        if (user != null) {
            StpUtil.login(user.getId());
            Object token = StpUtil.getTokenInfo();
            //将信息存到redis
            Map<String, Object> map = new HashMap<>();
            map.put("id", user.getId());
            map.put("username", user.getUsername());
            map.put("token", StpUtil.getTokenValue());
            redisTemplate.opsForHash().put("user", user.getId(), map);

            return Result.success(token);
        } else {
            return Result.error("登录失败");
        }
    }
    @PostMapping("/islogin")
    public Result isLogin() {
        System.out.println( "Redis读取"+redisTemplate.opsForHash().get("user",1));
        return Result.success(StpUtil.isLogin());
    }
    @PostMapping("logout")
    public Result logout() {
        StpUtil.logout();
        return Result.success();
    }
    @PostMapping("/register")
    public Result register(@RequestBody TUser user) {
        Preconditions.checkNotNull(user.getUsername(), "账号不能为空");
        Preconditions.checkNotNull(user.getPassword(), "密码不能为空");
        log.info(JSON.toJSONString(user));
        boolean success =  tUserService.register(user);
         return success ? Result.success() : Result.error("账号已注册");
    }
}





