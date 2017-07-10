package com.huang.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Objects;

import com.huang.beans.DoInfoBean;
import com.huang.common.Common;

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
	 * 在指定的目录中添加文件(夹)
	 * <pre>
	 * 	addFile(null)=FAIL;
	 * </pre>
	 * @param info
	 * @return 如果添加失败，返回"FAIL",如果添加成功，返回"SUCCESS"。
	 */
	public String createFile(String path, String isFile) {
		File file = new File(path);
		if (file.exists() || Objects.equals(file, null)) {
			return "create fail";
		}
		if (Objects.equals(isFile, "true")) {
			return new Common().createFile(file);
		}
		else {
			return new Common().createDir(file);
		}
	}

	/**
	 * 删除指定的文件
	 * <pre>
	 * 	delFile(null)="FAIL"
	 * </pre>
	 * @param info
	 * @return 如果删除失败，返回"FAIL",如果删除成功，返回"SUCCESS"。
	 */
	public void delFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (file.isDirectory()) {
			new Common().deleteAllFilesOfDir(file);
		}
		else {
			file.delete();
		}
	}

	/**
	 * 查找指定的文件
	 * <pre>
	 * 	searchFile(null)=""
	 * </pre>
	 * @param info
	 * @return 如果查找失败，返回"FAIL",如果查找成功，返回当前所在目录所有文件名。
	 */
	//	public String searchFile(DoInfoBean info) {
	//		if (!checkInfo(info)) {
	//			return FAIL;
	//		}
	//		return SUCCESS;
	//	}
	//
	//	/**
	//	 * 修改指定的文件
	//	 * <pre>
	//	 * 	searchFile(null)="FAIL"
	//	 * </pre>
	//	 * @param info
	//	 * @return 如果修改失败，返回"FAIL",如果修改成功，返回"SUCCESS"。
	//	 */
	public String updateFile(String path, String newName, String newContext) {
		Common common = new Common();
		File file = new File(path);
		if (Objects.equals(file, null)) {
			return "update fail";
		}
		if (!file.exists()) {
			return "update fail";
		}
		if (file.isDirectory()) {
			return common.updateFileName(path, newName);
		}
		else {
			if (Objects.equals(common.updateFileName(path, newName), "update Success")) {
				return common.updateFileContext(path, newContext);
			}
			else {
				return "update fail";
			}
		}
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

	/**
	 * 获取文件内容
	 * @param path
	 * @return
	 */
	public String getFile(String path) {
		try {
			return new String(new Common().file2buf(path));
		}
		catch (FileNotFoundException e) {
			return "";
		}
	}
}