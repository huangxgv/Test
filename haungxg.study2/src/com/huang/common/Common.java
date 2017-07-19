package com.huang.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

/**
 * 工具类
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huangxg
 * @createdate 2017年7月6日
 */
public class Common {

	/**
	 * 获取文件列表信息
	 * @param fileInput
	 * @return
	 */
	public static String getFolderList(String fileInput) {
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
	public static void fileRead(PrintWriter out, String fileInput) throws FileNotFoundException {
		File file = new File(fileInput);
		if (file.isDirectory()) {
			return;
		}
		if (!file.exists())
			throw new FileNotFoundException();
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return;
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
		out.print(new String(buffer));
	}

	/**
	 * 返回文件信息Json字符串
	 * <pre>
	 * 	{"callback":["name":"test.txt","size":"1024","date":"2017-07-06 20:04:50"]}
	 * </pre>
	 * @param Catalog 文件夹
	 * @return
	 */
	private static String getFilesJson(File Catalog) {
		if (Catalog.isFile()) {
			return null;
		}
		StringBuilder jsonString = new StringBuilder("{\"callback\":[");
		String[] listNameArr = Catalog.list();
		int length = listNameArr.length;
		if (length == 0) {
			return "{\"callback\":[]}";
		}
		String name;
		File[] files = Catalog.listFiles();
		File file;
		for (int i = 0; i < length - 1; i++) {
			name = listNameArr[i];
			file = files[i];
			jsonString.append("{\"name\":\"").append(name).append("\",");
			jsonString.append("\"size\":\"").append(file.isFile() ? file.length() : "").append("\",");
			jsonString.append("\"isFile\":\"").append(file.isFile()).append("\",");
			jsonString.append("\"date\":\"").append(file.lastModified()).append("\"},");
		}
		File fileLast = files[length - 1];
		jsonString.append("{\"name\":\"").append(listNameArr[length - 1]).append("\",");
		jsonString.append("\"size\":\"").append(fileLast.isFile() ? fileLast.length() : "").append("\",");
		jsonString.append("\"isFile\":\"").append(fileLast.isFile()).append("\",");
		jsonString.append("\"date\":\"").append(fileLast.lastModified()).append("\"}]}");
		return new String(jsonString);
	}

	/**
	 * 删除文件夹
	 * @param path
	 */
	public static void deleteAllFilesOfDir(File path) {
		if (!path.exists())
			return;
		if (path.isFile()) {
			path.delete();
			return;
		}
		File[] files = path.listFiles();
		for (int i = 0; i < files.length; i++) {
			deleteAllFilesOfDir(files[i]);
		}
		path.delete();
	}

	/**
	 * 更新文件名或文件夹名
	 * <pre>
	 * updateFileName("notExit","hello.txt")="update fail";
	 * updateFileName("hello.txt","")="update fail";
	 * 修改文件名
	 * updateFileName("test.txt","hello.txt")="update Success";
	 * 修改文件夹名
	 * updateFileName("hello","test")="update Success";
	 * </pre>
	 * @param path
	 * @param newName
	 * @return
	 */
	public String updateFileName(String path, String newName) {
		File file = new File(path);
		if (!file.exists()) {
			return "update fail";
		}
		if (newName == null || newName == "") {
			return "update fail";
		}
		else {
			file.renameTo(new File(newName));
			return "update Success";
		}
	}

	public static String updateFileContext(String path, String context) {
		File file = new File(path);
		FileOutputStream fos = null;
		int index = 0;
		int length = context.length();
		try {
			byte bytes[] = new byte[4096];
			while (index < length) {
				if (length < index + 4096) {
					bytes = (context.substring(index, length).getBytes("UTF-8"));
				}
				else {
					bytes = (context.substring(index, index + 4096)).getBytes("UTF-8");
				}
				int bytesLength = bytes.length;
				index += bytesLength;
				fos = new FileOutputStream(file);
				fos.write(bytes, 0, bytesLength);
				fos.flush();
			}
		}
		catch (Exception e) {
			return "update fail";
		}
		finally {
			if (fos != null) {
				try {
					fos.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "update success";
	}

	/**
	 * 创建单个文件
	 * @param filePath
	 * @return
	 */
	public static String createFile(File file) {
		try {
			if (file.createNewFile()) {
				return "create success";
			}
			else {
				return "create fail";
			}
		}
		catch (IOException e) {// 捕获异常
			e.printStackTrace();
			return "create fail";
		}
	}

	/**
	 * 创建目录
	 * @param destDirName
	 * @return
	 */
	public static String createDir(File dir) {
		if (dir.mkdirs()) {
			return "create success";
		}
		else {
			return "create fail";
		}
	}

	/**
	 * 关闭流
	 * @param input
	 * @param output
	 * @param socket
	 */
	public void closeSource(InputStream input, OutputStream output, Socket socket) {
		try {
			if (input != null) {
				input.close();
			}
		}
		catch (IOException e) {
		}
		try {
			if (output != null) {
				output.close();
			}
		}
		catch (IOException e) {
		}
		try {
			if (socket != null) {
				socket.close();
			}
		}
		catch (IOException e) {
		}
	}

	public static void getPath(int len, StringBuilder result, String root, String fileName) {
		File file = new File(root);
		File[] files = file.listFiles();
		if (Objects.equals(files, null)) {
			return;
		}
		String[] liStrings = file.list();
		for (int i = 0, length = liStrings.length; i < length; i++) {
			if (Objects.equals(liStrings[i], fileName)) {
				result.append(root.substring(len)).append(',');
			}
			else if (files[i].isDirectory()) {
				getPath(len, result, files[i].getAbsolutePath(), fileName);
			}
		}
	}
}
