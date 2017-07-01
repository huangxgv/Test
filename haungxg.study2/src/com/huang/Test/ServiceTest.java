package com.huang.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.huang.beans.DoInfoBean;
import com.huang.dao.DelFileDao;

public class ServiceTest {
	DoInfoBean doBean = new DoInfoBean();

	DelFileDao fileDao = new DelFileDao();

	private void init(String name, String path, boolean isFile, String type, String context) {
		doBean.setName(name);
		doBean.setPath(path);
		doBean.setIsFile(isFile);
		doBean.setType(type);
		doBean.setContext(context);
	}

	@Test
	public void delFileDaoTest() {
		init("test.txt", "D:/", true, "", "");
		assertEquals("success", fileDao.delFile(doBean));
		init("test.txt", "D:/", true, "", "");
		assertEquals("success", fileDao.addFile(doBean));
		init("test.txt", "D:/", true, "", "welcome");
		assertEquals("success", fileDao.updateFile(doBean));
		init("test.txt", "D:/", true, "", "");
		assertEquals("welcome", fileDao.getFileContext(doBean));
		init("", "D:/workSpace/", true, "", "");
		assertEquals(".metadata:.recommenders:front:service", fileDao.getFolderList(doBean));
	}

}
