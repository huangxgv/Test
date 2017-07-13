package com.huang.action;

import com.huang.common.Common;
import com.huang.dao.DelFileDao;

public class FileAction {

	private String uri;

	private String path;

	private String name;

	private String context;

	private String isFile;

	public FileAction(String uri, String path, String name, String context, String isFile) {
		this.uri = uri;
		this.path = "D:/TestTwo/" + path;
		this.name = this.path + "/" + name;
		this.context = context;
		this.isFile = isFile;
	}

	public String getPath() {
		return this.path;
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
				case "watch":
					String liString = com.getFolderList(path);
					sendString.append(httpHeader);
					sendString.append("Content-Type:text/html\r\n\r\n");
					sendString.append(liString).append("\r\n");
					break;
				case "update":
					sendString.append(httpHeader);
					sendString.append("Content-Type:text/html\r\n\r\n");
					sendString.append(new DelFileDao().updateFile(path, name, context));
					break;
				case "download":
					sendString.append(new String(com.file2buf(path)));
					break;
				case "delete":
					new DelFileDao().delFile(path);
					sendString.append(httpHeader);
					sendString.append("Content-Type:text/html\r\n\r\n");
					sendString.append("Delete Success").append("\r\n");
					break;
				case "create":
					sendString.append(httpHeader);
					sendString.append("Content-Type:text/html\r\n\r\n");
					sendString.append(new DelFileDao().createFile(name, isFile)).append("\r\n");
					break;
				//				case "file":
				////					sendString.append(httpHeader);
				////					sendString.append("Content-Type:text/html\r\n\r\n");
				//					sendString.append(path).append("\r\n");
				//					break;
				case "search":
					break;
				default:
					sendString.append(uri);
					break;
			}
		}
		catch (Exception e) {
			return null;
		}
		return new String(sendString);
	}
}
