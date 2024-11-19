
package com.yinggg.translator.Controller;

import com.yinggg.translator.entity.TUser;
import com.yinggg.translator.service.impl.TQuestionBankServiceImpl;
import com.yinggg.translator.utils.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@CrossOrigin
@RestController
public class UploadController {
    @Resource
    private TQuestionBankServiceImpl uploadServiceImpl;


    /**
     *
     * 用户上传文件 将文件里的文字识别返回前端由用户确认
     *  解析文章
     *
     *
     */
    @PostMapping("/fetchtext")
    public Result upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("userId") String userId) throws IOException {
        TUser tuser = new TUser();
        tuser.setId(Integer.valueOf(userId));
        List<String[]> data = this.uploadServiceImpl.upload(file, tuser.getId());
        if (data.isEmpty()) {
            Result.error("上传失败");
        }
        return  Result.success(data);

    }
    /**
     * 用户确认后上传数据库 前端请求数据为 id 英文 和 中文
     * 请求路径/uploadText
     */


}
