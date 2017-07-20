package com.huang.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.huang.beans.FileBean;
import com.huang.common.Common;
import com.huang.server.Request;
import com.huang.server.Response;

public class Watch implements Servlet {
	@Override
	public void init() {
		System.out.println("init");
	}

	@Override
	public void service(Request request, Response response) throws IOException {
		System.out.println("from service watch");
		OutputStream out = response.getOutputStream();
		String contentType = null;
		String filePath = null;
		String parma = FileBean.parma == null ? "" : FileBean.parma;
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = getClass().getResourceAsStream("/data.properties");
			prop.load(in);
			contentType = prop.getProperty("Watch");
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
		out.write(("HTTP/1.1 200 OK\r\n" + contentType + "\r\n").getBytes("UTF-8"));
		System.out.println(filePath + "/" + FileBean.parma);
		String path = filePath + "/" + FileBean.parma;
		File file = new File(filePath + "/" + FileBean.parma);
		if (file == null) {
			return;
		}
		if (!file.exists()) {
			return;
		}
		if (file.isDirectory()) {
			out.write((Common.getFolderList(filePath + "/" + parma)).getBytes("UTF-8"));
		}
		if (file.isFile()) {
			Common.fileRead(out, path);
		}
		out.flush();
		out.close();
	}

	@Override
	public void destroy() {
		System.out.println("destroy");
	}

	public String getServletInfo() {
		return null;
	}

}