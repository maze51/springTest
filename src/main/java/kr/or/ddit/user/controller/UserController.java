package kr.or.ddit.user.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IuserService;

@Controller // 1. 컨트롤러로 지정
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="userService")
	private IuserService userService; // 2. 주입받기
	
	/**
	 * 
	* Method : userList
	* 작성자 : PC10
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 사용자 전체 리스트
	 */
	@RequestMapping("/userList") // 3. 메서드 만들기
	public String userList(Model model) { // 4. 메서드 인자 선언
		model.addAttribute("userList", userService.userList());
		
		return "user/userList"; // 5. view를 정한다(화면으로 forward했던 과정)
	}
	
	/**
	 * 
	* Method : userPagingList
	* 작성자 : PC10
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 페이징 리스트
	 */
	@RequestMapping("/userPagingList")
	
	// 다른 방식
	public String userPagingList(PageVo pageVo, Model model) {
		logger.debug("pageVo : {}", pageVo);
		
	// 기존 방식
	//  public String userPagingList(int page, int pageSize) { // 파라미터를 받는다. 하지만 보낸 파라미터가 없으면?
	//	public String userPagingList(Model model,
	//								 @RequestParam(name = "page", defaultValue = "1")int page, 
	//								 @RequestParam(name = "pageSize", defaultValue = "10")int pageSize) { // 기본값을 이렇게 부여할 수 있다(몇개 안 되면)
	//		PageVo pageVo = new PageVo(page, pageSize);
		
		Map<String, Object> resultMap = userService.userPagingList(pageVo);
		List<UserVo> userList = (List<UserVo>)resultMap.get("userList");
		int paginationSize = (Integer)resultMap.get("paginationSize");
		
		model.addAttribute("userList", userList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVo", pageVo);
		
		return "user/userPagingList";
	}
}
