package com.fwantastic.example1;

import java.net.URLEncoder;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import common.commonUtils;

/**
 * PDF변환 처리
 * 엑셀파일을 PDF파일로 변환하는 배치프로그램
 * 프로퍼티파일에 지정된 경로의 폴더 안의 파일이 대상
 */
public class pdfBox implements Tasklet {
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
        commonUtils.outLog(pdfBox.class, "INFO", "PDF변환 처리 작업 시작");
        try {
        	//Map<String, String> propertyMap = commonUtils.getProperties("ExcelToPdf.properties");
        	
        	
        	// 문서 만들기

        	PDDocument doc = new PDDocument();
        	// 파일 다운로드 설정

        	response.setContentType("application/pdf");

        	String fileName = URLEncoder.encode("샘플PDF", "UTF-8");

        	response.setHeader("Content-Disposition", "inline; filename=" + fileName + ".pdf");



        	// PDF 파일 출력

        	doc.save(response.getOutputStream());

        	doc.close();



        	출처: https://offbyone.tistory.com/267 [쉬고 싶은 개발자]
            
        } catch(Exception e) {
        	System.out.println(e.getMessage());
        	e.printStackTrace();
        }
        
        commonUtils.outLog(pdfBox.class, "INFO", "PDF변환 처리 작업 완료");
        return null;
    }

}