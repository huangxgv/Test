package com.huang.server;

import java.io.IOException;
import java.io.OutputStreamWriter;

import com.huang.action.FileAction;
import com.huang.beans.FileBean;

public class Response {
	private OutputStreamWriter output;

	private FileBean bean;

	public Response(OutputStreamWriter output) {
		this.output = output;
	}

	public void setBean(FileBean bean) {
		this.bean = bean;
	}

	public void sendStaticResource() {
		StringBuilder sendString = new StringBuilder();
		FileAction action = new FileAction(bean.getType(), bean.getPath(), bean.getName(), bean.getContext(),
				bean.getIsFile());
		sendString.append(action.getSendString());
		try {
			if (!(new String(sendString).startsWith("HTTP/1.1 200 OK\r\n"))) {
				String[] pathArr = bean.getPath().split("/");
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
