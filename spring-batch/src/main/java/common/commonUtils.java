package common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class commonUtils {
	static String commonPropertyPath = "C:\\common\\Properties\\";
	public static Map<String, String> getProperties(String file) {
		Map<String, String> result = new HashMap<String, String>();
		
		try {
			System.out.println("프로퍼티파일 읽기처리 Start");
			
			File targetPropsFile = new File(commonPropertyPath + file);
			
			if (!targetPropsFile.exists()) {
				throw new Exception("파일이 존재하지 않습니다");
			}
			
			// 객체 생성
			Properties props = new Properties();
			
			// 파일 스트림에 담기
			FileInputStream fis = new FileInputStream(commonPropertyPath + file);
			
			BufferedInputStream bi = new BufferedInputStream(fis);
			
			// 파일 로딩
			props.load(bi);
			
			for (Map.Entry<Object, Object> propEntry : props.entrySet()) {
				//[] bytes = propEntry.getValue().toString().getBytes("UTF-8");
				System.out.println("키: "+propEntry.getKey().toString() + " 값: "+ propEntry.getValue().toString());
				result.put(propEntry.getKey().toString(), propEntry.getValue().toString());
			}
			System.out.println("프로퍼티파일 읽기처리 End");
		} catch (Exception e) {
			System.out.println("프로퍼티파일 읽기처리 Error");
			e.printStackTrace();
		}
		
		return result;
	}
}
