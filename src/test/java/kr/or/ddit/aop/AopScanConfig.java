package kr.or.ddit.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IboardService;


@Configuration // <context:annotation-config/> 역할을 @Configuration에서 담당한다
//@ComponentScan(basePackages = {"kr.or.ddit"},// <context:component-scan base-package="kr.or.ddit"> 부분
//			includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Aspect.class)) // <context:include-filter type="annotation" expression="org.aspectj.lang.annotation.Aspect"/> 부분
@EnableAspectJAutoProxy // <aop:aspectj-autoproxy/> 부분

public class AopScanConfig {
	
	@Bean
	public IboardDao boardDao() {
		return new BoardDao();
	}
	
	@Bean
	public IboardService boardService() {
		return new BoardService();
	}
}
