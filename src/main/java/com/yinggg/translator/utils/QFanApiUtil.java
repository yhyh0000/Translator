package com.yinggg.translator.utils;

import cn.hutool.core.lang.Dict;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QFanApiUtil {

    private final String tokenUrl;
    private final String yi_34b_chatUrl;
    private final String apiKey;
    private final String secretKey;

    // 构造函数注入配置
    public QFanApiUtil(
            @Value("${QFan.access.token.url}") String tokenUrl,
            @Value("${QFan.model.url}") String yi_34b_chatUrl,
            @Value("${QFan.API.Key}") String apiKey,
            @Value("${QFan.Secret.Key}") String secretKey) {
        this.tokenUrl = tokenUrl;
        this.yi_34b_chatUrl = yi_34b_chatUrl;
        this.apiKey = apiKey;
        this.secretKey = secretKey;
    }

    public Dict getAccessToken() {
        try {
            if (tokenUrl == null || apiKey == null || secretKey == null) {
                System.out.println("配置值为空！");
                Dict errorDict = new Dict();
                errorDict.set("error", "配置值为空，请检查配置文件！");
                return errorDict;
            }

            // 发送HTTP请求获取访问令牌
            String requestBody = HttpRequest.post(tokenUrl + "?grant_type=client_credentials&" +
                            "client_id=" + apiKey + "&" +
                            "client_secret=" + secretKey)
                    .header(Header.CONTENT_TYPE, "application/json")
                    .execute()
                    .body();

            // 解析JSON响应
            JSONObject jsonObject = JSONUtil.parseObj(requestBody);
            String accessToken = jsonObject.getStr("access_token");

            Dict result = new Dict();
            result.set("code", "200");
            result.set("access_token", accessToken);
            result.set("massage", "成功获取access_token");

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Dict errorDict = new Dict();
            errorDict.set("error", "获取访问令牌失败：" + e.getMessage());
            return errorDict;
        }
    }

    public Dict commonReq(ArrayList messages) {

        try{
            String access_token = getAccessToken().getStr("access_token");
            if(access_token.isEmpty()){
                System.out.println("获取访问令牌失败");
                Dict errorDict = new Dict();
                errorDict.set("error", "获取访问令牌失败");
                return errorDict;
            }
            JSONObject data = JSONUtil.createObj();
            data.set("messages", messages);
            data.set("temperature",1.0);
            data.set("response_format","json_object");

            String requestBody = HttpRequest.post(yi_34b_chatUrl + access_token)
                    .header(Header.CONTENT_TYPE, "application/json")
                    .body(JSONUtil.toJsonStr(data))
                    .execute()
                    .body();
            JSONObject jsonObject = JSONUtil.parseObj(requestBody);
            jsonObject.getStr("result");
            Dict result = new Dict();
            result.set("code", "200");
            result.set("result", jsonObject.getStr("result"));
            return result;
        }catch (Exception e){
            e.printStackTrace();
            Dict errorDict = new Dict();
            errorDict.set("error", "发送失败：" + e.getMessage());
            return errorDict;
        }

    }
}
