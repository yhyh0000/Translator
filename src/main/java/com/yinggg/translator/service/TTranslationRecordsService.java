package com.yinggg.translator.service;

import com.yinggg.translator.entity.TTranslationRecords;

import java.util.ArrayList;

public interface TTranslationRecordsService {
    ArrayList<TTranslationRecords> getHistoryByUserId(Integer userId);

    int  addTranslate(TTranslationRecords tTranslationRecords);
}
