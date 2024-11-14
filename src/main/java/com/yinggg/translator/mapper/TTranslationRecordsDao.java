package com.yinggg.translator.mapper;

import com.yinggg.translator.entity.TTranslationRecords;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface TTranslationRecordsDao {

    /**
     * 查询所有错题记录
     * @return 实例对象
     */
    ArrayList<TTranslationRecords> getHistory();
}
