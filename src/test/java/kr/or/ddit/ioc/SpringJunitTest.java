package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.board.service.IboardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-test.xml")
public class SpringJunitTest {
	
	// boardDao : spring boardDao(scope=singleton)
	// boardDaoPrototype : spring boardDaoPrototype(scope=prototype)
	// boardDaoPrototype2 : spring boardDaoPrototype(scope=prototype)
	// 1. boardDao, boardDaoPrototype : spring bean id가 다르므로 다른 객체
	// 2. boardDaoPrototype, boardDaoPrototype2 : 두 객체는 같은 spring bean이지만 scope가 prototype이므로 다른 객체
	@Resource(name="boardDao")
	private IboardDao boardDao;
	
	@Resource(name="boardDaoPrototype")
	private IboardDao boardDaoPrototype;
	
	@Resource(name="boardDaoPrototype")
	private IboardDao boardDaoPrototype2;
	
	// field 기준으로 boardService, boardService는 spring boardService (scope=singleton)
	//			   boardServiceConstructor는 spring boardServiceConstructor bean (scope=singleton)
	// 1. boardService, boardService2 : 같아야 한다
	// 2. boardService, boardServiceConstructor : 같은 클래스에서 만들어진 객체이지만 spring singleton개념에 따라 다른 객체
	// 3. boardService2, boardServiceConstructor : 같은 클래스에서 만들어진 객체이지만 spring singleton개념에 따라 다른 객체
	@Resource(name="boardService")
	private IboardService boardService;
	
	@Resource(name="boardService")
	private IboardService boardService2;
	
	@Resource(name="boardServiceConstructor")
	private IboardService boardServiceConstructor;
	
	/**
	 * 
	* Method : springIocTest
	* 작성자 : PC08
	* 변경이력 :
	* Method 설명 : 스프링 컨테이너 생성 테스트
	 */
	@Test
	public void springIocTest() {
		/***Given***/

		/***When***/
		String msg = boardService.sayHello();

		/***Then***/
		assertNotNull(boardService);
		assertEquals("boardDao sayHello", msg);
	}
	
	@Test
	public void springSingletonScopeTest() {
		/***Given***/
		// 1. boardService, boardService2 : 같아야 한다
		// 2. boardService, boardServiceConstructor : 같은 클래스에서 만들어진 객체이지만 spring singleton개념에 따라 다른 객체
		// 3. boardService2, boardServiceConstructor : 같은 클래스에서 만들어진 객체이지만 spring singleton개념에 따라 다른 객체
		
		/***When***/

		/***Then***/
		assertNotNull(boardService);
		assertNotNull(boardService2);
		assertNotNull(boardServiceConstructor);
		assertEquals(boardService, boardService2);
		assertNotEquals(boardService, boardServiceConstructor);
		assertNotEquals(boardService2, boardServiceConstructor);
	}
	
	@Test
	public void springPrototypeScopeTest() {
		/***Given***/
		// 1. boardDao, boardDaoPrototype : spring bean id가 다르므로 다른 객체
		// 2. boardDaoPrototype, boardDaoPrototype2 : 두 객체는 같은 spring bean이지만 scope가 prototype이므로 다른 객체

		/***When***/

		/***Then***/
		assertNotNull(boardDao);
		assertNotNull(boardDaoPrototype);
		assertNotNull(boardDaoPrototype2);
		assertNotEquals(boardDao, boardDaoPrototype);
		assertNotEquals(boardDaoPrototype, boardDaoPrototype2);
		
	}

}
