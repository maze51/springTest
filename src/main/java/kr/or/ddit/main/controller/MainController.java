package kr.or.ddit.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
	servlet에서는
		- extends HttpServlet
		- servlet-mapping (web.xml, @WebServlet)
		- service method 호출 후 => doXXX
		
	spring에서는
		- POJO(Plain Old Java Object), @Controller
		- @RequestMapping (class 또는 method에 적용)
		- @RequestMapping에 설정한 url method 호출
 */

@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping("/main") // /main url로 요청이 오면 이 메서드를 호출하도록 설정
	public String mainView(Model model) {
		logger.debug("mainView");
		
		// prefix : /WEB-INF/views/
		// suffix : .jsp (이렇게 application-context에서 설정했었음)
		
		// prefix + viewName + suffix
		// /WEB-INF/views/main.jsp (최종적으로 이렇게 완성된다)
		
		// 아래 문장은 다음과 동일하다
		// request.setAttribute("mainUserId", "brown"); // 인자를 HttpServletRequest request 설정하면 동일하게 작동된다
		model.addAttribute("mainUserId", "brown");
		
		// 이 반환값이 viewName
		return "main";
	}
}
