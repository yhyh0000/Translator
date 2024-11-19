
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
@Slf4j
@CrossOrigin
@RestController
public class UploadController {
    @Resource
    private UploadService uploadService;
    @PostMapping("/fetchtext")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("接受到了请求");
       String fetchtext = this.uploadService.upload(file);
       boolean success = fetchtext != null;
       return success ? Result.success(fetchtext) : Result.error("上传失败");
    }

}
