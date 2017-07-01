package com.huang.beans;

import java.util.Objects;

import net.sf.json.JSONObject;

public class DoInfoBean {
	private String type;

	private Boolean isFile;

	private String name;

	private String path;

	private String context;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getIsFile() {
		return isFile;
	}

	public void setIsFile(Boolean isFile) {
		this.isFile = isFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public boolean setDoInfo(String Info) {
		if (Objects.equals(Info, "")) {
			return false;
		}
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
		this.isFile = isFile;
		this.name = name;
		this.path = path;
		this.context = context;
		this.type = type;
		return true;
	}
}
