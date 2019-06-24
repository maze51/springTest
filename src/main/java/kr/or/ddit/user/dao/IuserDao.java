package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.user.model.UserVo;

public interface IuserDao {
	
	/**
	 * 
	* Method : userList
	* 작성자 : PC10
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 조회
	 */
	List<UserVo> userList();
	
	/**
	 * 
	* Method : insertUser
	* 작성자 : PC10
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 추가
	 */
	int insertUser(UserVo userVo);
	
	/**
	 * 
	* Method : deleteUser
	* 작성자 : PC10
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	 */
	int deleteUser(String userId);
	
	/**
	 * 
	* Method : getUser
	* 작성자 : PC10
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 정보 조회
	 */
	UserVo getUser(String userId);
}
