package common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.logging.log4j.Logger;

public class commonUtils {
	static String commonPropertyPath = "C:\\common\\Properties\\";
	
	/**
	 * getProperties메소드
	 * 프로퍼티파일로부터 값 가져오기.
	 * @param file
	 * @return Map
	 */
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
	
	public static void outLog(@SuppressWarnings("rawtypes") Class clazz, String logLevel, String message) {
		try {
			System.setProperty("log4j.configurationFile", "file:/C:/common/Properties/log4j2.xml");
			System.setProperty("log4j.configuratorClass", "org.apache.log4j.xml.DOMConfigurator");
			Logger logger = org.apache.logging.log4j.LogManager.getLogger(clazz.getName());
			switch (logLevel) {
			case "DEBUG":
				logger.debug(message);
				break;
			case "INFO":
				logger.info(message);
				break;
			case "WARN":
				logger.warn(message);
				break;
			case "ERROR":
				logger.error(message);
				break;
			case "FATAL":
				logger.fatal(message);
				break;
			default:
				logger.error("지정된 로그 레벨이 정의 되어 있지 않습니다.");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
