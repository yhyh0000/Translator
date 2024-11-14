package com.yinggg.translator.mapper;

import com.yinggg.translator.entity.TTranslationRecords;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface TTranslationRecordsDao {
    ArrayList<TTranslationRecords> queryAll();
}
