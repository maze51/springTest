package kr.or.ddit.typeConvert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class StringDateConverter implements Converter<String, Date>{
	
	private String pattern = "yyyy-MM-dd";
	
	@Override
	public Date convert(String source) {
		//여기서 logger를 찍어보면 application-ioc-type-convert의 두 가지 방법 중 어떤 것이 우선시되는지 파악할 수 있다
		
		// source : 2019-08-08 형태
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		Date dt = null;
		try {
			dt = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return dt;
	}
	
	// 외부에서 바꿀 수 있도록 setter 만들기
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	

}
