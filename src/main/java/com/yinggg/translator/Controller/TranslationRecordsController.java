package com.yinggg.translator.Controller;

import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.yinggg.translator.entity.TTranslationRecords;
import com.yinggg.translator.entity.TUser;
import com.yinggg.translator.service.impl.TTranslationRecordsServiceImpl;
import com.yinggg.translator.utils.JwtUtils;
import com.yinggg.translator.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/translation")
public class TranslationRecordsController {
    @Autowired
    TTranslationRecordsServiceImpl tTranslationRecordsService;

    /**
     *
     * @param userId
     * @return
     */
    @PostMapping("/history")
    public Result history(@RequestParam("userId") Integer userId) {

        ArrayList<TTranslationRecords> tTranslationRecords = tTranslationRecordsService.getHistoryByUserId(userId);
        System.out.println(ArrayUtil.toString(tTranslationRecords));
        if (tTranslationRecords.isEmpty()) {
            return Result.error("获取历史记录失败");
        }

        return Result.success(tTranslationRecords);
    }

    /**
     *
     * @param tTranslationRecords
     * @return
     */
    @PostMapping("/addTranslate")
    public Result addTranslate(@RequestBody TTranslationRecords tTranslationRecords){
        int result = tTranslationRecordsService.addTranslate(tTranslationRecords);
        if(result == 0){
            return Result.error("添加失败");
        }
        return Result.success("添加成功");
    }

}
