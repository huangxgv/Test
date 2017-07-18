package com.huang.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import com.huang.beans.FileBean;
import com.huang.common.Common;
import com.huang.server.Request;
import com.huang.server.Response;

import net.sf.json.JSONObject;

public class Update implements Servlet {
	@Override
	public void init() {
		System.out.println("init");
	}

	@Override
	public void service(Request request, Response response) throws IOException {
		System.out.println("from service update");
		PrintWriter out = response.getWriter();
		String contentType = null;
		String filePath = null;
		if (FileBean.parma == null) {
			return;
		}
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = getClass().getResourceAsStream("/data.properties");
			prop.load(in);
			contentType = prop.getProperty("Update");
			filePath = prop.getProperty("path");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		out.println("HTTP/1.1 200 OK\r\n" + contentType + "\r\n");
		System.out.println("#########:" + FileBean.parma);
		JSONObject jsonString = JSONObject.fromObject(FileBean.parma);
		String path = filePath + "/" + jsonString.getString("path");
		String param = jsonString.getString("context");
		File file = new File(path);
		if (file == null) {
			return;
		}
		if (file.exists()) {
			Common.updateFileContext(path, param);
		}
		out.flush();
		out.close();
	}

	public void destroy() {
		System.out.println("destroy");
	}

	public String getServletInfo() {
		return null;
	}

}