package huangxg.study2;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestJutil {

	Book book = null;

	@Before
	public void setUp() throws Exception {
		System.out.println("测试开始！");
		book = new Book();
		System.out.println("book对象被初始化！");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("book对象将被清理！");
		book = null;
		System.out.println("测试结束！");
	}

	@Test
	public void caseId() {
		book.setId("001"); //设置id属性的值为001
		//使用Assert查看id属性的值是否为001
		assertEquals("001", book.getId());
		System.out.println("id属性被测试！");
	}

	@Test
	public void caseName() {
		book.setName("ASP"); //设置name属性的值为ASP
		//使用Assert查看name属性的值是否为JSP，这是个必然出现错误的测试
		assertEquals("JSP", book.getName());
		System.out.println("name属性被测试！");
	}

}
