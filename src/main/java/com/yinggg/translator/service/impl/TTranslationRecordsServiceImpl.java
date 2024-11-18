package com.yinggg.translator.service.impl;

import com.yinggg.translator.entity.SearchRequest;
import com.yinggg.translator.entity.TTranslationRecords;
import com.yinggg.translator.mapper.TTranslationRecordsDao;
import com.yinggg.translator.service.TTranslationRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.yinggg.translator.utils.AdvancedDatabaseFormatValidatorUtils.validateDatabaseFormat;

/**
 * 翻译记录表业务
 */
@Service
public class TTranslationRecordsServiceImpl implements TTranslationRecordsService {
    @Autowired
    TTranslationRecordsDao tTranslationRecordsDao;

    @Override
    public ArrayList<TTranslationRecords> queryHistoryByUserIdOrOrigOrTran(SearchRequest searchRequest) {
        int page = searchRequest.getPage();
        int size = searchRequest.getSize();

        // 检查页码是否小于1，如果是则设置为默认值1
        if (page < 1) {
            page = 1;
        }

        // 直接根据页码和每页记录数计算偏移量
        searchRequest.setPage((page - 1) * size);
        ArrayList<TTranslationRecords> tTranslationRecords = tTranslationRecordsDao.queryHistoryByUserIdOrOrigOrTran(searchRequest);
        if (tTranslationRecords.isEmpty()) {
            return new ArrayList<>();
        }
        return tTranslationRecords;
    }

    /*
        记得对数据格式进行验证
     */
    @Override
    public int addTranslate(TTranslationRecords tTranslationRecords) {
        if (!validateDatabaseFormat(tTranslationRecords)) {
            return 0;
        }

        return tTranslationRecordsDao.addTranslate(tTranslationRecords);

    }

    @Override
    public int updateTranslate(TTranslationRecords tTranslationRecords) {
        if (!validateDatabaseFormat(tTranslationRecords)) {
            return 0;
        }
        return tTranslationRecordsDao.updateTranslate(tTranslationRecords);
    }

    @Override
    public int deleteTranslate(Integer id) {
        if (id == null) {
            return 0;
        }
        return tTranslationRecordsDao.deleteTranslate(id);
    }
}
