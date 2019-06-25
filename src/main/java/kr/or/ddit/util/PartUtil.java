package kr.or.ddit.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PartUtil {
	
	private static final String UPLOAD_PATH = "d:\\springUpload\\";

	/**
	 * 
	* Method : getExt
	* 작성자 : PC10
	* 변경이력 :
	* @param fileName
	* @return
	* Method 설명 : 파일명에서 파일 확장자를 반환
	 */
	public static String getExt(String fileName) {
		String ext = "";
		// 다른 방법(결과는 동일)
		String[] splited = fileName.split("\\.");
		if(splited.length != 1) 
			ext = splited[splited.length-1];
		
		return ext.equals("") ? "" : "."+ext;
		
//		if(fileName.indexOf(".") == -1){
//			return "";
//		}
//		int lastDot = fileName.lastIndexOf(".");
//		
//		return fileName.substring(lastDot+1);
	}
	
	/**
	 * 
	* Method : checkUploadFolder
	* 작성자 : PC10
	* 변경이력 :
	* @param yyyy
	* @param mm
	* Method 설명 : 년, 월 업로드 폴더가 존재하는지 체크, 없을 경우 폴더 생성
	 */
	public static void checkUploadFolder(String yyyy, String mm){
		
		File yyyyFolder = new File(UPLOAD_PATH + yyyy);
		
		// 신규연도로 넘어갔을 때 해당 연도의 폴더를 생성한다
		if(!yyyyFolder.exists()){
			yyyyFolder.mkdir();
		}
		
		// 월에 해당하는 폴더가 있는지 확인
		File mmFolder = new File(UPLOAD_PATH + yyyy + File.separator + mm);
		if(!mmFolder.exists()){
			mmFolder.mkdir();
		}
	}
	
	/**
	 * 
	* Method : getUploadPath
	* 작성자 : PC10
	* 변경이력 :
	* @param yyyy
	* @param mm
	* @return
	* Method 설명 : 업로드 경로를 반환
	 */
	public static String getUploadPath(){
		// 업로드할 폴더 확인
		// 연도에 해당하는 폴더가 있는지, 연도 안에 월에 해당하는 폴더가 있는지 검색
		Date dt = new Date(); // 오늘 날짜 구하기
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMM");
		String yyyyMM = sdf1.format(dt); // Date를 String으로 변환
		String yyyy = yyyyMM.substring(0, 4);
		String mm = yyyyMM.substring(4, 6); // 연, 월을 문자열로 formatting
		
		checkUploadFolder(yyyy, mm); // 폴더체크를 함께 처리
		return UPLOAD_PATH + yyyy + File.separator + mm; // File.separator는 구분자(\\)
	}
	
}
