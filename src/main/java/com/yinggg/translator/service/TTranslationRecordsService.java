package com.yinggg.translator.service;

import com.yinggg.translator.entity.TTranslationRecords;

import java.util.ArrayList;

public interface TTranslationRecordsService {
    ArrayList<TTranslationRecords> queryHistoryByUserIdOrOrigOrTran(TTranslationRecords tTranslationRecords);

    int  addTranslate(TTranslationRecords tTranslationRecords);

    int updateTranslate(TTranslationRecords tTranslationRecords);
}
