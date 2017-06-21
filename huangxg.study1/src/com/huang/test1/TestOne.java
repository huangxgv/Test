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
	 * @param file
	 * @return
	 * @throws FileNotFoundException 
	 */
	public byte[] file2buf(File file) throws FileNotFoundException {
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
			while (offset < buffer.length && (numRead = fi.read(buffer, offset, length - offset)) >= 0) {
				offset += numRead;
				// 确保所有数据均被读取  
				if (offset != buffer.length) {
					throw new IOException("Could not completely read file " + file.getName());
				}
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
}
