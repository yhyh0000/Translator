package com.yinggg.translator.Controller;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    /**
     *
     * @param tuser
     * 接受username 和password字段
     * json请求方式
     */
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

    /**
     * 生成6位字母和数字组成的验证码
     *
     */
    @GetMapping("/generateCaptcha")
    public Result generateCaptcha(HttpServletResponse response) throws IOException {
        // 使用Hutool的CaptchaUtil创建一个验证码对象，指定宽度、高度以及验证码长度
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(120, 40, 6, 5);
        // 获取验证码文本内容
        String captchaCode = lineCaptcha.getCode();
        // 设置响应头，指定内容类型为PNG图片格式，以便客户端能正确识别并展示图片
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        // 将验证码图片写入响应输出流，发送给客户端
        lineCaptcha.write(response.getOutputStream());
        // 创建一个Map用于存放验证码文本（后续可用于前端验证等）
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("captchaCode", captchaCode);
        // 返回包含验证码相关信息的响应实体，状态码设置为200表示成功
        return Result.success(resultMap);
    }


    @PostMapping("/islogin")
    public Result isLogin() {
        System.out.println( "Redis读取"+redisTemplate.opsForHash().get("user",1));
        return Result.success(StpUtil.isLogin());
    }

    /**
     * 退出登录
     *
     */
    @PostMapping("logout")
    public Result logout() {
        StpUtil.logout();
        return Result.success();
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody TUser user) {
        Preconditions.checkNotNull(user.getUsername(), "账号不能为空");
        Preconditions.checkNotNull(user.getPassword(), "密码不能为空");
        log.info(JSON.toJSONString(user));
        boolean success =  tUserService.register(user);
         return success ? Result.success() : Result.error("账号已注册");
    }
}





