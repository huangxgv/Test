package com.huang.beans;

import java.util.Objects;

import net.sf.json.JSONObject;

public class FileBean {
	private String type;

	private String isFile;

	private String name;

	private String path;

	private String context;

	public void sets(JSONObject json) {
		this.type = json.getString("type");
		this.isFile = json.getString("isFile");
		this.name = json.getString("name");
		this.path = json.getString("path");
		this.context = json.getString("context");
	}

	public String getType() {
		if (Objects.equals(type, null)) {
			return "";
		}
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsFile() {
		if (Objects.equals(isFile, null)) {
			return "";
		}
		return isFile;
	}

	public void setIsFile(String isFile) {
		this.isFile = isFile;
	}

	public String getName() {
		if (Objects.equals(name, null)) {
			return "";
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		if (Objects.equals(path, null)) {
			return "";
		}
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContext() {
		if (Objects.equals(context, null)) {
			return "";
		}
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
}
