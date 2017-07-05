package com.huang.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Common {

	private static String[] fileSize = { "B", "KB", "M", "G", "T" };

	/**
	 * 用byte数组替换读取到的文件内容
	 * 
	 * @param file 读取的文件对象
	 * @return 返回文件内容转换成的byte数组
	 * @throws FileNotFoundException 如果文件不存在或者传入的是目录
	 * @throws NullPointerException  如果传入的是null
	 */
	public String file2buf(String fileInput) throws FileNotFoundException {
		File file = new File(fileInput);
		if (file.isDirectory()) {
			StringBuilder result = new StringBuilder();
			return result.append(file.list()).toString();
		}
		if (!file.exists())
			throw new FileNotFoundException();
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		int offset = 0;
		int numRead = 0;
		FileInputStream fi = null;
		byte[] buffer = null;
		try {
			fi = new FileInputStream(file);
			buffer = new byte[(int) fileSize];
			int length = buffer.length;
			while (offset < length
					&& (numRead = fi.read(buffer, offset, length - offset > 4096 ? 4096 : length - offset)) >= 0) {
				offset += numRead;
			}
			if (offset != length) {
				throw new IOException("Could not completely read file " + file.getName());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (fi != null) {
					fi.close();
				}
			}
			catch (IOException e) {
			}
		}
		return buffer.toString();
	}

	//获取文件大小
	public String getFileLength(File file) {
		long size = file.length();
		int index = 0;
		double length = size;
		while (length > 1024) {
			length /= 1024;
			index++;
		}
		String lenStr = Double.toString(length);
		int pointAddress = lenStr.indexOf(".");
		return lenStr.substring(0, pointAddress + 4) + fileSize[index];
	}

	public String getFileLastUpdateTime(File file) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(file.lastModified());
		String modify = sdf.format(cal.getTime());
		return modify;
	}
}
