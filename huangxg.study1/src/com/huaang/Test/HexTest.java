package com.huaang.Test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.huang.test2.TestTwo;

public class HexTest {
	TestTwo testTwo = new TestTwo();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertEquals("-F", testTwo.intToHex(-15));
		assertEquals("-9", testTwo.intToHex(-9));
		assertEquals("-10", testTwo.intToHex(-16));
		assertEquals("-80000000", testTwo.intToHex(Integer.MIN_VALUE));
		assertEquals("0", testTwo.intToHex(0));
		assertEquals("F", testTwo.intToHex(15));
		assertEquals("9", testTwo.intToHex(9));
		assertEquals("10", testTwo.intToHex(16));
		assertEquals("7FFFFFFF", testTwo.intToHex(Integer.MAX_VALUE));
	}

}
