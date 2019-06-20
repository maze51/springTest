package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.ioc.collection.IocCollection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-collection.xml")
public class SpringIocCollectionTest {
	
	@Resource(name="collectionBean")
	private IocCollection collectionBean;
	
	@Test
	public void springCollectionTest() {
		/***Given***/

		/***When***/

		/***Then***/
		assertNotNull(collectionBean.getList());
		//assertNotNull(collectionBean.getMap());
		
	}

}
