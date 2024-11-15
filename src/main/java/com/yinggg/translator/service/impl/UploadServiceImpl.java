
package com.yinggg.translator.service.impl;

import com.yinggg.translator.mapper.UploadMapper;
import com.yinggg.translator.service.UploadService;
import com.yinggg.translator.utils.FileUtils;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements UploadService {
    private FileUtils fileUtils;
    @Autowired
    private UploadMapper uploadMapper;

    public UploadServiceImpl() {
    }

    public Boolean upload(MultipartFile file, Integer id) throws IOException {
        if (file == null || file.isEmpty()) {
            return false;
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            return false;
        }

        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String contxt = null;

        if (suffixName.equals(".txt") || suffixName.equals(".doc")) {
            if (fileUtils == null) {
                throw new IllegalStateException("FileUtils is not initialized");
            }

            contxt = this.fileUtils.WordToText(file.getInputStream());

            if (contxt == null) {
                return false;
            } else {
                this.uploadMapper.Upload(contxt, id);
                return true;
            }
        } else {
            return false;
        }
    }
}
