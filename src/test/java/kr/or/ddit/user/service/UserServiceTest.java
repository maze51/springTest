package kr.or.ddit.user.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVo;


public class UserServiceTest extends LogicTestEnv{
	
	@Resource(name="userService")
	private IuserService userService;
	
	/**
	 * 
	* Method : userListTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 사용자 전체 리스트 조회 테스트
	 */
	@Test
	public void userListTest() {
		/***Given***/

		/***When***/
		List<UserVo> userList = userService.userList();

		/***Then***/
		assertNotNull(userList);
		assertTrue(userList.size() >= 100);
	}
	
	/**
	 * 
	* Method : insertUserTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 사용자 등록 테스트
	 */
	@Test
	public void insertUserTest() throws ParseException{
		/***Given***/
		// 사용자 정보를 담고 있는 vo객체 준비
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		UserVo userVo = null;
		
			userVo = new UserVo("홍길동", "userTest", "길동", "userTest1234", 
					"대전광역시 중구 대흥등", "영민빌딩", "34940", sdf.parse("2019-05-31"));
		
		/***When***/
		int insertCnt = userService.insertUser(userVo);
		
		/***Then***/
		assertEquals(1, insertCnt);
		
		// data 삭제
		userService.deleteUser(userVo.getUserId());
	}
	
	/**
	 * 
	* Method : getUserTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 사용자 정보 조회 테스트
	 */
	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "user1";

		/***When***/
		UserVo userVo = userService.getUser(userId);

		/***Then***/
		assertEquals("가나다라", userVo.getName());
		
	}

}
