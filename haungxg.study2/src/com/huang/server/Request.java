package com.huang.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Objects;

import net.sf.json.JSONObject;

public class Request {
	private BufferedReader input;

	private String uri;

	private String path;

	private String name;

	private String context;

	private String isFile;

	public Request(BufferedReader input) {
		this.input = input;
		uri = "";
		path = "";
	}

	/**
	 * 读取path
	 * 如地址为localhost：8080/hello,获取hello
	 */
	public void parse() {
		StringBuilder request = new StringBuilder();
		try {
			request.append(input.readLine());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		uri = parseUri(request.toString());
	}

	/**
	 * 处理读取到的URI字符串
	 * @param requestString
	 * @return
	 */
	private String parseUri(String requestString) {
		String uri = null;
		if (!Objects.equals(requestString, null)) {
			try {
				uri = URLDecoder.decode(requestString.split(" ")[1].substring(1), "utf-8");
				if (uri.startsWith("{")) {
					JSONObject json = JSONObject.fromObject(uri);
					uri = json.getString("type");
					path = json.getString("path");
					name = json.getString("name");
					context = json.getString("context");
					isFile = json.getString("isFile");
				}
				return uri;
			}
			catch (UnsupportedEncodingException e) {
				return uri;
			}
		}
		else {
			return uri;
		}
	}

	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

	public String getContext() {
		return context;
	}

	public String getIsFile() {
		return isFile;
	}

	public String getUri() {
		return uri;
	}

}
