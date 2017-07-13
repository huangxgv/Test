package com.huang.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Objects;

import com.huang.beans.FileBean;

import net.sf.json.JSONObject;

public class Servlet {
	private FileBean bean;

	public Servlet(FileBean bean) {
		this.bean = bean;
	}

	public FileBean getBean() {
		return bean;
	}

	public void setBean(FileBean bean) {
		this.bean = bean;
	}

	public void doGet(String path, BufferedReader buf) throws IOException {
		String uri = "";
		if (!Objects.equals(path, "null")) {
			try {
				uri = URLDecoder.decode(path.split(" ")[1].substring(1), "utf-8");
				String[] pathArr = uri.split("[?]");
				if (pathArr.length > 1) {
					bean.setType(pathArr[0]);
					if (pathArr[1].startsWith("{") && pathArr[1].endsWith("}")) {
						bean.sets(JSONObject.fromObject(pathArr[1]));
					}
					else {
						bean.setPath(pathArr[1]);
					}
				}
				else {
					bean.setType(uri);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void doPost(String info, BufferedReader buf) throws IOException {
		StringBuilder sBuilder = new StringBuilder();
		String lines = null;
		while (true) {
			lines = buf.readLine();
			sBuilder.append(lines);
			if ("".equals(lines)) {
				break;
			}
		}
		lines = buf.readLine();
		JSONObject json = JSONObject.fromObject(lines);
		bean.sets(json);
	}
}
