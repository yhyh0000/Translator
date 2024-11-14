package com.yinggg.translator.Controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.yinggg.translator.entity.TTranslationRecords;
import com.yinggg.translator.entity.TUser;
import com.yinggg.translator.service.impl.TTranslationRecordsServiceImpl;
import com.yinggg.translator.utils.JwtUtils;
import com.yinggg.translator.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/translation")
public class TranslationRecordsController {
    @Autowired
    TTranslationRecordsServiceImpl tTranslationRecordsService;

    @PostMapping("/history")
    public Result history() {

        ArrayList<TTranslationRecords> tTranslationRecords = tTranslationRecordsService.getHistory();
        if (tTranslationRecords.isEmpty()) {
            return Result.error("获取历史记录失败");
        }

        return Result.success(tTranslationRecords);
    }
}
