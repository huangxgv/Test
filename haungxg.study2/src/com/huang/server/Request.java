package com.huang.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Objects;

import com.huang.beans.FileBean;

public class Request {
	private InputStream input;

	private FileBean bean;

	public void setBean(FileBean bean) {
		this.bean = bean;
	}

	public Request(InputStream input) {
		this.input = input;
	}

	public BufferedReader fileStreamToBufStream(InputStream input) {
		return new BufferedReader(new InputStreamReader(input));
	}

	/**
	 * 读取path
	 * 如地址为localhost：8080/hello,获取hello
	 */
	public void parse(FileBean bean) {
		this.bean = bean;
		try {
			Servlet servlet = new Servlet(bean);
			BufferedReader bif = fileStreamToBufStream(input);
			String info = bif.readLine();
			String uri = null;
			if (!Objects.equals(info, null)) {
				uri = URLDecoder.decode(info.split(" ")[0], "utf-8");
			}
			if ("POST".equals(uri)) {
				servlet.doPost(info, bif);
			}
			else if ("GET".equals(uri)) {
				servlet.doGet(info, bif);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FileBean getBean() {
		return bean;
	}

}
