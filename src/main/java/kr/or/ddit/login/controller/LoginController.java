package kr.or.ddit.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IuserService;

@Controller
public class LoginController {
	
	@Resource(name="userService")
	private IuserService userService;
	
	/**
	 * 
	* Method : loginView
	* 작성자 : PC10
	* 변경이력 :
	* @param session
	* @return
	* Method 설명 : 사용자 로그인 화면 요청
	 */
	@RequestMapping(path = "/login", method = RequestMethod.GET) // get으로 요청 오면 메서드를 탄다
	public String loginView(HttpSession session) {
		if(session.getAttribute("USER_INFO")!=null)
			return "tiles.main"; // /WEB-INF/views/main.jsp
		else
			return "login/login"; // login 폴더의 login.jsp로 리턴하겠다(/WEB-INF/views/login/login.jsp)
		
		// prefix : /WEB-INF/views/
		// suffix : .jsp
	}
	
	/**
	 * 
	* Method : loginProcess
	* 작성자 : PC10
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 로그인 요청 처리
	 */
	@RequestMapping(path = "/login", method = RequestMethod.POST) // path= 부분을 의미있는 url로 바꿔주면 좋다(servlet과 달리 자유롭게 부여 가능)
	public String loginProcess(String userId, String password, String rememberme, 
			HttpServletResponse response, HttpSession session) { // 쿠기 저장을 위해 response를 인자로 받는다. 세션에 넣기 위해 session도 받는다
		
		String encryptPassword = KISA_SHA256.encrypt(password);
		//스캔해서 Service객체는 이미 만들어져 있다 : new 연산자로 다시 만들지 않음. 위에서 주입받으면 된다
		UserVo userVo = userService.getUser(userId);
		
		if(userVo != null && encryptPassword.equals(userVo.getPass())) {
			rememberMeCookie(userId, rememberme, response);
			
			session.setAttribute("USER_INFO", userVo);
			
			return "tiles.main"; // main으로 forward하는 과정
		}
		else
			return "login/login";
	}
	
	/**
	 * 
	* Method : rememberMeCookie
	* 작성자 : PC10
	* 변경이력 :
	* @param userId
	* @param rememberme
	* @param response
	* Method 설명 : rememberme 쿠키를 응답으로 생성
	 */
	private void rememberMeCookie(String userId, String rememberme, HttpServletResponse response) {
		int cookieMaxAge = 0;
		if(rememberme != null)
			cookieMaxAge = 60*60*24*30;
		
		Cookie userIdCookie = new Cookie("userId", userId);
		userIdCookie.setMaxAge(cookieMaxAge);
		
		Cookie rememberMeCookie = new Cookie("rememberme", "true");
		rememberMeCookie.setMaxAge(cookieMaxAge);
		
		response.addCookie(userIdCookie);
		response.addCookie(rememberMeCookie);
	}
}
