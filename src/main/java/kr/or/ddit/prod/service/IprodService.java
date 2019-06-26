package kr.or.ddit.prod.service;

import java.util.Map;

import kr.or.ddit.paging.model.PageVo;

public interface IprodService {
	
	/**
	 * 
	* Method : prodPagingList
	* 작성자 : PC10
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : prod 페이징 리스트 조회
	 */
	Map<String, Object> prodPagingList(PageVo pageVo);
	
}
