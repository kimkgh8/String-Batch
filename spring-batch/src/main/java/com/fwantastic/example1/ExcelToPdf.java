package com.fwantastic.example1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import common.commonUtils;

/**
 * 두 개의 메세지만 출력하고 끝나는 단순한 tasklet.
 */
public class ExcelToPdf implements Tasklet {

    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
        System.out.println("작업 시작...");
        
        try {
        	
        	Map<String, String> propertyMap = commonUtils.getProperties("ExcelToPdf.properties");
        	
        	//대상디렉토리
        	File excelDir = new File(propertyMap.get("inputFilePath"));
        	File[] excelFiles = excelDir.listFiles();
        	
        	for (File targetFile:excelFiles) {
        		 FileInputStream input_excel = new FileInputStream(targetFile);
        		 // 확장자
        		 String extension = targetFile.getName().substring(targetFile.getName().lastIndexOf("."),targetFile.getName().length());
        		 // 확장자를 제거한 파일명
        		 String fileName = targetFile.getName().substring(0,targetFile.getName().lastIndexOf("."));
        		 System.out.println("파일명 : " + fileName);
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
                 		switch (cell.getCellTypeEnum()) {
                 		case STRING:
                 			table_cell=new PdfPCell(new Phrase(cell.getStringCellValue()));
                 			my_table.addCell(table_cell);
                 			break;
                 		}
                 	}

                 }
                 ouput_pdf.add(my_table);
                 ouput_pdf.add(new Chunk(""));

                 ouput_pdf.close();  
                 input_excel.close();
                 //File renameFile = new File(propertyMap.get("backupPath")+targetFile.getName()+".xlsx");
                 //targetFile.renameTo(renameFile);
        	}
            
        } catch(Exception e) {
        	System.out.println(e.getMessage());
        	e.printStackTrace();
        }
        
        System.out.println("작업 완료!");
        return null;
    }

}