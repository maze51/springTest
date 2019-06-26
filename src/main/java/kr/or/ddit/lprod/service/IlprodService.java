package kr.or.ddit.lprod.service;

import java.util.Map;

import kr.or.ddit.paging.model.PageVo;

public interface IlprodService {
	
	/**
	 * 
	* Method : lprodPagingList
	* 작성자 : PC10
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : lprod 페이징 리스트 조회
	 */
	Map<String, Object> lprodPagingList(PageVo pageVo);
}
