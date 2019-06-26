package kr.or.ddit.lprod.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.testenv.ControllerTestEnv;

public class LprodControllerTest extends ControllerTestEnv{
	
	/**
	 * 
	* Method : lprodPagingListTest
	* 작성자 : PC10
	* 변경이력 :
	* @throws Exception
	* Method 설명 : lprod 페이징 리스트 테스트
	 */
	@Test
	public void lprodPagingListTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/pagingList")
							.param("page", "1").param("pageSize", "5")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		String viewName = mav.getViewName();
		List<LprodVo> lprodList = (List<LprodVo>) mav.getModelMap().get("lprodList");
		int paginationSize = (Integer)mav.getModelMap().get("paginationSize");
		PageVo pageVo = (PageVo) mav.getModelMap().get("pageVo");

		/***Then***/
		assertNotNull(lprodList);
		assertEquals("lprodPagingList", viewName);
		assertEquals(5, lprodList.size());
	}
	
	@Test
	public void lprodPagingListWithoutParameterTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/pagingList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		String viewName = mav.getViewName();
		List<LprodVo> lprodList = (List<LprodVo>) mav.getModelMap().get("lprodList");
		int paginationSize = (Integer)mav.getModelMap().get("paginationSize");
		PageVo pageVo = (PageVo) mav.getModelMap().get("pageVo");
		/***Then***/
		assertNotNull(lprodList);
		assertEquals("lprodPagingList", viewName);
		assertEquals(9, lprodList.size());
		
	}

}
