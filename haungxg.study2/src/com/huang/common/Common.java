package com.huang.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 工具类
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huangxg
 * @createdate 2017年7月6日
 */
public class Common {

	public String getFolderList(String fileInput) {
		File file = new File(fileInput);
		if (file.isDirectory()) {
			return getFilesJson(file);
		}
		return null;
	}
	/**
	 * 用byte数组替换读取到的文件内容
	 * 
	 * @param file 读取的文件对象
	 * @return 返回文件内容转换成的byte数组
	 * @throws FileNotFoundException 如果文件不存在或者传入的是目录
	 * @throws NullPointerException  如果传入的是null
	 */
	public byte[] file2buf(String fileInput) throws FileNotFoundException {
		File file = new File(fileInput);
		if (file.isDirectory()) {
			return null;
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
		return buffer;
	}

	private String getFilesJson(File Catalog) {
		if (Catalog.isFile()) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		StringBuilder jsonString = new StringBuilder("{\"callback\":[");
		String[] listNameArr = Catalog.list();
		int length = listNameArr.length;
		String name;
		File[] files = Catalog.listFiles();
		File file;
		for (int i = 0; i < length - 1; i++) {
			name = listNameArr[i];
			file = files[i];
			jsonString.append("{\"name\":\"").append(name).append("\",");
			jsonString.append("\"size\":\"").append(file.isFile() ? file.length() : "").append("\",");
			jsonString.append("\"isFile\":\"").append(file.isFile()).append("\",");
			cal.setTimeInMillis(file.lastModified());
			jsonString.append("\"date\":\"").append(sdf.format(cal.getTime())).append("\"},");
		}
		File fileLast = files[length - 1];
		jsonString.append("{\"name\":\"").append(listNameArr[length - 1]).append("\",");
		jsonString.append("\"size\":\"").append(fileLast.isFile() ? fileLast.length() : "").append("\",");
		jsonString.append("\"isFile\":\"").append(fileLast.isFile()).append("\",");
		cal.setTimeInMillis(fileLast.lastModified());
		jsonString.append("\"date\":\"").append(sdf.format(cal.getTime())).append("\"}]}");
		return new String(jsonString);
	}
}
