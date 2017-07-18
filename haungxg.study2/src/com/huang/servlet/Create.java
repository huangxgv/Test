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

public class Create implements Servlet {
	@Override
	public void init() {
		System.out.println("init");
	}

	@Override
	public void service(Request request, Response response) throws IOException {
		System.out.println("from service");
		PrintWriter out = response.getWriter();
		String contentType = null;
		String filePath = null;
		String parma = FileBean.parma == null ? "" : FileBean.parma;
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = getClass().getResourceAsStream("/data.properties");
			prop.load(in);
			contentType = prop.getProperty("Create");
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
		System.out.println(filePath + "/" + FileBean.parma);
		String[] parArr = (FileBean.parma).split("&");
		String path = filePath + "/" + parArr[0];
		File file = new File(path);
		if (file == null) {
			return;
		}
		if (file.exists()) {
			return;
		}
		if ("2".equals(parArr[1])) {
			Common.createDir(file);
		}
		else {
			Common.createFile(file);
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