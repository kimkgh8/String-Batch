package com.fwantastic.example1;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class autoExcuteMain {

	public static void main(String[] args) throws Exception {
		
		// MyThread인스턴스를 2개 만듭니다. 
		excuteThread t1 = new excuteThread();
        t1.start();
        
        System.out.println("YES");
	}

}

