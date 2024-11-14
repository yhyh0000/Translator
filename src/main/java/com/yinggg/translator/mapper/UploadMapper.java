
package com.yinggg.translator.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UploadMapper {
    @Insert({"insert into  t_translation_records (original_text,id) values (#{context},#{id})"})
    void Upload(@Param("original_text") String var1, Integer var2);
}
