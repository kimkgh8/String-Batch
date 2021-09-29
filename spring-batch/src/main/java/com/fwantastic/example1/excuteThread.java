package com.fwantastic.example1;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class excuteThread extends Thread {
    public excuteThread() throws Exception{
    	
    	System.out.println("시작준비");
		Thread.sleep(3000);
		System.out.println("시작");
		Robot robot = new Robot();
    	
		
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_TAB);
		
    	//텔레포트 버튼
    	robot.mouseMove(394, 550);
    	robot.mousePress(InputEvent.BUTTON1_MASK);
    	robot.mouseRelease(InputEvent.BUTTON1_MASK);
    	
    	//맵선택 버튼
    	robot.mouseMove(880, 584);
    	robot.mousePress(InputEvent.BUTTON1_MASK);
    	robot.mouseRelease(InputEvent.BUTTON1_MASK);
    	
    	//마우스 스크롤 한칸 아래
    	robot.mouseWheel(1);
    	
    	
    	//아벨로 위치 특정
    	robot.mouseMove(1123, 502);
    	robot.mousePress(InputEvent.BUTTON1_MASK);
    	robot.mouseRelease(InputEvent.BUTTON1_MASK);
    	
    	//이동버튼
    	robot.mouseMove(823, 569);
    	robot.mousePress(InputEvent.BUTTON1_MASK);
    	robot.mouseRelease(InputEvent.BUTTON1_MASK);
    	
    	//esc
    	robot.keyPress(KeyEvent.VK_ESCAPE);
    	robot.keyRelease(KeyEvent.VK_ESCAPE);
    	
    	
    	//아벨로 클릭
    	robot.mouseMove(806, 377);
    	robot.mousePress(InputEvent.BUTTON1_MASK);
    	robot.mouseRelease(InputEvent.BUTTON1_MASK);
    	
    	//수리버튼 클릭
    	robot.mouseMove(878, 483);
    	robot.mousePress(InputEvent.BUTTON1_MASK);
    	robot.mouseRelease(InputEvent.BUTTON1_MASK);
    	//바로 확인
    	robot.mousePress(InputEvent.BUTTON1_MASK);
    	robot.mouseRelease(InputEvent.BUTTON1_MASK);
    	
    	//엔터
    	robot.keyPress(KeyEvent.VK_ENTER);
    	robot.keyRelease(KeyEvent.VK_ESCAPE);
    	
    	//텔레포트 버튼
    	robot.mouseMove(394, 550);
    	robot.mousePress(InputEvent.BUTTON1_MASK);
    	robot.mouseRelease(InputEvent.BUTTON1_MASK);
    	
    	//맵선택 버튼
    	robot.mouseMove(880, 584);
    	robot.mousePress(InputEvent.BUTTON1_MASK);
    	robot.mouseRelease(InputEvent.BUTTON1_MASK);
    	
    	//마우스 스크롤 한칸 위
    	robot.mouseWheel(-1);
    	
    	//엘븐가드 위치
    	robot.mouseMove(805, 704);
    	robot.mousePress(InputEvent.BUTTON1_MASK);
    	robot.mouseRelease(InputEvent.BUTTON1_MASK);
    	
    	//이동버튼
    	robot.mouseMove(823, 569);
    	robot.mousePress(InputEvent.BUTTON1_MASK);
    	robot.mouseRelease(InputEvent.BUTTON1_MASK);
    	
    	//esc
    	robot.keyPress(KeyEvent.VK_ESCAPE);
    	robot.keyRelease(KeyEvent.VK_ESCAPE);
    	
    	System.out.println("종료");
    }
	public void run(){
		
        for(int i = 0; i < 1; i ++){
            try {
                //컴퓨터가 너무 빠르기 때문에 수행결과를 잘 확인 할 수 없어서 Thread.sleep() 메서드를 이용해서 조금씩 
                //쉬었다가 출력할 수 있게한다. 
                Thread.sleep((int)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } 
    } 
}
