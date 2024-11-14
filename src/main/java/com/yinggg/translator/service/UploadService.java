//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinggg.translator.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    Boolean upload(MultipartFile var1, Integer var2) throws IOException;
}
