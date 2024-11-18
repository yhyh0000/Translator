package com.yinggg.translator.service;

import com.yinggg.translator.entity.SearchRequest;
import com.yinggg.translator.entity.TTranslationRecords;

import java.util.ArrayList;

public interface TTranslationRecordsService {
    ArrayList<TTranslationRecords> queryHistoryByUserIdOrOrigOrTran(SearchRequest searchRequest);

    int  addTranslate(TTranslationRecords tTranslationRecords);

    int updateTranslate(TTranslationRecords tTranslationRecords);

    int deleteTranslate(Integer id);
}
