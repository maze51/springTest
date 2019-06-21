package kr.or.ddit.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ProfilingAspect {
	private static final Logger logger = LoggerFactory.getLogger(ProfilingAspect.class);
	
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy() {} // 의미없는 메서드
	
	@Around("dummy()") // 괄호 안은 Pointcut
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		long before = System.currentTimeMillis();
		logger.debug("beforeTime: {}", before);
		logger.debug("Profiling Aspect around method before");
		
		Object[] methodArgs = joinPoint.getArgs();
		Object returnObj = joinPoint.proceed(methodArgs);
		
		logger.debug("Profiling Aspect around method after");
		long after = System.currentTimeMillis();
		logger.debug("afterTime: {}", after);
		logger.debug("between : {}ms" , after - before);
		
		return returnObj;
	}

}
