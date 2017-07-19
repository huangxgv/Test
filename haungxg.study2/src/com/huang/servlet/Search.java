package com.huang.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Properties;

import com.huang.beans.FileBean;
import com.huang.common.Common;
import com.huang.server.Request;
import com.huang.server.Response;

public class Search implements Servlet {
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
			contentType = prop.getProperty("Search");
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
		String[] paramArr = parma.split("&");
		StringBuilder sb = new StringBuilder();
		Common.getPath(filePath.length(), sb, filePath + paramArr[0], paramArr[1]);
		if (Objects.equals(sb, null)) {
			return;
		}
		//		int len = filePath.length();
		//		sb.replace(0, len + 1, "");
		//		String reString = new String(sb);
		filePath = filePath.replace("/", "\\");
		//		String[] resultArr = reString.split(filePath);
		//		for (int i = 0, length = resultArr.length; i < length; i++) {
		//			resultArr[i] = resultArr[i].replace("\\", "/");
		//		}
		//		reString = reString.replace("\\", "/");
		System.out.println("&&&&&&&&" + sb);
		String result = new String(sb);
		out.print("HTTP/1.1 200 OK\r\n" + contentType + "\r\n");
		out.println("Access-Control-Allow-Origin:*\r\n");
		out.print(result.replace("\\", "/"));
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