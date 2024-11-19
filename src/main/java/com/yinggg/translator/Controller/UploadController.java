
package com.yinggg.translator.Controller;

import com.yinggg.translator.entity.TUser;
import com.yinggg.translator.service.impl.TQuestionBankServiceImpl;
import com.yinggg.translator.utils.Result;

import java.io.IOException;
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
     *
     *
     *
     */
    @PostMapping("/fetchtext")
    public Result upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("userId") String userId) throws IOException {
        TUser tuser = new TUser();
        tuser.setId(Integer.valueOf(userId));
        boolean success = this.uploadServiceImpl.upload(file, tuser.getId());
        return success ? Result.success("上传成功") : Result.error("上传失败");

    }
    /**
     * 用户确认后上传数据库
     */


}
