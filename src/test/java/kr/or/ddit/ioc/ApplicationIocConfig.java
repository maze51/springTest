package kr.or.ddit.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IboardService;

// spring bean임을 알려주는 설정
@Configuration
public class ApplicationIocConfig {
	
	
	// <bean id="boardDao" class="kr.or.ddit.board.dao.BoardDao"/>
	
	// xml을 java방식으로
	@Bean
	public IboardDao boardDao() {
		return new BoardDao();
	}
	// new연산자가 들어갔지만 객체는 여러 번 만들어도 동일하게 생성된다
	// @Bean 붙은것을 spring container가 읽어 실행한다
	// method 이름이 bean의 이름이 된다 (기본값)
	// 단, method 이름을 annotation으로 임의 지정하는 것도 가능하다 (ppt 54p참조) 이름 바꾼다면 메서드 이름(아래 쓸때)에 주의
	
//	<bean id="boardService" class="kr.or.ddit.board.service.BoardService">
//	
//<!-- 		<property name="name" value="brown"/> -->
//	<property name="boardDao" ref="boardDao"/>
//</bean>
	
	@Bean
	public BoardService boardService() {
		BoardService boardService = new BoardService();
		boardService.setName("brown");
		boardService.setBoardDao(boardDao()); // spring에서 관리되는 boardDao bean을 주입하려면 이렇게
		//boardService.setBoardDao(new BoardDao()); // 새로 만들어서 주입하려면 이렇게
		return boardService;
	}
	
	
}
