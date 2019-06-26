package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.lprod.dao.IlprodDao;
import kr.or.ddit.paging.model.PageVo;

@Service
public class LprodService implements IlprodService{
	
	@Resource(name="lprodDao")
	private IlprodDao lprodDao;
	
	/**
	 * 
	* Method : lprodPagingList
	* 작성자 : PC10
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : lprod 페이징 리스트 조회
	 */
	@Override
	public Map<String, Object> lprodPagingList(PageVo pageVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lprodList", lprodDao.lprodPagingList(pageVo));
		
		int lprodCnt = lprodDao.lprodCnt();
		
		int paginationSize = (int) Math.ceil((double)lprodCnt/pageVo.getPageSize());
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}

}
