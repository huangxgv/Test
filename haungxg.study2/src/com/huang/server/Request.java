package com.huang.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Locale;

import com.huang.beans.FileBean;

public class Request {
	private InputStream input;

	private static String uri;

	public Request(InputStream input) {
		this.input = input;
	}

	public static String getUri() {
		return uri;
	}

	public void doPost(String requestString) {
		String[] requestArr = requestString.split("\n\r");
		FileBean.parma = requestArr[1];
	}

	private String parseUri(String requestString) {
		try {
			requestString = URLDecoder.decode(requestString, "utf-8");
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String[] pramArr = null;
		int index1, index2;
		index1 = requestString.indexOf(' ');
		if (index1 != -1) {
			index2 = requestString.indexOf(' ', index1 + 1);
			if (index2 > index1) {
				pramArr = (requestString.substring(index1 + 1, index2)).split("[?]");
				if (requestString.startsWith("POST")) {
					doPost(requestString);
				}
				else if (pramArr.length > 1) {
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
		int i;
		byte[] buffer = new byte[8192];
		try {
			i = input.read(buffer);
		}
		catch (IOException e) {
			e.printStackTrace();
			i = -1;
		}
		String request = null;
		try {
			request = new String(buffer, "UTF-8");
		}
		catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			System.out.print(URLDecoder.decode(request, "utf-8"));
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		uri = parseUri(request);
	}

	public Object getAttribute(String attribute) {
		return null;
	}

	public String getRealPath(String path) {
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

	public Locale getLocale() {
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
}