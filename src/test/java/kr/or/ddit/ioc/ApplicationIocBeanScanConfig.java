package kr.or.ddit.ioc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// defaultFilter : @Controller, @Service, @Repository, @Component
// useDefaultFilters를 false로 선언하면 개발자가 원하는 annotation만 스캔 가능 (default는 true)
// ex : @Controller만 스캔
@Configuration
@ComponentScan(basePackages = {"kr.or.ddit"}, useDefaultFilters = true)
public class ApplicationIocBeanScanConfig {
	
}
