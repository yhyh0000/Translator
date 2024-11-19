
package com.yinggg.translator.service.impl;

import com.yinggg.translator.mapper.UploadMapper;
import com.yinggg.translator.service.UploadService;
import com.yinggg.translator.utils.FileUtils;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadMapper uploadMapper;
    @Resource
    FileUtils fileUtils;
    public UploadServiceImpl() {
    }

    public String upload(MultipartFile file) throws IOException {
        log.info("开始解析文件");
        String  error = "未上传或文件格式不支持";

        if (file == null || file.isEmpty()) {
            return error;
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            return error;
        }

        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String originalText = null;

        if (suffixName.equals(".txt") || suffixName.equals(".doc")) {
            if (fileUtils == null) {
                throw new IllegalStateException("FileUtils is not initialized");
            }
            if(suffixName.equals(".txt")){
                originalText = this.fileUtils.TXTToText(file.getInputStream());
            }else {
                originalText = this.fileUtils.WordToText(file.getInputStream());
            }
            log.info(originalText);
            if (originalText == null) {
                return error;
            } else {
                return originalText;
            }
        } else {
            return error;
        }
    }
}
