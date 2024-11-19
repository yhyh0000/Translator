
package com.yinggg.translator.mapper;

import com.yinggg.translator.entity.TQuestionBank;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TQuestionBankMapper {

//    @Insert("INSERT INTO t_question_bank VALUES (default,#{sourceText},#{targetText},#{userId},now()))")
    void Upload(TQuestionBank tQuestionBank);


}
