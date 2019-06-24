package kr.or.ddit.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVo;


public class UserDaoTest extends LogicTestEnv{
	
	@Resource(name="userDao")
	private IuserDao userDao;
	
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
		List<UserVo> userList = userDao.userList();

		/***Then***/
		assertNotNull(userList);
		assertTrue(userList.size() >= 100);
		assertEquals(109, userList.size());
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
		
			userVo = new UserVo("userTest", "홍길동", "길동", "userTest1234", 
					"대전광역시 중구 대흥등", "영민빌딩", "34940", sdf.parse("2019-05-31"));
		
		/***When***/
		int insertCnt = userDao.insertUser(userVo);
		
		/***Then***/
		assertEquals(1, insertCnt);
		
		// data 삭제
		userDao.deleteUser(userVo.getUserId());
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
		String userId = "brown";

		/***When***/
		UserVo userVo = userDao.getUser(userId);

		/***Then***/
		assertEquals("브라운", userVo.getName());
		assertEquals("곰곰곰곰곰", userVo.getAlias());
	}

}
