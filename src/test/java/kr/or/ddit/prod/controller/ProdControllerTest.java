package kr.or.ddit.prod.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.testenv.ControllerTestEnv;

public class ProdControllerTest extends ControllerTestEnv{
	
	/**
	 * 
	* Method : prodPagingListTest
	* 작성자 : PC10
	* 변경이력 :
	* @throws Exception
	* Method 설명 : prod 페이징 리스트 테스트
	 */
	@Test
	public void prodPagingListTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/prod/pagingList")
							.param("page", "2").param("pageSize", "10")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		String viewName = mav.getViewName();
		List<ProdVo> prodList = (List<ProdVo>) mav.getModelMap().get("prodList");
		int paginationSize = (Integer)mav.getModelMap().get("paginationSize");
		PageVo pageVo = (PageVo) mav.getModelMap().get("pageVo");
		
		/***Then***/
		assertNotNull(prodList);
		assertEquals("prodPagingList", viewName);
		assertEquals(10, prodList.size());
	}
	
	/**
	 * 
	* Method : prodPagingListWithoutParameterTest
	* 작성자 : PC10
	* 변경이력 :
	* @throws Exception
	* Method 설명 : prod 페이징 리스트 테스트(파라미터 없이 호출)
	 */
	@Test
	public void prodPagingListWithoutParameterTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/prod/pagingList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		String viewName = mav.getViewName();
		List<ProdVo> prodList = (List<ProdVo>) mav.getModelMap().get("prodList");
		int paginationSize = (Integer)mav.getModelMap().get("paginationSize");
		PageVo pageVo = (PageVo) mav.getModelMap().get("pageVo");
		
		/***Then***/
		assertEquals("prodPagingList", viewName);
		assertEquals(10, prodList.size());
		assertEquals(8, paginationSize);
		
	}

}
