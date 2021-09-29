package com.fwantastic.example1;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import common.commonUtils;

/**
 * JAVA를 이용한 매크로 프로그램
 */
public class autoExcute implements Tasklet {
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
        commonUtils.outLog(autoExcute.class, "INFO", "매크로 시작");
        try {
        	PointerInfo pI = MouseInfo.getPointerInfo();
            Point point = pI.getLocation();

	        int i = (int)point.getX();
	        int h = (int)point.getY();
            System.out.println(i);
            System.out.println(h);
            
            
        	Robot robot = new Robot();
        	
        	//텔레포트 버튼
        	robot.mouseMove(394, 550);
        	robot.mousePress(InputEvent.BUTTON1_MASK);
        	
        	//맵선택 버튼
        	robot.mouseMove(880, 584);
        	robot.mousePress(InputEvent.BUTTON1_MASK);
        	
        	//마우스 스크롤 한칸 아래
        	robot.mouseWheel(1);
        	
        	
        	//아벨로 위치 특정
        	robot.mouseMove(1123, 502);
        	robot.mousePress(InputEvent.BUTTON1_MASK);
        	
        	//이동버튼
        	robot.mouseMove(823, 569);
        	robot.mousePress(InputEvent.BUTTON1_MASK);
        	
        	//esc
        	robot.keyPress(KeyEvent.VK_ESCAPE);
        	
        	//아벨로 클릭
        	robot.mouseMove(806, 377);
        	robot.mousePress(InputEvent.BUTTON1_MASK);
        	
        	//수리버튼 클릭
        	robot.mouseMove(878, 483);
        	robot.mousePress(InputEvent.BUTTON1_MASK);
        	//바로 확인
        	robot.mousePress(InputEvent.BUTTON1_MASK);
        	//엔터
        	robot.keyPress(KeyEvent.VK_ENTER);
        	
        	//텔레포트 버튼
        	robot.mouseMove(394, 550);
        	robot.mousePress(InputEvent.BUTTON1_MASK);
        	
        	//맵선택 버튼
        	robot.mouseMove(880, 584);
        	robot.mousePress(InputEvent.BUTTON1_MASK);
        	
        	//마우스 스크롤 한칸 위
        	robot.mouseWheel(-1);
        	
        	//엘븐가드 위치
        	robot.mouseMove(805, 704);
        	robot.mousePress(InputEvent.BUTTON1_MASK);
        	
        	//이동버튼
        	robot.mouseMove(823, 569);
        	robot.mousePress(InputEvent.BUTTON1_MASK);
        	
        	//esc
        	robot.keyPress(KeyEvent.VK_ESCAPE);
        	
        	
        } catch(Exception e) {
        	System.out.println(e.getMessage());
        	e.printStackTrace();
        }
        
        commonUtils.outLog(autoExcute.class, "INFO", "매크로 완료");
        return null;
    }

}