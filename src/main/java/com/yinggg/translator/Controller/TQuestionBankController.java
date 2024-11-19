package com.yinggg.translator.Controller;

import com.yinggg.translator.entity.TQuestionBank;
import com.yinggg.translator.service.impl.TQuestionBankServiceImpl;
import com.yinggg.translator.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController

public class TQuestionBankController {
    @Autowired
    TQuestionBankServiceImpl tQuestionBankService;
    @GetMapping("/getArticleByBelong")
    public Result getArticleByBelong(@RequestParam("belong") String belong){
        ArrayList<TQuestionBank> articleByBelong = tQuestionBankService.getArticleByBelong(belong);
        if (articleByBelong.isEmpty()) {
           return Result.error("获取失败");
        }
        return Result.success(articleByBelong);
    }
}
