package kr.or.ddit.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.ddit.user.dao.IuserDao;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(SessionCheckInterceptor.class);
	
	@Resource(name = "userDao")
	private IuserDao userDao;
	
	/**
	 * 
	* Method : preHandle
	* 작성자 : PC10
	* 변경이력 :
	* @param request
	* @param response
	* @param handler
	* @return
	* @throws Exception
	* Method 설명 : 로그인 한 사용자만 controller에 접근 가능하도록 체크
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		// 사용자 로그인 상태
		if(session.getAttribute("USER_INFO") != null)
			return true;
		// 로그인 상태가 아닌 경우 : 로그인 페이지로 이동
		else
			response.sendRedirect("/login"); // contextPath를 쓴다면 'request.getContextPath() + ...'식으로 써야 한다
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		logger.debug("##########################userDao : {}", userDao);
		request.setAttribute("userList", userDao.userList());
		
	}
	
	
	
}
