package com.huang.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.huang.beans.FileBean;

public class Request implements ServletRequest {
	private InputStream input;

	private static String uri;

	public Request(InputStream input) {
		this.input = input;
	}

	public static String getUri() {
		return uri;
	}

	//	public String doGet(String requestString) {
	//		String[] parmArr = null;
	//		int index1, index2;
	//		index1 = requestString.indexOf(' ');
	//		if (index1 != -1) {
	//			index2 = requestString.indexOf(' ', index1 + 1);
	//			if (index2 > index1) {
	//				parmArr = (requestString.substring(index1 + 1, index2)).split("[?]");
	//				if (parmArr.length > 1) {
	//					FileBean.parma = parmArr[1];
	//					return parmArr[1];
	//				}
	//			}
	//		}
	//		return null;
	//	}

	public String doPost(String requestString) {
		return null;
	}

	private String parseUri(String requestString) {
		String[] pramArr = null;
		int index1, index2;
		index1 = requestString.indexOf(' ');
		if (index1 != -1) {
			index2 = requestString.indexOf(' ', index1 + 1);
			if (index2 > index1) {
				pramArr = (requestString.substring(index1 + 1, index2)).split("[?]");
				if (pramArr.length > 1) {
					FileBean.parma = pramArr[1];
				}
				else {
					FileBean.parma = "";
				}
				return pramArr[0];
			}
		}
		return null;
	}

	public void parse() {
		// Read a set of characters from the socket  
		StringBuffer request = new StringBuffer(2048);
		int i;
		byte[] buffer = new byte[2048];
		try {
			i = input.read(buffer);
		}
		catch (IOException e) {
			e.printStackTrace();
			i = -1;
		}
		System.out.println(System.currentTimeMillis());
		for (int j = 0; j < i; j++) {
			request.append((char) buffer[j]);
		}
		System.out.println(System.currentTimeMillis());
		System.out.print(request.toString());
		uri = parseUri(request.toString());
	}

	public Object getAttribute(String attribute) {
		return null;
	}

	public Enumeration getAttributeNames() {
		return null;
	}

	public String getRealPath(String path) {
		return null;
	}

	public RequestDispatcher getRequestDispatcher(String path) {
		return null;
	}

	public boolean isSecure() {
		return false;
	}

	public String getCharacterEncoding() {
		return null;
	}

	public int getContentLength() {
		return 0;
	}

	public String getContentType() {
		return null;
	}

	public ServletInputStream getInputStream() throws IOException {
		return null;
	}

	public Locale getLocale() {
		return null;
	}

	public Enumeration getLocales() {
		return null;
	}

	public String getParameter(String requestString) {
		if (requestString.startsWith("POST")) {
			return doPost(requestString);
		}
		return null;
	}

	public Map getParameterMap() {
		return null;
	}

	public Enumeration getParameterNames() {
		return null;
	}

	public String[] getParameterValues(String parameter) {
		return null;
	}

	public String getProtocol() {
		return null;
	}

	public BufferedReader getReader() throws IOException {
		return null;
	}

	public String getRemoteAddr() {
		return null;
	}

	public String getRemoteHost() {
		return null;
	}

	public String getScheme() {
		return null;
	}

	public String getServerName() {
		return null;
	}

	public int getServerPort() {
		return 0;
	}

	public void removeAttribute(String attribute) {
	}

	public void setAttribute(String key, Object value) {
	}

	public void setCharacterEncoding(String encoding) throws UnsupportedEncodingException {
	}

	@Override
	public String getLocalAddr() {
		return null;
	}

	@Override
	public String getLocalName() {
		return null;
	}

	@Override
	public int getLocalPort() {
		return 0;
	}

	@Override
	public int getRemotePort() {
		return 0;
	}

	@Override
	public AsyncContext getAsyncContext() {
		return null;
	}

	@Override
	public long getContentLengthLong() {
		return 0;
	}

	@Override
	public DispatcherType getDispatcherType() {
		return null;
	}

	@Override
	public ServletContext getServletContext() {
		return null;
	}

	@Override
	public boolean isAsyncStarted() {
		return false;
	}

	@Override
	public boolean isAsyncSupported() {
		return false;
	}

	@Override
	public AsyncContext startAsync() throws IllegalStateException {
		return null;
	}

	@Override
	public AsyncContext startAsync(ServletRequest arg0, ServletResponse arg1) throws IllegalStateException {
		return null;
	}
}