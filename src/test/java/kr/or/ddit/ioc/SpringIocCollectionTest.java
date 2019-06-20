package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
		assertNotNull(collectionBean.getMap());
		assertEquals("brown", collectionBean.getList().get(0));
		assertEquals("2019-08-08", collectionBean.getMap().get("birth"));
		assertTrue(collectionBean.getSet().contains("james"));
		assertEquals(2, collectionBean.getProperties().size());
		
	}

}
