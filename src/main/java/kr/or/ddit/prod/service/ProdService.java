package kr.or.ddit.prod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.prod.dao.IprodDao;
import kr.or.ddit.prod.model.ProdVo;

@Service
public class ProdService implements IprodService{
	
	@Resource(name="prodDao")
	private IprodDao prodDao;
	
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
	public Map<String, Object> prodPagingList(PageVo pageVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("prodList", prodDao.prodPagingList(pageVo));
		
		int prodCnt = prodDao.prodCnt();
		
		int paginationSize = (int) Math.ceil((double)prodCnt/pageVo.getPageSize());
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}
}
