package kr.or.ddit.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.user.model.UserVo;

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
@SessionAttributes("rangers")
@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	// 메서드에 적용된 @ModelAttribute
	// @RequestMapping이 붙은 메서드가 실행될 때(요청이 처리될 때)
	// @ModelAttribute가 적용된 메서드가 리턴하는 값을 Model객체에 자동적으로 넣어준다
	// localhost/main ==> @RequestMapping("/main") : mainView ==> Model에는 rangers속성이 들어가 있다
	// localhost/mainMav ==> @RequestMapping("/mainMav") : mainViewMav ==> (동일하게)Model에는 rangers속성이 들어가 있다
	
	@ModelAttribute("rangers")
	public List<String> rangers(){
		
		logger.debug("{}", "rangers()");
		
		// 반환할 List객체를 임의로 만든다
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("sally");
		rangers.add("moon");
		rangers.add("james");
		
		return rangers;
	}
	
	/**
	 * 
	* Method : mainView
	* 작성자 : PC10
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : main페이지 요청(viewName 사용)
	 */
	@RequestMapping("/main") // /main url로 요청이 오면 이 메서드를 호출하도록 설정
	public String mainView(Model model, @ModelAttribute("userVo")UserVo userVo) {
		logger.debug("mainView");
		
		logger.debug("model.asMap().get(\"rangers\") : {}", model.asMap().get("rangers")); // List의 toString()으로 출력된다
		
		logger.debug("userVo : {}", userVo);
		
		userVo.setName("브라운");
		
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
	
	/**
	 * 
	* Method : mainViewMav
	* 작성자 : PC10
	* 변경이력 :
	* @return
	* Method 설명 : main페이지 요청(ModelAndView 사용)
	 */
	@RequestMapping("/mainMav")
	public ModelAndView mainViewMav(@ModelAttribute("rangers") List<String> rangers) {
		
		logger.debug("mainViewMav : {}", rangers); // List의 toString()으로 출력된다
		
		// viewName을 기반으로 ModelAndView객체를 생성하는 형태
		ModelAndView mav = new ModelAndView("main"); // mav.setViewName("otherMain");처럼 viewName을 바꿔줄 수도 있다
		
		// 위 문장은 아래 두 문장과 같다
		// ModelAndView mav = new ModelAndView();
		// mav.setViewName("main");
		
		mav.addObject("mainUserId", "brown");
		
		return mav;
	}
	
	/**
	 * 
	* Method : pathvariable
	* 작성자 : PC10
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : pathvariable로부터 사용자 아이디 가져오기(예: 도서관 사업소)
	 */
	// localhost/main/pathvariable/brown
	// localhost/main/pathvariable/sally
	// 마지막 path 값을 변수처럼 사용하기
	@RequestMapping("/main/pathvariable/{userId}")
	public String pathvariable(@PathVariable("userId") String userId) {
		logger.debug("userId : {}", userId);
		
		return "main"; // main view를 리턴
	}
	
//	@RequestMapping("/main/pathvariable")
//	public String pathvariable(String userId) {		이런 식으로 받을 수도 있다
	
	/**
	 * 
	* Method : header
	* 작성자 : PC10
	* 변경이력 :
	* @param agent
	* @return
	* Method 설명 : 헤더 정보 가져오기
	 */
	@RequestMapping("/main/header")
	public String header(@RequestHeader(name="Accept"/*, required=false*/) String accept) {
		logger.debug("Accept : {}", accept);
		
		return "main";
	}
}
