
package com.yinggg.translator.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.yinggg.translator.entity.TQuestionBank;
import com.yinggg.translator.mapper.TQuestionBankMapper;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class FileUtils {

    @Autowired
    TQuestionBankMapper TQuestionBankMapper;

    /**
     * 处理Word文件
     * @param inputStream
     * @return 返回中英两个数组列表
     * @throws IOException
     */
    public List<String[]> WordToText(InputStream inputStream) throws IOException {
        XWPFDocument document = new XWPFDocument(inputStream);
        StringBuilder content = new StringBuilder();
        Iterator var4 = document.getParagraphs().iterator();

        while (var4.hasNext()) {
            XWPFParagraph paragraph = (XWPFParagraph) var4.next();
            content.append(paragraph.getText()).append("\n");
        }

        return processDocument(content.toString());
    }

    /**
     *  处理文本文件
     * @param inputStream
     * @return 返回中英两个数组列表
     * @throws IOException
     */
    public List<String[]> TXTToText(InputStream inputStream) throws IOException {

        String content = new String(inputStream.readAllBytes(), "UTF-8");
        inputStream.close();
//        List<String[]> strings = processDocument(content);
        return processDocument(content);

    }

    /**
     *  文本处理工具方法，用于将句子分段
     * @param content
     * @return 返回中英两个数组列表
     * @throws IOException
     */
    public List<String[]> processDocument(String content) throws IOException {
        List<String[]> result = new ArrayList<>();

        // 使用FileInputStream读取文件
//        FileInputStream fis = new FileInputStream(new File(filePath));
//        String content = IOUtils.toString(fis, Charset.forName("UTF-8"));
//        fis.close();

        // 处理换行及按照分隔符分割内容
        String[] contentSplit = content.split("(翻译：|翻译:)");

        if (contentSplit.length >= 2) {
            // 处理原始文本，去除两端空白字符并检查英文标点
            String originalText = containsEnglishPunctuation(contentSplit[0].trim());
            // 处理翻译文本，去除两端空白字符并检查中文标点
            String translatedText = containsChinesePunctuation(contentSplit[1].trim());

            // 用于临时存储英文句子和对应的中文翻译
            String[] englishSentences = originalText.split("-----");
            String[] chineseTranslations = translatedText.split("-----");

            result.add(englishSentences);
            result.add(chineseTranslations);
        } else {
            // 处理分割不符合预期的情况，比如输出错误提示信息等
            System.out.println("文件内容的分隔符格式不符合预期，请检查文件内容。");
        }

        return result;
    }

    // 处理字符串包含常见英文标点符号
    private static String containsEnglishPunctuation(String str) {
        return str.replaceAll("[\n\t\r]", "")
                .replaceAll("[.;?!\"'()]", "-----");
    }
    // 处理字符串包含常见中文标点符号
    private static String containsChinesePunctuation(String str) {
        return str.replaceAll("[\n\t\r]", "")
                .replaceAll("[。！；？]", "-----");
    }
}
