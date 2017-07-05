package com.huang.action;

import com.huang.common.Common;

public class FileAction {

	private String uri;

	private String path;

	/**
	 * 获取action类型
	 * @param uri
	 */
	public void setDirect(String uri) {
		this.uri = uri;
	}

	/**
	 * 获取action路径
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 获取后台输出字符串
	 * @return
	 */
	public String getSendString() {

		StringBuilder sendString = new StringBuilder();

		Common com = new Common();

		String httpHeader = "HTTP/1.1 200 OK\r\n";
		try {
			switch (uri) {
				case "":
					sendString.append(httpHeader);
					sendString.append("Content-Type:text/html\r\n\r\n");
					sendString.append(com.file2buf("src/com/huang/front/index.html")).append("\r\n");
					break;
				case "style.css":
					sendString.append(httpHeader);
					sendString.append("Content-Type:text/css\r\n\r\n");
					sendString.append(com.file2buf("src/com/huang/front/style.css")).append("\r\n");
					break;
				case "control.js":
					sendString.append(httpHeader);
					sendString.append("Content-Type:application/x-javascript\r\n\r\n");
					sendString.append(com.file2buf("src/com/huang/front/control.js")).append("\r\n");
					break;
				case "folderImg.png":
					sendString.append(httpHeader);
					sendString.append("Content-Type:image/png\r\n\r\n");
					sendString.append(com.file2buf("folderImg.png")).append("\r\n");
					break;
				case "watch":
					sendString.append(httpHeader);
					sendString.append("Access-Control-Allow-Origin:*\r\n\r\n");
					sendString.append(com.file2buf(path)).append("\r\n");
					break;
				case "update":
					break;
				case "delete":
					break;
				case "add":
					break;
				case "search":
					break;
				default:
					break;
			}
		}
		catch (Exception e) {
			return null;
		}
		return sendString.toString();
	}
}
