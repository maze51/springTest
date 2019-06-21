package kr.or.ddit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	public void before(JoinPoint joinPoint) {
		logger.debug("Logging Aspect before method");
	}
	
	public void after(JoinPoint joinPoint) {
		logger.debug("Logging Aspect after method");
	}
	
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		// business logic 실행 전
		logger.debug("Logging Aspect around method before");
		
		// business logic 실행
		logger.debug("method name : {}", joinPoint.getSignature().getName()); // Signature는 메서드 Signature
		
		Object[] methodArgs = joinPoint.getArgs(); // 메서드 인자를 받는다
		Object returnObj = joinPoint.proceed(methodArgs); // 메서드 실행. 반환값이 있을 경우 받아준다
		
		// business logic 실행 후
		logger.debug("Logging Aspect around method after");
		
		return returnObj;
	}
}
