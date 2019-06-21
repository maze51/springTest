package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.typeConvert.model.FormattingVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-type-formatting.xml")
public class ApplicationIocTypeFormattingTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationIocTypeFormattingTest.class);
	
	@Resource(name="formattingVo")
	private FormattingVo formattingVo; 
	
	@Test
	public void TypeFormattingTest() {
		/***Given***/
		
		/***When***/
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		String modDt = sdf.format(formattingVo.getMod_dt());
		String regDt = sdf.format(formattingVo.getReg_dt());
		logger.debug("Mod_dt : {}", formattingVo.getMod_dt());
		logger.debug("Reg_dt : {}", formattingVo.getReg_dt());
		
		/***Then***/
		assertNotNull(formattingVo);
		assertEquals("06-21-2019", modDt);
		assertEquals("06-21-2019", regDt);
		assertEquals(6000, formattingVo.getNumber()); // "6,000"을 숫자 6000으로 바꿔줬음
	}

}
