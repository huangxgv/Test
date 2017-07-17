package com.huang.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class PrimitiveServlet implements Servlet {
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init");
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		System.out.println("from service");
		PrintWriter out = response.getWriter();
		out.println("HTTP/1.1 200 OK\r\n" + "Content-Type:text/html\r\n\r\n" + "<html><head><title>linkin.html</title>"
				+ "<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">"
				+ "<meta http-equiv='description' content='this is my page'>"
				+ "<meta http-equiv='content-type' content='text/html; charset=UTF-8'>"
				+ "<!--<link rel='stylesheet' type='text/css' href='./styles.css'>-->"
				+ "</head><h1>NightWish，气质在作祟。。。</h1><br></body></html>");
		out.println("值得注意的是，要是去web应用去找一个文件那么直接指定物理路径找就好了，要是用类加载加载一个class文件，是要在classpath里面去找的");
		out.println("现在一般不需要配置java的classpath了，默认情况就是当前的工作路径，也就是这个项目的bin下面的路径，在类加载找这个文件的时候一定要写上包名");
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