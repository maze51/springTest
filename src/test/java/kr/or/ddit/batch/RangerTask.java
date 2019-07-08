package kr.or.ddit.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RangerTask {
	private static final Logger logger = LoggerFactory.getLogger(RangerTask.class);
	
	// 주기별로 스케줄러에서 실행될 메서드 만들기
	public void rangerTask() {
		logger.debug("=====================rangerTask=====================");
	}
}
