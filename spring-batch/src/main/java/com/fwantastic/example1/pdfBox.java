package com.fwantastic.example1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import common.commonUtils;

/**
 * PDFBOX를 이용한 PDF파일의 텍스트 추출
 */
public class pdfBox implements Tasklet {
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
        commonUtils.outLog(pdfBox.class, "INFO", "PDF텍스트 추출 처리 작업 시작");
        
        String src = "D:\\study\\data\\test.pdf";

        String text = null;
        
        COSDocument cosDoc = null;
        try {
        	File file  = new File(src);
        	InputStream is = new FileInputStream(file);
        	cosDoc = parseDocument(is);
        	PDFTextStripper striper = new PDFTextStripper();
        	text = striper.getText(new PDDocument(cosDoc));
        	System.out.println(text);
        		
		} catch (Exception e) {
			e.printStackTrace();
		}
        commonUtils.outLog(pdfBox.class, "INFO", "PDF텍스트 추출 작업 완료");
        return null;
    }
    private static COSDocument parseDocument(InputStream is) throws IOException {
    	PDFParser parser = new PDFParser((RandomAccessRead) is);
    	parser.parse();
    	return parser.getDocument();
    		
    }


}