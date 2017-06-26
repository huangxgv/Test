package com.huaang.Test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.huang.test1.TestOne;

public class OneTest {
	TestOne tOne = new TestOne();

	String[] ENCODING = { "UTF-8", "GBK", "UNICODE" };
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private void write(String path, String content, String encoding) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
		BufferedWriter writer = null;
		try {
			file.createNewFile();
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));
			writer.write(content);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				writer.close();
			}
			catch (IOException e) {
			}
		}
	}

	private String read(String path, String encoding) {
		String content = "";
		File file = new File(path);
		BufferedReader reader = null;
		try {
			String line = null;
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
			while ((line = reader.readLine()) != null) {
				content += line;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			}
			catch (IOException e) {
			}
		}
		return content;
	}

	@Test
	public void txtTest() {

		String testString = "测试写入的中文字符串";
		String path = "D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1/chinese.txt";
		for (int i = 0; i < ENCODING.length; i++) {
			write(path, testString, ENCODING[i]);
		File testFile1 = new File("D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1", "chinese.txt");
		try {
				assertEquals(Arrays.toString(testString.getBytes(ENCODING[i])),
						Arrays.toString(tOne.file2buf(testFile1)));
				assertEquals(Arrays.toString(read(path, ENCODING[i]).getBytes(ENCODING[i])),
					Arrays.toString(tOne.file2buf(testFile1)));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		}
	}

	@Test
	public void mediaTest() {
		File testFile1 = new File("D:/succezIDE/workspace/huangxg.devstudy/huangxg.study1", "music.mp3");
		try {
			assertEquals(testFile1.length(), tOne.file2buf(testFile1).length);
		}
		catch (FileNotFoundException | NullPointerException e) {
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
