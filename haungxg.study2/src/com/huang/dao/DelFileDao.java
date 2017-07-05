package com.huang.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
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

	/**
	 * 返回指定目录下所有的文件名和文件夹名
	 * <pre>
	 * 	getFolder(null)=null;
	 * </pre>
	 * @param info
	 * @return
	 */
	public String getFolderList(DoInfoBean info) {
		if (Objects.equals(info, null)) {
			return FAIL;
		}
		String filenameTemp = info.getPath();
		System.out.println(filenameTemp);
		File file = new File("D:/TestTwo/" + filenameTemp);
		if (!file.exists()) {
			return FAIL;
		}
		String[] fa = file.list();
		StringBuilder folderNameArr = new StringBuilder();
		folderNameArr.append(Arrays.toString(fa));
		return new String(folderNameArr);
	}
}