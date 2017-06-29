package com.huang.dao;

import java.util.Objects;

import com.huang.beans.DoInfoBean;

import net.sf.json.JSONObject;

public class DelFileDao {
	private static final String SUCCESS = "success";

	DoInfoBean dInfoBean = new DoInfoBean();
	public void setDoInfo(String Info) {
		JSONObject json = JSONObject.fromObject(Info);
		String type;
		String path;
		String name;
		boolean isFile;
		String context;
		try {
			type = json.getString("type");
		}
		catch (Exception e) {
			type = null;
		}
		try {
			path = json.getString("path");
		}
		catch (Exception e) {
			path = null;
		}
		try {
			name = json.getString("name");
		}
		catch (Exception e) {
			name = null;
		}
		try {
			isFile = Objects.equals("true", json.getString("isFile"));
		}
		catch (Exception e) {
			isFile = false;
		}
		try {
			context = json.getString("type");
		}
		catch (Exception e) {
			context = null;
		}
		dInfoBean.setType(type);
		dInfoBean.setContext(context);
		dInfoBean.setIsFile(isFile);
		dInfoBean.setName(name);
		dInfoBean.setPath(path);
	}

	//增
	public String addFile(DoInfoBean info) {
		return SUCCESS;
	}

	//删
	public String delFile(DoInfoBean info) {
		return SUCCESS;
	}

	//查
	public String searchFile(DoInfoBean info) {
		return SUCCESS;
	}

	//改
	public String updateFile(DoInfoBean info) {
		return SUCCESS;
	}
}
