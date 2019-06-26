package kr.or.ddit.lprod.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.testenv.LogicTestEnv;

public class LprodDaoTest extends LogicTestEnv{
	
	@Resource(name="lprodDao")
	private IlprodDao lprodDao;
	
	/**
	 * 
	* Method : lprodPagingListTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : lprod 페이징 리스트 조회 테스트
	 */
	@Test
	public void lprodPagingListTest() {
		/***Given***/
		PageVo pageVo = new PageVo(1, 5);
		
		/***When***/
		List<LprodVo> lprodList = lprodDao.lprodPagingList(pageVo);

		/***Then***/
		assertEquals(5, lprodList.size());
	}
	
	/**
	 * 
	* Method : lprodCntTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : lprod 전체 수 조회 테스트
	 */
	@Test
	public void lprodCntTest() {
		/***When***/
		int lprodCnt = lprodDao.lprodCnt();

		/***Then***/
		assertEquals(9, lprodCnt);
	}

}
