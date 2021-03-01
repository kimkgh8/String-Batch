package com.fwantastic.example1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * 두 개의 메세지만 출력하고 끝나는 단순한 tasklet.
 */
public class ExcelToPdf implements Tasklet {

    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
        System.out.println("작업 시작...");
        
        File excelFile = new File("C:\\Users\\kim\\Documents\\Batch\\input\\고정파일명");
        
        FileInputStream input_excel = new FileInputStream(excelFile);
        
        HSSFWorkbook xls_workbook = new HSSFWorkbook(input_excel);
        
        HSSFSheet worksheet = xls_workbook.getSheetAt(0);

        Iterator<Row> rowIterator = worksheet.iterator();
        
        Document ouput_pdf = new Document();
        
        PdfWriter.getInstance(ouput_pdf, new FileOutputStream("Excel2PDF_Output.pdf"));
        
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

        ouput_pdf.close();  
        input_excel.close();
        	
        
        System.out.println("작업 완료!");
        return null;
    }

}