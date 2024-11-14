package com.yinggg.translator.entity;

import lombok.Data;


/**
 * (t_translation_records)实体类
 *
 * @author makejava
 * @since 2024-11-14 14:24:59
 */
@Data
public class TTranslationRecords {
    /**
     *     id;  翻译记录 ID，自增长的主键
     *     user_id;   提交翻译请求的用户 ID，外键关联用户表
     *     original_text;   原文内容
     *     translated_text;   翻译后的内容
     *     source_language;   原文语言
     *     target_language;   目标翻译语言
     *     translation_date;  翻译时间，默认当前时间
     *     differences;   用户自行翻译与软件翻译结果的差异（可存储为特定格式，如 JSON）
     */
    private int id;
    private int user_id;
    private String original_text;
    private String translated_text;
    private String source_language;
    private String target_language;
    private String translation_date;
    private String differences;
}
