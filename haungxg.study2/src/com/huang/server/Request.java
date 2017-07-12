package com.huang.server;

import java.io.BufferedReader;
import java.net.URLDecoder;
import java.util.Objects;

import com.huang.beans.FileBean;

public class Request {
	private BufferedReader input;

	private FileBean bean;

	public void setBean(FileBean bean) {
		this.bean = bean;
	}

	public Request(BufferedReader input) {
		this.input = input;
		//		bean.setType("");
		//		bean.setPath("");
	}

	/**
	 * 读取path
	 * 如地址为localhost：8080/hello,获取hello
	 */
	public void parse(FileBean bean) {
		this.bean = bean;
		//		StringBuilder request = new StringBuilder();
		try {
			Servlet servlet = new Servlet(bean);
			String info = input.readLine();
			String uri = null;
			if (!Objects.equals(info, null)) {
				uri = URLDecoder.decode(info.split(" ")[0], "utf-8");
			}
			if ("POST".equals(uri)) {
				servlet.doPost(input);
			}
			else if ("GET".equals(uri)) {
				servlet.doGet(info, input);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//		this.bean.setType(parseUri(request.toString()));
	}

	//	/**
	//	 * 处理读取到的URI字符串
	//	 * @param requestString
	//	 * @return
	//	 */
	//	private String parseUri(String requestString) {
	//		String uri = "";
	//		if (!Objects.equals(requestString, "null")) {
	//			try {
	//				uri = URLDecoder.decode(requestString.split(" ")[1].substring(1), "utf-8");
	//				if (uri.startsWith("{")) {
	//					JSONObject json = JSONObject.fromObject(uri);
	//					bean.sets(json);
	//					return bean.getType();
	//				}
	//				return uri;
	//			}
	//			catch (UnsupportedEncodingException e) {
	//				return uri;
	//			}
	//		}
	//		else {
	//			return uri;
	//		}
	//	}

	public FileBean getBean() {
		return bean;
	}

}
