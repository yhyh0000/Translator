
package com.yinggg.translator.mapper;

import com.yinggg.translator.entity.TQuestionBank;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface TQuestionBankMapper {

//    @Insert("INSERT INTO t_question_bank VALUES (default,#{sourceText},#{targetText},#{userId},now()))")
    void addQuestion(TQuestionBank tQuestionBank);

    ArrayList<TQuestionBank> getArticleByBelong(String belong);

}
