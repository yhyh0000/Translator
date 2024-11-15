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
    ArrayList<TTranslationRecords> getHistoryByUserId(Integer userId);

    /**
     * 添加错词记录
     * @param tTranslationRecords 实体对象
     * @return 受影响的行数
     */
    int  addTranslate(TTranslationRecords tTranslationRecords);

    /**
     * 更新错题记录
     * @param tTranslationRecords 实体对象
     * @return 受影响的行数
     */
    int updateTranslate(TTranslationRecords tTranslationRecords);
}
