package com.huang.server;

import java.io.IOException;
import java.io.OutputStream;

import com.huang.action.FileAction;
import com.huang.beans.FileBean;
import com.huang.common.Common;

public class Response {
	private OutputStream output;

	private FileBean bean;

	public Response(OutputStream output) {
		this.output = output;
	}

	public void setBean(FileBean bean) {
		this.bean = bean;
	}

	public void sendStaticResource() {
		//		StringBuilder sendString = new StringBuilder();
		FileAction action = new FileAction(bean.getType(), bean.getPath(), bean.getName(), bean.getContext(),
				bean.getIsFile());
		String contentType = "";
		Common com = new Common();
		String actionResult = action.getSendString();
		try {
			if (!(actionResult.startsWith("HTTP/1.1 200 OK\r\n"))) {
				String[] pathArr = bean.getPath().split("/");
				String path = pathArr[pathArr.length - 1];

				if (actionResult.endsWith(".html")) {
					actionResult = "webRoot/front/html/" + actionResult;
					contentType = "Content-Type:text/html\r\n\r\n";
				}
				else if (actionResult.endsWith(".css")) {
					actionResult = "webRoot/front/css/" + actionResult;
					contentType = "Content-Type:text/css\r\n\r\n";
				}
				else if (actionResult.endsWith(".js")) {
					actionResult = "webRoot/front/js/" + actionResult;
					contentType = "Content-Type:application/x-javascript\r\n\r\n";
				}
				else if (actionResult.endsWith(".docx")) {
					actionResult = "D:/TestTwo/" + actionResult;
					contentType = "Content-Type:text/html\r\n\r\n";
				}
				else {
					contentType = "Content-Disposition:attachment;filename=" + path + "\r\n\r\n";
				}
				output.write(("HTTP/1.1 200 OK\r\n" + contentType).getBytes("UTF-8"));
				//				output.write(contentType.getBytes("UTF-8"));
				output.write(com.file2buf(actionResult));
			}
			else {
				output.write(actionResult.getBytes("UTF-8"));
				//				output.write("\n\r\n\r".getBytes("UTF-8"));
			}
			output.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
