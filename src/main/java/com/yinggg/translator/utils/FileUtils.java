
package com.yinggg.translator.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class FileUtils {
    public String WordToText(InputStream inputStream) throws IOException {
        XWPFDocument document = new XWPFDocument(inputStream);
        StringBuilder content = new StringBuilder();
        Iterator var4 = document.getParagraphs().iterator();

        while(var4.hasNext()) {
            XWPFParagraph paragraph = (XWPFParagraph)var4.next();
            content.append(paragraph.getText()).append("\n");
        }

        return content.toString();
    }

    public String TXTToText(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder content = new StringBuilder();

        String line;
        while((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }

        return content.toString();
    }
}
