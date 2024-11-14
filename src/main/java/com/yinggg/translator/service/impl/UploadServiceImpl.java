
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
        String fileName = file.getOriginalFilename();
        String suffixName = null;
        String contxt = null;
        if (fileName != null) {
            suffixName = fileName.substring(fileName.lastIndexOf("."));
            if (suffixName.equals(".txt")) {
                contxt = this.fileUtils.WordToText(file.getInputStream());
            } else {
                if (!suffixName.equals(".doc")) {
                    return false;
                }

                contxt = this.fileUtils.WordToText(file.getInputStream());
            }

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
