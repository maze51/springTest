package kr.or.ddit.board.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.board.dao.IboardDao;

@Transactional
@Service
public class BoardService implements IboardService {
	
	//property or field
	@Resource(name="boardDao")
	private IboardDao boardDao;
	
	private String name;
	
	//인자를 받는 생성자를 만들었다면 기본 생성자도 만들어야 오류 X
	public BoardService() {
	}
	
	public BoardService(IboardDao boardDao) {
		this.boardDao = boardDao;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String sayHello() {
		return boardDao.sayHello();
	}
	public IboardDao getBoardDao() {
		return boardDao;
	}
	public void setBoardDao(IboardDao boardDao) {
		this.boardDao = boardDao;
	}

	
	
}
