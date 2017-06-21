package com.huaang.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.huang.test1.TestOne;

public class TestOneTest {
	TestOne tOne = new TestOne();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void txtTest() {
		try {
			File testFile1 = new File("D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1", "chinese.txt");
			assertEquals(testFile1.length(), tOne.file2buf(testFile1).length);
			File testFile2 = new File("D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1", "english.txt");
			assertArrayEquals("abcdefghijklmnopqrstuvwxyz".getBytes(), tOne.file2buf(testFile2));
			File testFile3 = new File("D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1", "null.txt");
			assertArrayEquals("".getBytes(), tOne.file2buf(testFile3));
			File testFile4 = new File("D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1", "number.txt");
			assertArrayEquals("1234567890".getBytes(), tOne.file2buf(testFile4));
			File testFile5 = new File("D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1", "sign.txt");
			assertArrayEquals("!@#$%^&*()-_=+\":'?/\\,.><".getBytes(), tOne.file2buf(testFile5));
			File testFile6 = new File("D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1", "notExit.txt");
			assertArrayEquals(null, tOne.file2buf(testFile6));
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void binaryTest() {

	}

	@Test
	public void mediaTest() {
		try {
			File testFile1 = new File("D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1", "music.mp3");
			assertEquals("测试音乐文件", testFile1.length(), tOne.file2buf(testFile1).length);
			File testFile2 = new File("D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1", "pic.jpg");
			assertEquals("测试图片文件", testFile1.length(), tOne.file2buf(testFile1).length);

		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
