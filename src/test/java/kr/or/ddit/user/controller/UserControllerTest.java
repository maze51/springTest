package kr.or.ddit.user.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVo;

public class UserControllerTest extends ControllerTestEnv{

	/**
	 * 
	* Method : userListTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 사용자 전체 리스트 테스트
	 * @throws Exception 
	 */
	@Test
	public void userListTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/list")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		/***Then***/
		assertEquals("user/userList", mav.getViewName());
		assertEquals(109, ((List<UserVo>)mav.getModelMap().get("userList")).size());
	}
	
	/**
	 * 
	* Method : userPagingListTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 사용자 페이징 리스트 테스트
	 * @throws Exception 
	 */
	@Test
	public void userPagingListTest() throws Exception {
		/***Given***/
		/***When***/
		// 파라미터를 보낼 때 정상적으로 처리되는가?
		MvcResult mvcResult = mockMvc.perform(get("/user/pagingList")
							.param("page", "2").param("pageSize", "10")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		String viewName = mav.getViewName();
		List<UserVo> userList = (List<UserVo>) mav.getModelMap().get("userList");
		int paginationSize = (Integer)mav.getModelMap().get("paginationSize");
		PageVo pageVo = (PageVo) mav.getModelMap().get("pageVo");
		
		/***Then***/
		assertEquals("tiles.userPagingList", viewName);
		assertEquals(10, userList.size());
		assertEquals(11, paginationSize);
		assertEquals(2, pageVo.getPage());
		assertEquals(10, pageVo.getPageSize());
	}
	
	/**
	 * 
	* Method : userPagingListWithoutParameterTest
	* 작성자 : PC10
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 페이징 리스트 테스트(page, pageSize 파라미터 없이 호출)
	 */
	@Test
	public void userPagingListWithoutParameterTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/pagingList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		String viewName = mav.getViewName();
		List<UserVo> userList = (List<UserVo>) mav.getModelMap().get("userList");
		int paginationSize = (Integer)mav.getModelMap().get("paginationSize");
		PageVo pageVo = (PageVo) mav.getModelMap().get("pageVo");
		
		/***Then***/
		assertEquals("tiles.userPagingList", viewName);
		assertEquals(10, userList.size());
		assertEquals(11, paginationSize);
		
		// PageVo에 hashcode와 equals를 구현해서 객체간 값 비교. 아래 두 줄과 동일하다
		assertEquals(new PageVo(1, 10), pageVo); 
//		assertEquals(1, pageVo.getPage());
//		ssertEquals(10, pageVo.getPageSize());
	}
	
	/**
	 * 
	* Method : userTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 사용자 상세조회 테스트
	 * @throws Exception 
	 */
	@Test
	public void userTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/user").param("userId", "brown")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVo userVo = (UserVo) mav.getModelMap().get("userVo");
		
		/***Then***/
		assertEquals("user/user", viewName);
		assertEquals("브라운", userVo.getName());
		
	}
	
	/**
	 * 
	* Method : userFormTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 사용자 입력 화면 요청
	 * @throws Exception 
	 */
	@Test
	public void userFormTest() throws Exception {
		
		mockMvc.perform(get("/user/form"))
					.andExpect(view().name("user/userForm"));
	}
	
	/**
	 * 
	* Method : userFormPostSuccessTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 사용자 등록 테스트 (success 시나리오)
	 * @throws Exception 
	 */
	@Test
	public void userFormPostSuccessTest() throws Exception {
		// 이미 존재하는 사용자를 잘 처리하는가? 등록이 잘 처리되는가?	2개의 test 시나리오  
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/ryan.png");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));
		
		/***When***/
		mockMvc.perform(fileUpload("/user/form").file(file)
				.param("userId", "userTest1")
				.param("name", "대덕인")
				.param("alias", "별명")
				.param("addr1", "대전광역시 중구 중앙로76")
				.param("addr2", "영민빌딩")
				.param("zipcd", "34940")
				.param("birth", "2019-05-31")
				.param("pass", "userTest1234"))
			.andExpect(view().name("redirect:/user/pagingList"));
		
	}
	
	/**
	 * 
	* Method : userFormPostFailTest
	* 작성자 : PC10
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 등록 테스트 (fail 시나리오)
	 */
	@Test
	public void userFormPostFailTest() throws Exception {
		// 이미 존재하는 사용자를 잘 처리하는가? 등록이 잘 처리되는가?	2개의 test 시나리오  
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/ryan.png");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));
		
		/***When***/
		mockMvc.perform(fileUpload("/user/form").file(file)
				.param("userId", "user3") // 이미 존재하는 아이디
				.param("name", "대덕인")
				.param("alias", "별명")
				.param("pass", "userTest1234")
				.param("addr1", "대전광역시 중구 중앙로76")
				.param("addr2", "영민빌딩")
				.param("zipcd", "34940")
				.param("birth", "2019-05-31"))
			.andExpect(view().name("user/userForm"));
	}
	
	/**
	 * 
	* Method : profileTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 사용자 사진 응답 테스트
	 * @throws Exception 
	 */
	@Test
	public void profileTest() throws Exception { // 다른 테스트 방법이 딱히 없다
		mockMvc.perform(get("/user/profile").param("userId", "brown"))
						.andExpect(status().is(200));
	}
	
	/**
	 * 
	* Method : userModifyGetTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 사용자 수정 화면 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void userModifyGetTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/modify").param("sendId", "brown")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVo userVo = (UserVo) mav.getModelMap().get("userVo");

		/***Then***/
		assertEquals("user/userModify", viewName);
		assertEquals("브라운", userVo.getName());
	}
	
	/**
	 * 
	* Method : userModifyPostTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 사용자 정보 수정 테스트
	 * @throws Exception 
	 */
	@Test
	public void userModifyPostTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/ryan.png");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));
		
		/***When***/
		mockMvc.perform(fileUpload("/user/modify").file(file)
				.param("userId", "brown")
				.param("name", "대덕인")
				.param("alias", "별명")
				.param("pass", "userTest1234")
				.param("addr1", "대전광역시 중구 중앙로76")
				.param("addr2", "영민빌딩")
				.param("zipcd", "34940")
				.param("birth", "2019-05-31"))
			.andExpect(view().name("redirect:/user/user"));
	}
}
