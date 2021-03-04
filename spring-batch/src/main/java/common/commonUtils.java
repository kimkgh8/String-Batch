package common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Properties;

public class commonUtils {
	public static void getProperties(String file) {
		try {
			System.out.println("프로퍼티파일 읽기처리 Start");
			
			// 객체 생성
			Properties props = new Properties();
			
			// 파일 스트림에 담기
			FileInputStream fis = new FileInputStream(file);
			
			BufferedInputStream bi = new BufferedInputStream(fis);
			
			// 파일 로딩
			props.load(bi);
			
			
			System.out.println("프로퍼티파일 읽기처리 End");
			
		} catch (Exception e) {
			System.out.println("프로퍼티파일 읽기처리 Error");
			e.printStackTrace();
		}
	}
}
