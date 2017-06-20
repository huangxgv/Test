package com.huaang.Test;

import static org.junit.Assert.assertArrayEquals;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.huang.test1.TestOne;

public class TestOneTest {
	TestOne tOne = null;

	@Before
	public void setUp() throws Exception {
		System.out.println("测试开始！");
		tOne = new TestOne();
		System.out.println("testone的对象已被实例化！");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("测试结束！");
		tOne = null;
		System.out.println("testone对象已被销毁！");
	}

	@Test
	public void test() {

		File testFile1 = new File("D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1", "chinese.txt");
		assertArrayEquals("我是一个用于进行读练习的写文件".getBytes(), tOne.file2buf(testFile1));
		System.out.println("读取中文转byte数组测试完毕");

		File testFile2 = new File("D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1", "english.txt");
		assertArrayEquals("abcdefghijklmnopqrstuvwxyz".getBytes(), tOne.file2buf(testFile2));
		System.out.println("读取英文转byte数组测试完毕");

		File testFile3 = new File("D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1", "null.txt");
		assertArrayEquals("".getBytes(), tOne.file2buf(testFile3));
		System.out.println("读取空文件测试完毕");

		File testFile4 = new File("D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1", "number.txt");
		assertArrayEquals("1234567890".getBytes(), tOne.file2buf(testFile4));
		System.out.println("读取数字转byte数组测试完毕");

		File testFile5 = new File("D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1", "sign.txt");
		assertArrayEquals("!@#$%^&*()-_=+\":'?/\\,.><".getBytes(), tOne.file2buf(testFile5));
		System.out.println("读取标点符号转byte数组测试完毕");

		File testFile6 = new File("D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1", "notExit.txt");
		assertArrayEquals(null, tOne.file2buf(testFile6));
		System.out.println("读取文件失败测试完毕");
	}

}
