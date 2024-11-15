
package com.yinggg.translator.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UploadMapper {

        @Insert("INSERT INTO t_translation_records (original_text, id) VALUES (#{originalText}, #{id})")
        void Upload(@Param("originalText") String originalText, @Param("id") Integer id);


}
