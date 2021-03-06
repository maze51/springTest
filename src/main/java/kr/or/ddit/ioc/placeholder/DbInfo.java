package kr.or.ddit.ioc.placeholder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IboardDao;

public class DbInfo implements ApplicationContextAware{
	
	private String driver;
	private String url;
	private String username;
	private String password;
	
	private static final Logger logger = LoggerFactory.getLogger(DbInfo.class);
	
	public DbInfo() {
	}
	
	public DbInfo(String driver, String url, String username, String password) {
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	// 굳이 이렇게 설정하지 않아도 @Resources annotation을 쓰면 된다. applicationContext객체가 필요하다면 쓸 수도 있다
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		IboardDao boardDao = applicationContext.getBean("boardDao", BoardDao.class); // 형변환 없이 받을 수 있다
		String msg = boardDao.sayHello();
		logger.debug("msg : {}", msg);
	}
	
	public void init() {
		logger.debug("init-method");
	}
	
	
}
