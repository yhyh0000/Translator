package com.yinggg.translator;

import com.yinggg.translator.service.impl.TQuestionBankServiceImpl;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class test {


    TQuestionBankServiceImpl uploadService = new TQuestionBankServiceImpl();

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
