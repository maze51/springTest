package kr.or.ddit.main.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;

public class MainControllerTest extends ControllerTestEnv{
	/**
	 * 
	* Method : mainViewTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : Main View 호출 테스트
	 * @throws Exception 
	 */
	@Test
	public void mainViewTest() throws Exception {
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/main")).andReturn(); // url을 설정했을 때 나올 결과
		ModelAndView mav = mvcResult.getModelAndView(); // 말 그대로 model과 view를 합쳐놓은 것(spring내부에서 이렇게 관리한다)
		String viewName = mav.getViewName();
		String userId = (String)mav.getModel().get("mainUserId"); // MainController에서 model.addAttribute("mainUserId", "brown");로 넣어준 값
		
		/***Then***/
		assertEquals("main", viewName);
		assertEquals("brown", userId);
	}
	
	@Test
	public void mainViewAndExpectTest() throws Exception {
		/***Given***/

		/***When***/
		// 위 test과정을 다르게 적은 것
		mockMvc.perform(get("/main"))
				.andExpect(status().isOk()) // 200이면 통과
				.andExpect(view().name("main"))
				.andExpect(model().attribute("mainUserId", "brown"));
		
		
		/***Then***/
	}

}
