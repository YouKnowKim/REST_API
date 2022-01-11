package com.example.user_demo.room;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	@Autowired
	private RoomDao roomDao;
	
	private static final String SAVE_PATH = "/upload";
	private static final String PREFIX_URL = "/upload/";
	
	public String[] restore(MultipartFile multipartFile) {
		String[] string = new String[2];
		
		try {
			String originalFileName = multipartFile.getOriginalFilename();
			String extName = originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
			Long size = multipartFile.getSize();


			// 서버에서 저장 할 파일 이름
			String systemFileName = getSaveFileName(extName);

			System.out.println("originFilename : " + originalFileName);
			System.out.println("extensionName : " + extName);
			System.out.println("size : " + size);
			System.out.println("systemFileName : " + systemFileName);

			string[0] = systemFileName;
			string[1] = originalFileName;

			// file저장
			writeFile(multipartFile, systemFileName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return string;
	}
	
	private String getSaveFileName(String extName) {
		String fileName="";
		
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;

		return fileName;
	}
	
	private boolean writeFile(MultipartFile multipartFile, String systemFileName) throws IOException {
		boolean result = false;
		byte[] data = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + systemFileName);
		fos.write(data);
		fos.close();
		
		return result;
	}
	
}





























