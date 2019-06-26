package kr.or.ddit.lprod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.service.IlprodService;
import kr.or.ddit.paging.model.PageVo;

@RequestMapping("/lprod")
@Controller
public class LprodController {
	
	@Resource(name = "lprodService")
	private IlprodService lprodService;
	
	/**
	 * 
	* Method : lprodPagingList
	* 작성자 : PC10
	* 변경이력 :
	* @param pageVo
	* @param model
	* @return
	* Method 설명 : lprod 페이징 리스트
	 */
	@RequestMapping("/pagingList")
	public String lprodPagingList(PageVo pageVo, Model model) {
		
		Map<String, Object> resultMap = lprodService.lprodPagingList(pageVo);
		List<LprodVo> lprodList = (List<LprodVo>) resultMap.get("lprodList");
		int paginationSize = (Integer) resultMap.get("paginationSize");
		
		model.addAttribute("lprodList", lprodList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVo", pageVo);
		
		return "lprodPagingList";
		
	}
}
