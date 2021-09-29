package com.fwantastic.example1;

import java.awt.Robot;
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
 * JAVA를 이용한 매크로 프로그램
 */
public class autoExcute implements Tasklet {
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
        commonUtils.outLog(autoExcute.class, "INFO", "매크로 시작");
        try {
        	Robot robot = new Robot();
        	
        	robot.mouseMove(40, 40);
        	
        	
            
        } catch(Exception e) {
        	System.out.println(e.getMessage());
        	e.printStackTrace();
        }
        
        commonUtils.outLog(autoExcute.class, "INFO", "매크로 완료");
        return null;
    }

}