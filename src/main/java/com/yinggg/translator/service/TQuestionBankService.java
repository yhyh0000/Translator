//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinggg.translator.service;

import java.io.IOException;
import java.util.ArrayList;

import com.yinggg.translator.entity.TQuestionBank;
import org.springframework.web.multipart.MultipartFile;

public interface TQuestionBankService {
    Boolean upload(MultipartFile var1, Integer var2) throws IOException;
    ArrayList<TQuestionBank> getArticleByBelong(String belong,String userId);
}