package com.huaang.Test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.huang.test1.TestOne;

public class OneTest {
	TestOne tOne = new TestOne();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private byte[] mediaFileRead(File file) throws FileNotFoundException {
		if (!file.exists() || file.isDirectory() || file == null)
			throw new FileNotFoundException();
		long fileSize = file.length();
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		try {
			int length = buffer.length;
			while (offset < length
					&& (numRead = fi.read(buffer, offset, length - offset > 8 ? 8 : length - offset)) >= 0) {
				offset += numRead;
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				fi.close();
			}
			catch (IOException e) {
			}
		}
		return buffer;
	}

	private String txtRead(File file) {
		return null;
	}

	@Test
	public void txtTest() {
		try {
			File testFile1 = new File("D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1", "chinese.txt");

		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void mediaTest() {
		try {
			File testFile1 = new File("D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1", "music.mp3");
			long length1 = testFile1.length();
			assertEquals(length1, tOne.file2buf(testFile1).length);
			byte[] bs1 = new byte[(int) length1];
			bs1 = mediaFileRead(testFile1);
			bs1.equals(tOne.file2buf(testFile1));

			File testFile2 = new File("D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1", "pic.jpg");
			long length2 = testFile2.length();
			assertEquals(testFile2.length(), tOne.file2buf(testFile2).length);
			byte[] bs2 = new byte[(int) length2];
			bs2 = mediaFileRead(testFile2);
			bs2.equals(tOne.file2buf(testFile2));
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = NullPointerException.class)
	public void expectNullPointerTest() throws NullPointerException, FileNotFoundException {
		tOne.file2buf(null);
	}

	@Test(expected = FileNotFoundException.class)
	public void expectNotFoundTest() throws NullPointerException, FileNotFoundException {
		File testFile1 = new File("D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1", "notExit.txt");
		tOne.file2buf(testFile1);
	}
}
