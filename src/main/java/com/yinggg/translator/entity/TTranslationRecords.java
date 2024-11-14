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
    private int id;
    private int user_id;
    private String original_text;
    private String translated_text;
    private String source_language;
    private String target_language;
    private String translation_date;
    private String differences;
}
