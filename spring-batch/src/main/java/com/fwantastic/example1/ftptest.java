package com.fwantastic.example1;

import java.io.File;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.spire.xls.FileFormat;
import com.spire.xls.Workbook;

import common.commonUtils;

/**
 * PDF변환 처리
 * 엑셀파일을 PDF파일로 변환하는 배치프로그램
 * 프로퍼티파일에 지정된 경로의 폴더 안의 파일이 대상
 */
public class ftptest implements Tasklet {
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
        commonUtils.outLog(ftptest.class, "INFO", "PDF변환 처리 작업 시작");
        try {
        	Map<String, String> propertyMap = commonUtils.getProperties("ExcelToPdf.properties");
        	
        	//대상디렉토리
        	File excelDir = new File(propertyMap.get("inputFilePath"));
        	File[] excelFiles = excelDir.listFiles();
        	
        	for (File targetFile:excelFiles) {
        		 // 확장자
        		 String extension = targetFile.getName().substring(targetFile.getName().lastIndexOf("."),targetFile.getName().length());
        		 // 확장자를 제거한 파일명
        		 String fileName = targetFile.getName().substring(0,targetFile.getName().lastIndexOf("."));
        		 
        		 Workbook wb = new Workbook();
        		 wb.loadFromFile(targetFile.getPath());
        		 //PDF로 저장함
        	     wb.saveToFile(propertyMap.get("outFilePath") + fileName+".pdf",FileFormat.PDF);
        		 
        		 /* 다른 방법
                 XSSFWorkbook xls_workbook = new XSSFWorkbook(input_excel);
                 
                 XSSFSheet worksheet = xls_workbook.getSheetAt(0);

                 Iterator<Row> rowIterator = worksheet.iterator();
                 
                 Document ouput_pdf = new Document();
                 PdfWriter.getInstance(ouput_pdf, new FileOutputStream(propertyMap.get("outFilePath") + fileName+".pdf"));
                 ouput_pdf.open();
                 
                 PdfPTable my_table = new PdfPTable(2);

                 PdfPCell table_cell;
                 
                 while (rowIterator.hasNext()) {
                 	Row row = rowIterator.next();
                 	Iterator<Cell> cellIterator = row.cellIterator();
                 	while (cellIterator.hasNext()) {
                 		Cell cell = cellIterator.next();
                 		switch (cell.getCellType()) {
                 		case Cell.CELL_TYPE_STRING:
                 			table_cell=new PdfPCell(new Phrase(cell.getStringCellValue()));
                 			my_table.addCell(table_cell);
                 			break;
                 		}
                 	}

                 }
                 ouput_pdf.add(my_table);
                 ouput_pdf.close();  
                 input_excel.close();
                 */
        		 
        		 
        		 
                 //File renameFile = new File(propertyMap.get("backupPath")+targetFile.getName()+".xlsx");
                 //targetFile.renameTo(renameFile);
        	}
            
        } catch(Exception e) {
        	System.out.println(e.getMessage());
        	e.printStackTrace();
        }
        
        commonUtils.outLog(ftptest.class, "INFO", "PDF변환 처리 작업 완료");
        return null;
    }

}