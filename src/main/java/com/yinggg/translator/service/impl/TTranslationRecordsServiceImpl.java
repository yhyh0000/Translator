package com.yinggg.translator.service.impl;

import com.yinggg.translator.entity.TTranslationRecords;
import com.yinggg.translator.mapper.TTranslationRecordsDao;
import com.yinggg.translator.service.TTranslationRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TTranslationRecordsServiceImpl implements TTranslationRecordsService {
    @Autowired
    TTranslationRecordsDao tTranslationRecordsDao;

    @Override
    public ArrayList<TTranslationRecords> queryAll() {
        return tTranslationRecordsDao.queryAll();
    }
}
