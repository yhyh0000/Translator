package com.yinggg.translator;

import cn.hutool.json.JSONUtil;
import com.yinggg.translator.utils.QFanApiUtil;
import cn.hutool.core.lang.Dict;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class test {

    @Autowired
    private QFanApiUtil qFanApiUtil;

    @Test
    void testGetAccessToken() {
        // 调用getAccessToken方法获取访问令牌
        Dict response = qFanApiUtil.getAccessToken();

        // 打印返回结果，检查获取的令牌是否正确
        System.out.println("Response: " + response);

        // 断言结果
        assertNotNull(response);
        assertTrue(response.containsKey("access_token"), "Access token is missing in response");
        assertFalse(response.getStr("access_token").isEmpty(), "Access token is empty");
    }
    @Test
    void testCommonReq(){
        ArrayList messages = new ArrayList();
        messages.add(JSONUtil.createObj().set("role","user").set("content","我想去自驾游你有什么推荐的地方吗？"));
        Dict dict = qFanApiUtil.commonReq(messages);
        System.out.println(dict.getStr("result"));
    }
}
