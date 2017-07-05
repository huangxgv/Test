package com.huang.server;

import java.io.IOException;
import java.io.OutputStreamWriter;

import com.huang.action.FileAction;

public class Response {
	private OutputStreamWriter output;

	private Request request;

	private FileAction action;

	public Response(OutputStreamWriter output) {
		this.output = output;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public void sendStaticResource() {
		StringBuilder sendString = new StringBuilder();
		action.setDirect(request.getUri());
		action.setPath(request.getPath());
		sendString.append(action.getSendString());
		try {
			output.write(sendString.toString());
			output.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (output != null) {
					output.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
