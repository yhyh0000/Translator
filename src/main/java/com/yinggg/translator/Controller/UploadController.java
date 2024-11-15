
package com.yinggg.translator.Controller;

import com.yinggg.translator.entity.TUser;
import com.yinggg.translator.service.UploadService;
import com.yinggg.translator.utils.Result;
import java.io.IOException;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
    @Resource
    private UploadService uploadService;

    public UploadController() {
    }

    @PostMapping("uploadFile")
    public Result upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("userId") String userId) throws IOException {
        TUser tuser = new TUser();
        tuser.setId(Integer.valueOf(userId));
        boolean success = this.uploadService.upload(file, tuser.getId());
        return success ? Result.success("上传成功") : Result.error("上传失败");
    }

}
