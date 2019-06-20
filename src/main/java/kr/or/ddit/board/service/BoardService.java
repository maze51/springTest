package kr.or.ddit.board.service;

import kr.or.ddit.board.dao.IboardDao;

public class BoardService implements IboardService {
	
	//property or field
	private IboardDao boardDao;
	
	
	//인자를 받는 생성자를 만들었다면 기본 생성자도 만들어야 오류 X
	public BoardService() {
	}
	
	public BoardService(IboardDao boardDao) {
		this.boardDao = boardDao;
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
