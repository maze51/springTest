package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.model.UserVoValidator;
import kr.or.ddit.user.service.IuserService;
import kr.or.ddit.util.PartUtil;

@RequestMapping("/user")
@Controller // 1. 컨트롤러로 지정
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource(name = "userService")
	private IuserService userService; // 2. 주입받기

	/**
	 * 
	 * Method : userList 작성자 : PC10 변경이력 :
	 * 
	 * @param model
	 * @return Method 설명 : 사용자 전체 리스트
	 */
	@RequestMapping("/list") // 3. 메서드 만들기
	public String userList(Model model) { // 4. 메서드 인자 선언
		model.addAttribute("userList", userService.userList());

		return "user/userList"; // 5. view를 정한다(화면으로 forward했던 과정)
	}
	
	
	@RequestMapping("/userListExcel")
	public String userListExcel(Model model) {
		List<String> header = new ArrayList<String>();
		header.add("userId");
		header.add("name");
		header.add("alias");
		header.add("addr1");
		header.add("addr2");
		header.add("zipcd");
		header.add("birth");
		
		model.addAttribute("header", header);
		model.addAttribute("data", userService.userList());
		
		return "userExcelView";
	}

	
	/**
	 * 
	 * Method : userPagingList 작성자 : PC10 변경이력 :
	 * 
	 * @return Method 설명 : 사용자 페이징 리스트
	 */
	@RequestMapping("/pagingList")

	// 다른 방식
	public String userPagingList(PageVo pageVo, Model model) {
		logger.debug("pageVo : {}", pageVo);

		// 기존 방식
		// public String userPagingList(int page, int pageSize) { // 파라미터를 받는다. 하지만 보낸
		// 파라미터가 없으면?
		// public String userPagingList(Model model,
		// @RequestParam(name = "page", defaultValue = "1")int page,
		// @RequestParam(name = "pageSize", defaultValue = "10")int pageSize) { // 기본값을
		// 이렇게 부여할 수 있다(몇개 안 되면)
		// PageVo pageVo = new PageVo(page, pageSize);

		Map<String, Object> resultMap = userService.userPagingList(pageVo);
		List<UserVo> userList = (List<UserVo>) resultMap.get("userList");
		int paginationSize = (Integer) resultMap.get("paginationSize");

		model.addAttribute("userList", userList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVo", pageVo);

		//return "user/userPagingList";
		return "tiles.userPagingList";
	}

	/**
	 * 
	 * Method : user 작성자 : PC10 변경이력 :
	 * 
	 * @param userId
	 * @param model
	 * @return Method 설명 : 사용자 상세조회
	 */
	@RequestMapping("/user")
	public String user(String userId, Model model) {

		model.addAttribute("userVo", userService.getUser(userId));

		return "user/user"; // 앞 뒤 "/"유무에 주의! (application-context에 설정한 접두/접미어와 함께 생각할 것)
	}
	
	/**
	 * 
	* Method : userAjax
	* 작성자 : PC10
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 정보 json응답
	 */
	@RequestMapping("/userJson")
	public String userJson(String userId, Model model) {
	//public ModelAndView userJson(String userId, Model model) {
	//public View userJson(String userId, Model model) {
		
		//ModelAndView mav = new ModelAndView("jsonView");
		//mav.setViewName("jsonView");
		//mav.setView(new MappingJackson2JsonView()); 이건 별로
		
		model.addAttribute("userVo", userService.getUser(userId)); // 이것이 일반적인 형태

		return "jsonView";
		//return new MappingJackson2JsonView(); // 처리해줄 view를 우리가 직접 고른다. 다만 비효율적이라 잘 쓰이지 않는다. 객체를 매번 생성한다 - 메모리가 쌓인다
	}

	/**
	 * 
	 * Method : userForm 작성자 : PC10 변경이력 :
	 * 
	 * @return Method 설명 : 사용자 등록 화면
	 */
	@RequestMapping(path = "/form", method = RequestMethod.GET)
	public String userForm() {
		return "user/userForm";
	}

	/**
	 * 
	* Method : userForm
	* 작성자 : PC10
	* 변경이력 :
	* @param userVo
	* @param userId
	* @param profile
	* @param model
	* @return
	* Method 설명 : 사용자 등록
	 */
	//@RequestMapping(path = "/form", method = RequestMethod.POST)
	public String userForm(UserVo userVo, BindingResult result, String userId, MultipartFile profile, Model model) {
			// MultipartFile의 인자명을 name속성의 값과 동일한 값으로 바꾸면 @RequestPart("profile")는 필요없다 (바인딩된다)
		
		new UserVoValidator().validate(userVo, result); // validator 실행
		
		if(result.hasErrors()) // 검증된 에러가 있는지 체크
			return "user/userForm";
			
		String viewName = "";
		
		UserVo dbUser = userService.getUser(userId); // userVo.getUserId()로 인자 추가 없이 불러올 수도 있다
		
		if (dbUser == null) {
			// 파일 업로드 처리 (인자값에 @RequestPart("profile") MultipartFile file 추가)
			if (profile.getSize() > 0) {
				String fileName = profile.getOriginalFilename();
				String ext = PartUtil.getExt(fileName);

				String uploadPath = PartUtil.getUploadPath();
				// 기존의 if(uploadFolder.exists()){ 는 뺀다. 이미 체크했음
				String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
				userVo.setPath(filePath);
				userVo.setFilename(fileName);

				try {
					profile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
			int insertCnt = userService.insertUser(userVo);

			if (insertCnt == 1)
				viewName =  "redirect:/user/pagingList";
		} else {
			model.addAttribute("msg", "이미 존재하는 사용자입니다");
			viewName = userForm();
			return viewName;
		}
		return viewName;
	} // end of userForm
	
	/**
	 * 
	* Method : userFormJsr
	* 작성자 : PC10
	* 변경이력 :
	* @param userVo
	* @param userId
	* @param profile
	* @param model
	* @return
	* Method 설명 : 사용자 등록
	 */
	@RequestMapping(path = "/form", method = RequestMethod.POST)
	public String userFormJsr(@Valid UserVo userVo, BindingResult result, String userId, MultipartFile profile, Model model) {
			// MultipartFile의 인자명을 name속성의 값과 동일한 값으로 바꾸면 @RequestPart("profile")는 필요없다 (바인딩된다)
		
		if(result.hasErrors()) // 검증된 에러가 있는지 체크
			return "user/userForm";
			
		String viewName = "";
		
		UserVo dbUser = userService.getUser(userId); // userVo.getUserId()로 인자 추가 없이 불러올 수도 있다
		
		if (dbUser == null) {
			// 파일 업로드 처리 (인자값에 @RequestPart("profile") MultipartFile file 추가)
			if (profile.getSize() > 0) {
				String fileName = profile.getOriginalFilename();
				String ext = PartUtil.getExt(fileName);

				String uploadPath = PartUtil.getUploadPath();
				// 기존의 if(uploadFolder.exists()){ 는 뺀다. 이미 체크했음
				String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
				userVo.setPath(filePath);
				userVo.setFilename(fileName);

				try {
					profile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
			int insertCnt = userService.insertUser(userVo);

			if (insertCnt == 1)
				viewName =  "redirect:/user/pagingList";
		} else {
			model.addAttribute("msg", "이미 존재하는 사용자입니다");
			viewName = userForm();
			return viewName;
		}
		return viewName;
	} // end of userForm
	
	
	/**
	 * 
	* Method : profile
	* 작성자 : PC10
	* 변경이력 :
	* @param userId
	* @param request
	* @param response
	* @throws IOException
	* Method 설명 : 사용자 사진 응답 생성
	 */
	@RequestMapping("/profile")
	// void인 이유: 응답을 메서드 안에서 만든다. response객체를 가지고
	public String profile(String userId, Model model) throws IOException {
		
		// 사용자 정보(path)를 조회
		UserVo userVo = userService.getUser(userId);
		model.addAttribute("userVo", userVo);
		
		return "profileView";
	}
	
	/**
	 * 
	* Method : userModify
	* 작성자 : PC10
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 수정 화면 요청
	 */
	@RequestMapping(path = "/modify", method = RequestMethod.GET)
	public String userModify(String sendId, Model model) {
		model.addAttribute("userVo", userService.getUser(sendId)); // getUser해서 가져온 UserVo를 바로 넣어준 것
		return "user/userModify";
	}
	
	/**
	 * 
	* Method : userModify
	* 작성자 : PC10
	* 변경이력 :
	* @param userVo
	* @param profile
	* @param session
	* @param model
	* @param redirectAttributes
	* @return
	* Method 설명 : 사용자 정보 수정
	 */
	@RequestMapping(path = "/modify", method = RequestMethod.POST)
	public String userModify(UserVo userVo, MultipartFile profile, HttpSession session, Model model
							, RedirectAttributes redirectAttributes) { // redirectAttributes : 임시값을 넣어줄 수 있다 
		//추후 ajax 요청으로 분리
		//userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
		
		if(profile.getSize() > 0) {
			String fileName = profile.getOriginalFilename();
			String ext = PartUtil.getExt(fileName);
			
			String uploadPath = PartUtil.getUploadPath();
			
			String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
			
			userVo.setPath(filePath);
			userVo.setFilename(fileName);
			
			try {
				profile.transferTo(new File(filePath));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		int updateCnt = userService.updateUser(userVo);
		
		if(updateCnt == 1) {
			//session.setAttribute("msg", "등록되었습니다");
			redirectAttributes.addFlashAttribute("msg", "등록되었습니다");
			redirectAttributes.addAttribute("userId", userVo.getUserId()); // 파라미터를 자동으로 붙여준다(아니면 기존처럼 직접 쓴다)
			return "redirect:/user/user"; 
		} else 
			return userModify(userVo.getUserId(), model);
	}
}
