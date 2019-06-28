package kr.or.ddit.user.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserVoValidator implements Validator{

	// 목적: UserVo value객체 바인딩 과정에서 에러 발생 여부 체크
	@Override
	public boolean supports(Class<?> clazz) {
		return UserVo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// 데이터 검증
		UserVo userVo = (UserVo)target;
		
		// 사용자 아이디 길이가 4글자 이상인지 체크
		if(userVo.getUserId().length() <= 3)
			errors.rejectValue("userId", "length"); // 두 번째가 errorCode. 정하기 나름
		
		// 사용자 이름이 2글자 이상
		if(userVo.getName().length() < 2)
			errors.rejectValue("name", "length");
		
	}

}
