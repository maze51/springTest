package kr.or.ddit.config.spring;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class ApplicationTransaction {
	
	@Resource(name="datasource") // 다른 설정파일에서 주입받음
	private DataSource datasource;
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager dstm = new DataSourceTransactionManager();
		dstm.setDataSource(datasource);
		return dstm;
	}
}
