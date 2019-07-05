package kr.or.ddit.testenv;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.config.spring.ApplicationContext;
import kr.or.ddit.config.spring.ApplicationDatasource_dev;
import kr.or.ddit.config.spring.ApplicationTransaction;
import kr.or.ddit.config.spring.RootContext;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:kr/or/ddit/config/spring/application-context.xml",
//					   "classpath:kr/or/ddit/config/spring/root-context.xml",
//					   "classpath:kr/or/ddit/config/spring/application-datasource-dev.xml",
//					   "classpath:kr/or/ddit/config/spring/application-transaction.xml"})
@ContextConfiguration(classes = {ApplicationContext.class,
								 RootContext.class, 
								 ApplicationDatasource_dev.class, 
								 ApplicationTransaction.class})
//일반 자바 환경 => 웹 환경
//applicationContext => 웹 환경의 applicationContext 생성			logic test때보다 annotation이 하나 더 들어간다
@WebAppConfiguration
public class ControllerTestEnv {
	// 필요한 것
	@Autowired
	protected WebApplicationContext ctx; // spring container 역할
	protected MockMvc mockMvc; // dispatcher servlet 역할
	
	@Resource(name="datasource") // 아래 두번째 인자값에 넣기 위해 주입받음
	private DataSource datasource;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		
		ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
		rdp.setContinueOnError(false); // 에러 발생시 계속 진행할지 여부
		rdp.addScript(new ClassPathResource("kr/or/ddit/testenv/dbInit.sql"));
		
		DatabasePopulatorUtils.execute(rdp, datasource);
	}
	
	@Ignore
	@Test
	public void dummy() {
		
	}
}
