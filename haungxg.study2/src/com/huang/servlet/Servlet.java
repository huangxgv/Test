package com.huang.servlet;

import java.io.IOException;

import com.huang.server.Request;
import com.huang.server.Response;

public interface Servlet {
	public void init();

	public void service(Request request, Response response) throws IOException;

	public void destroy();
}
