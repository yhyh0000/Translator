
package com.yinggg.translator.service.impl;

import com.yinggg.translator.entity.TQuestionBank;
import com.yinggg.translator.mapper.TQuestionBankMapper;
import com.yinggg.translator.service.UploadService;
import com.yinggg.translator.utils.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private TQuestionBankMapper TQuestionBankMapper;
    @Resource
    FileUtils fileUtils;



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
        List<String[]> originalText = null;

        if (!(suffixName.equals(".txt") || suffixName.equals(".doc"))) {
            return false;
        }
        if (fileUtils == null) {
            throw new IllegalStateException("FileUtils is not initialized");
        }
        if (suffixName.equals(".txt")) {
            originalText = this.fileUtils.TXTToText(file.getInputStream());
        } else {
            originalText = this.fileUtils.WordToText(file.getInputStream());
        }

        log.info(String.valueOf(originalText));
        if (originalText.isEmpty()) {
            return false;
        }
        String[] englishSentences = originalText.get(0);
        String[] chineseTranslations = originalText.get(1);
        if(!(englishSentences.length == chineseTranslations.length)){
            return false;
        }


        // 将英文句子和对应的中文翻译组合成数组添加到结果列表
        for (int i = 0; i < englishSentences.length; i++) {
            // 创建TQuestionBank对象并上传数据到数据库，添加异常处理
            try {
                TQuestionBank tQuestionBank = new TQuestionBank(0, englishSentences[i], chineseTranslations[i], 1, "");
                this.TQuestionBankMapper.Upload(tQuestionBank);
            } catch (Exception e) {
                // 处理数据库操作异常，比如输出错误提示信息、记录日志等
                System.out.println("上传数据到数据库时出现异常：" + e.getMessage());
            }
        }

        return true;

    }
}
