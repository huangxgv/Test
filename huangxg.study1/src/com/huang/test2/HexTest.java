package com.huang.test2;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HexTest {
	Testtwo testtwo = null;

	@Before
	public void setUp() throws Exception {
		System.out.println("测试开始！");
		testtwo = new Testtwo();
		System.out.println("Testtwo的对象已被实例化！");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("测试结束！");
		testtwo = null;
		System.out.println("Testtwo对象已被销毁！");
	}

	@Test
	public void test() {
		assertEquals(Integer.toHexString(-15), testtwo.intToHex(-15));
		System.out.println("-15测试完毕");
		assertEquals(Integer.toHexString(-10), testtwo.intToHex(-16));
		System.out.println("-16测试完毕");
		assertEquals(Integer.toHexString(Integer.MIN_VALUE), testtwo.intToHex(Integer.MIN_VALUE));
		System.out.println("Integer.MIN_VALUE测试完毕");
		assertEquals(Integer.toHexString(0), testtwo.intToHex(0));
		System.out.println("0测试完毕");
		assertEquals(Integer.toHexString(15), testtwo.intToHex(15));
		System.out.println("15测试完毕");
		assertEquals(Integer.toHexString(16), testtwo.intToHex(16));
		System.out.println("16测试完毕");
		assertEquals(Integer.toHexString(Integer.MAX_VALUE), testtwo.intToHex(Integer.MAX_VALUE));
		System.out.println("Integer.MAX_VALUE测试完毕");
	}

}
