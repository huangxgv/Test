package com.huang.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Watch implements Servlet {
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init");
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		System.out.println("from service");
		PrintWriter out = response.getWriter();
		String contentType = null;
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = getClass().getResourceAsStream("/data.properties");
			prop.load(in);
			contentType = prop.getProperty("watch");
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
		out.println("HTTP/1.1 200 OK\r\n" + contentType + "\r\n\r\n" + "112233");
		//		out.println("值得注意的是，要是去web应用去找一个文件那么直接指定物理路径找就好了，要是用类加载加载一个class文件，是要在classpath里面去找的");
		//		out.println("现在一般不需要配置java的classpath了，默认情况就是当前的工作路径，也就是这个项目的bin下面的路径，在类加载找这个文件的时候一定要写上包名");
	}

	public void destroy() {
		System.out.println("destroy");
	}

	public String getServletInfo() {
		return null;
	}

	public ServletConfig getServletConfig() {
		return null;
	}

}