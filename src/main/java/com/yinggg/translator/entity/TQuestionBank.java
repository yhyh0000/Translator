package com.yinggg.translator.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TQuestionBank {
    private int id;
    private String sourceText;
    private String targetText;
    private int userId;
    private String uploadTime;

}
