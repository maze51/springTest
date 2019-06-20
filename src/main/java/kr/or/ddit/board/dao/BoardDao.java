package kr.or.ddit.board.dao;

import org.springframework.stereotype.Repository;

// spring bean 이름 : 인스턴스 명명 규칙 ==> 클래스명에서 첫글자를 소문자로 : 여기서는 boardDao
// spring bean 이름을 임의로 주고 싶은 경우에는
// @Repository("bDao")처럼 주면 된다
// 그리고 그 이름을 @Resource name에 넣어준다

@Repository // 데이터 관련 작업
public class BoardDao implements IboardDao {

	@Override
	public String sayHello() {
		return "boardDao sayHello";
	}
	
}
