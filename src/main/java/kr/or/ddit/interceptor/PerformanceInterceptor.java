package kr.or.ddit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PerformanceInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(PerformanceInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// true를 리턴하면 다음 인터셉터 혹은 (인터셉터가 없을 경우) controller로 요청이 간다
		// false를 리턴하면 요청이 중단된다
		logger.debug("preHandle : {}", request.getRequestURI());
		
		request.setAttribute("start", System.currentTimeMillis()); 
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		long start = (Long)request.getAttribute("start"); // 해당 요청에 대한 start 타임을 정확하게 가져올 수 있다(클래스변수 선언-사용은 같은 요청간 차이인지 아닌지 정확히 알 수 없다)
		long end = System.currentTimeMillis();
		
		logger.debug("diff : {}", end - start); // 
		logger.debug("postHandle : {}", request.getRequestURI());
	}
	
	
}
