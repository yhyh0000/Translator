package com.yinggg.translator.service.impl;

import com.yinggg.translator.entity.TTranslationRecords;
import com.yinggg.translator.mapper.TTranslationRecordsDao;
import com.yinggg.translator.service.TTranslationRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 翻译记录表业务
 */
@Service
public class TTranslationRecordsServiceImpl implements TTranslationRecordsService {
    @Autowired
    TTranslationRecordsDao tTranslationRecordsDao;

    @Override
    public ArrayList<TTranslationRecords> queryHistoryByUserIdOrOrigOrTran(TTranslationRecords tTranslationRecords) {
        return tTranslationRecordsDao.queryHistoryByUserIdOrOrigOrTran(tTranslationRecords);
    }

    /*
        记得对数据格式进行验证
     */
    @Override
    public int addTranslate(TTranslationRecords tTranslationRecords) {
        return tTranslationRecordsDao.addTranslate(tTranslationRecords);
    }

    @Override
    public int updateTranslate(TTranslationRecords tTranslationRecords) {
        return tTranslationRecordsDao.updateTranslate(tTranslationRecords);
    }
}
