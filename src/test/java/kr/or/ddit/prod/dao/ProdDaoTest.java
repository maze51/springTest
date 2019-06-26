package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.testenv.LogicTestEnv;

public class ProdDaoTest extends LogicTestEnv{

	@Resource(name="prodDao")
	private IprodDao prodDao;
	
	/**
	 * 
	* Method : prodPagingListTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : prod 페이징 리스트 조회 테스트
	 */
	@Test
	public void prodPagingListTest() {
		/***Given***/
		PageVo pageVo = new PageVo(1, 10);
		
		/***When***/
		List<ProdVo> prodList = prodDao.prodPagingList(pageVo);
		
		/***Then***/
		assertNotNull(prodList);
		assertEquals(10, prodList.size());
	}
	
	/**
	 * 
	* Method : prodCntTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : prod 전체 수 조회 테스트
	 */
	@Test
	public void prodCntTest() {
		/***When***/
		int prodCnt = prodDao.prodCnt();
		
		/***Then***/
		assertEquals(74, prodCnt);
	}

}
