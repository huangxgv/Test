package com.huang.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import com.huang.beans.DoInfoBean;

/**
 * 文件操作工具包
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huangxg
 * @createdate 2017年6月30日
 */
public class DelFileDao {
	private static final String SUCCESS = "success";

	private static final String FAIL = "fail";

	DoInfoBean dInfoBean = new DoInfoBean();

	private static String[] fileSize = { "B", "KB", "M", "G", "T" };

	//获取文件大小
	private String getFileLength(File file) {
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

	private String getFileLastUpdateTime(File file) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(file.lastModified());
		String modify = sdf.format(cal.getTime());
		return modify;
	}

	/**
	 * 在指定的目录中添加文件
	 * <pre>
	 * 	addFile(null)=FAIL;
	 * </pre>
	 * @param info
	 * @return 如果添加失败，返回"FAIL",如果添加成功，返回"SUCCESS"。
	 */
	public String addFile(DoInfoBean info) {
		if (!checkInfo(info)) {
			return FAIL;
		}
		String filenameTemp = info.getPath() + info.getName();
		File file = new File(filenameTemp);
		if (file.exists()) {
			return FAIL;
		}
		boolean isfile = info.getIsFile();
		boolean isEndWithSptor = filenameTemp.endsWith(File.separator);
		if (isfile && !isEndWithSptor) {
			try {
				file.createNewFile();
			}
			catch (IOException e) {
				return FAIL;
			}
		}
		else if (!isEndWithSptor) {
			if (!file.mkdirs()) {
				return FAIL;
			}
		}
		else {
			return FAIL;
		}
		return SUCCESS;
	}

	/**
	 * 删除指定的文件
	 * <pre>
	 * 	delFile(null)="FAIL"
	 * </pre>
	 * @param info
	 * @return 如果删除失败，返回"FAIL",如果删除成功，返回"SUCCESS"。
	 */
	public String delFile(DoInfoBean info) {
		if (!checkInfo(info)) {
			return FAIL;
		}
		//要求传入的文件名带后缀
		String filenameTemp = info.getPath() + info.getName();
		File file = new File(filenameTemp);
		try {
			if (file.exists()) {
				file.delete();
			}
			else {
				return FAIL;
			}
		}
		catch (Exception e) {
		}
		return SUCCESS;
	}

	/**
	 * 查找指定的文件
	 * <pre>
	 * 	searchFile(null)=""
	 * </pre>
	 * @param info
	 * @return 如果查找失败，返回"FAIL",如果查找成功，返回当前所在目录所有文件名。
	 */
	public String searchFile(DoInfoBean info) {
		if (!checkInfo(info)) {
			return FAIL;
		}
		return SUCCESS;
	}

	/**
	 * 修改指定的文件
	 * <pre>
	 * 	searchFile(null)="FAIL"
	 * </pre>
	 * @param info
	 * @return 如果修改失败，返回"FAIL",如果修改成功，返回"SUCCESS"。
	 */
	public String updateFile(DoInfoBean info) {
		if (!checkInfo(info)) {
			return FAIL;
		}
		String filenameTemp = info.getPath() + info.getName();
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(filenameTemp));
			String context = info.getContext();
			int length = context.length();
			int start = 0;
			while (length > 0) {
				out.write(context, start, length > 4096 ? 4096 : length);
				start += 4096;
				length -= 4096;
			}
		}
		catch (Exception e) {
			return FAIL;
		}
		finally {
			try {
				out.close();
			}
			catch (IOException e) {
			}
		}
		return SUCCESS;
	}

	public String getFileContext(DoInfoBean info) {
		if (!checkInfo(info)) {
			return FAIL;
		}
		String filenameTemp = info.getPath() + info.getName();
		File file = new File(filenameTemp);
		String context = FAIL;
		try {
			context = new String(file2buf(file));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return FAIL;
		}
		catch (NullPointerException e) {
			e.printStackTrace();
			return FAIL;
		}
		return context;
	}

	/**
	 * 对DoInfoBean对象进行检测
	 * <pre>
	 *	 checkInfo(null)=false;
	 * </pre>
	 * @param info
	 * @return false :如果参数对象的path属性与name属性有一个为空。
	 */
	private boolean checkInfo(DoInfoBean info) {
		if (info == null) {
			return false;
		}
		else if (Objects.equals(info.getPath(), null)) {
			return false;
		}
		else if (Objects.equals(info.getName(), null)) {
			return false;
		}
		return true;
	}

	public void downFile(DoInfoBean info) {

	}

	/**
	 * 返回指定目录下所有的文件名和文件夹名
	 * <pre>
	 * 	getFolder(null)=null;
	 * </pre>
	 * @param info
	 * @return
	 */
	public String getFolderList(DoInfoBean info) {
		if (Objects.equals(info, null) || Objects.equals(info.getPath(), null)) {
			return FAIL;
		}
		String filenameTemp = info.getPath();
		File file = new File(filenameTemp);
		if (!file.exists()) {
			return FAIL;
		}
		File fa[] = file.listFiles();
		int length = fa.length;
		if (length == 0) {
			return FAIL;
		}
		StringBuilder folderNameArr = new StringBuilder();
		for (int i = 0; i < length; i++) {
			folderNameArr.append(fa[i].getName()).append('|');
			if (fa[i] != null && fa[i].isFile()) {
				folderNameArr.append(getFileLength(fa[i])).append('|');
				folderNameArr.append(getFileLastUpdateTime(fa[i])).append('|');
				folderNameArr.append("true").append('*');
			}
			else {
				folderNameArr.append('\0').append('|');
				folderNameArr.append(getFileLastUpdateTime(fa[i])).append('|');
				folderNameArr.append("false").append('*');
			}
		}
		int strLength = folderNameArr.length() - 1;
		return new String(folderNameArr.substring(0, strLength));
	}

	/**
	 * 用byte数组替换读取到的文件内容
	 * 
	 * @param file 读取的文件对象
	 * @return 返回文件内容转换成的byte数组
	 * @throws FileNotFoundException 如果文件不存在或者传入的是目录
	 * @throws NullPointerException  如果传入的是null
	 */
	private byte[] file2buf(File file) throws FileNotFoundException, NullPointerException {
		if (file == null)
			throw new NullPointerException();
		if (!file.exists() || file.isDirectory())
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
}
