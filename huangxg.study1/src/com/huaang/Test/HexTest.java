package com.huaang.Test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.huang.test2.TestTwo;

public class HexTest {
	TestTwo testtwo = new TestTwo();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertEquals("-15测试", "-F", testtwo.intToHex(-15));
		assertEquals("-16测试", "-10", testtwo.intToHex(-16));
		assertEquals("Integer.MIN_VALUE测试", "-80000000", testtwo.intToHex(Integer.MIN_VALUE));
		assertEquals("0测试", "0", testtwo.intToHex(0));
		assertEquals("15测试", "F", testtwo.intToHex(15));
		assertEquals("16测试", "10", testtwo.intToHex(16));
		assertEquals("Integer.MAX_VALUE测试", "7FFFFFFF", testtwo.intToHex(Integer.MAX_VALUE));
	}

}
