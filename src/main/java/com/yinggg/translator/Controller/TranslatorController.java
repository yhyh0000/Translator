package com.yinggg.translator.Controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONUtil;
import com.yinggg.translator.utils.QFanApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/api")
public class TranslatorController {
    @Autowired
    QFanApiUtil qFanApiUtil;

    @GetMapping("/test")
    public Dict test(@RequestParam("role") String role,@RequestParam("user") String user){

        ArrayList param = new ArrayList();
        param.add(JSONUtil.createObj().set("role","user").set("content","中国"));
        Dict dict = qFanApiUtil.commonReq(param);
        return dict;
    }
}
