package com.fwantastic.example1;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import common.commonUtils;

/**
 * PDF변환 처리 엑셀파일을 PDF파일로 변환하는 배치프로그램 프로퍼티파일에 지정된 경로의 폴더 안의 파일이 대상
 */
public class ftptest implements Tasklet {
	public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
		commonUtils.outLog(ftptest.class, "INFO", "PDF변환 처리 작업 시작");
		FTPClient ftpClient = null;
		try {
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("euc-kr"); // 한글파일명 때문에 디폴트 인코딩을 euc-kr로 합니다
			ftpClient.connect("user.chollian.net"); // 천리안 FTP에 접속합니다

			int reply = ftpClient.getReplyCode(); // 응답코드가 비정상이면 종료합니다
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				System.out.println("FTP server refused connection.");
			} else {
				System.out.print(ftpClient.getReplyString()); // 응답 메세지를 찍어봅시다

				ftpClient.setSoTimeout(10000); // 현재 커넥션 timeout을 millisecond 값으로 입력합니다
				ftpClient.login("TEST", "PASSWORD"); // 로그인 유저명과 비밀번호를 입력 합니다

				// 목록보기 구현

				FTPFile[] ftpfiles = ftpClient.listFiles("/public"); // public 폴더의 모든 파일을 list 합니다
				if (ftpfiles != null) {
					for (int i = 0; i < ftpfiles.length; i++) {
						FTPFile file = ftpfiles[i];
						System.out.println(file.toString()); // file.getName(), file.getSize() 등등..
					}
				}

				ftpClient.logout();
			}

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			if (ftpClient != null && ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
					commonUtils.outLog(ftptest.class, "INFO", "PDF변환 처리 작업 완료");
					return null;
				} catch (IOException ioe) {
					ioe.printStackTrace();
					return null;
					
				}
			} else {
				return null;
			}
			
		}
	}
}