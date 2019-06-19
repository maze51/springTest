package kr.or.ddit.board.service;

import kr.or.ddit.board.dao.IboardDao;

public class BoardService implements IboardService {
	private IboardDao boardDao;
	
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
