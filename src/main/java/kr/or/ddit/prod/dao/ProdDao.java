package kr.or.ddit.prod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.prod.model.ProdVo;

@Repository
public class ProdDao implements IprodDao{
	
	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;
	
	/**
	 * 
	* Method : prodPagingList
	* 작성자 : PC10
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : prod 페이징 리스트 조회
	 */
	@Override
	public List<ProdVo> prodPagingList(PageVo pageVo) {
		return sqlSession.selectList("prod.prodPagingList", pageVo);
	}
	
	/**
	 * 
	* Method : prodCnt
	* 작성자 : PC10
	* 변경이력 :
	* @return
	* Method 설명 : prod 총 갯수 조회
	 */
	@Override
	public int prodCnt() {
		return sqlSession.selectOne("prod.prodCnt");
	}
	
}
