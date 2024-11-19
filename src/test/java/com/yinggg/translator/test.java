package com.yinggg.translator;

import com.yinggg.translator.service.impl.UploadServiceImpl;
import com.yinggg.translator.utils.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public class test {


    UploadServiceImpl uploadService = new UploadServiceImpl();

    @Test
    void testDocumentProcessing() {
        try {
            uploadService.upload(null, 0);
            System.out.println("成功");
        } catch (IOException e) {
            System.out.println("失败"+e.getMessage());
            throw new RuntimeException(e);

        }
    }
}
