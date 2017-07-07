package com.huang.server;

import java.io.IOException;
import java.io.OutputStreamWriter;

import com.huang.action.FileAction;

public class Response {
	private OutputStreamWriter output;

	private Request request;

	public Response(OutputStreamWriter output) {
		this.output = output;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public void sendStaticResource() {
		StringBuilder sendString = new StringBuilder();
		FileAction action = new FileAction();
		action.setDirect(request.getUri());
		action.setPath(request.getPath());
		sendString.append(action.getSendString());
		try {
			if (!(new String(sendString).startsWith("HTTP/1.1 200 OK\r\n"))) {
				String[] pathArr = request.getPath().split("/");
				String path = pathArr[pathArr.length - 1];
				output.write("HTTP/1.1 200 OK\r\n");
				output.write("Content-Disposition:attachment;filename=" + path + "\r\n\r\n");
				output.write(action.getSendString());
			}
			else {
				output.write(new String(sendString));
			}
			output.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
