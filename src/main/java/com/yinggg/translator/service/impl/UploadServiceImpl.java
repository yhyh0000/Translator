
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

    public Boolean upload(MultipartFile file, Integer id) throws IOException {
        log.info(String.valueOf(id));
        if (file == null || file.isEmpty()) {
            return false;
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            return false;
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
                return false;
            } else {
                this.uploadMapper.Upload(originalText, id);
                return true;
            }
        } else {
            return false;
        }
    }
}
