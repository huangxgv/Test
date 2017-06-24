package com.huang.test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 读取文件内容并以byte数组返回，若文件为空或者读取文件错误则返回null
 * 
 * @author huangxg@succez.com
 * @createdate 2017/6/14
 */
public class TestOne {

	/**
	 * 用byte数组替换读取到的文件内容
	 * 
	 * @param file 读取的文件对象
	 * @return 返回文件内容转换成的byte数组
	 * @throws FileNotFoundException 如果文件不存在或者传入的是目录
	 * @throws NullPointerException  如果传入的是null
	 */

	public byte[] file2buf(File file) throws FileNotFoundException, NullPointerException {
		if (file == null)
			throw new NullPointerException();
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		try {
			int length = buffer.length;
			while (offset < length
					&& (numRead = fi.read(buffer, offset, length - offset > 4096 ? 4096 : length - offset)) >= 0) {
				offset += numRead;
			}
			if (offset != length) {
				throw new IOException("Could not completely read file " + file.getName());
			}
		}
		catch (IOException e) {
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
}
