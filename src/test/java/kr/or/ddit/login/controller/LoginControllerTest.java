package kr.or.ddit.login.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVo;

public class LoginControllerTest extends ControllerTestEnv{
	
	/**
	 * 
	* Method : loginViewNotLoginedTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 접속하지 않은 상황에서 loginView 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void loginViewNotLoginedTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/login")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("login/login", viewName);
	}
	
	/**
	 * 
	* Method : loginViewLoginedTest
	* 작성자 : PC10
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 로그인 한 상황에서 loginView 요청 테스트
	 */
	@Test
	public void loginViewLoginedTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/login").sessionAttr("USER_INFO", new UserVo()))
								.andReturn(); // 속 빈 UserVo객체 생성 => main으로 이동하는지 테스트 가능
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("main", viewName);
	}
	
	/**
	 * 
	* Method : loginProcessSuccessTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 로그인 요청 처리 성공 테스트
	 * @throws Exception 
	 */
	@Test
	public void loginProcessSuccessTest() throws Exception {
		/***Given***/
		String userId = "brown";
		String password = "1234";

		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/login")
									.param("userId", userId)
									.param("password", password))
								.andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		HttpSession session = mvcResult.getRequest().getSession();
		
		String viewName = mav.getViewName();
		UserVo userVo = (UserVo) session.getAttribute("USER_INFO");
		
		/***Then***/
		assertEquals("main", viewName);
		assertNotNull(userVo); // session의 USER_INFO 검증
		assertEquals("brown", userVo.getUserId());
		assertEquals("곰곰곰곰곰", userVo.getAlias());
	}
	
	/**
	 * 
	* Method : loginProcessFailTest
	* 작성자 : PC10
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 로그인 요청 실패 테스트
	 */
	@Test
	public void loginProcessFailTest() throws Exception {
		/***Given***/
		String userId = "brown";
		String password = "5678"; // 틀린 비밀번호

		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/login")
									.param("userId", userId)
									.param("password", password))
								.andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("login/login", viewName);
	}

}
