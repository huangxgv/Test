package com.huang.action;

import com.huang.common.Common;
import com.huang.dao.DelFileDao;

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
		this.path = "D:/TestTwo/" + path;
		System.out.println(this.path);
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
				case "":
					sendString.append(httpHeader);
					sendString.append("Content-Type:text/html\r\n\r\n");
					sendString.append(new String(com.file2buf("src/com/huang/front/index.html"))).append("\r\n");
					break;
				case "style.css":
					sendString.append(httpHeader);
					sendString.append("Content-Type:text/css\r\n\r\n");
					sendString.append(new String(com.file2buf("src/com/huang/front/style.css"))).append("\r\n");
					break;
				case "control.js":
					sendString.append(httpHeader);
					sendString.append("Content-Type:application/x-javascript\r\n\r\n");
					sendString.append(new String(com.file2buf("src/com/huang/front/control.js"))).append("\r\n");
					break;
				case "folderImg.png":
					sendString.append(httpHeader);
					sendString.append("Content-Type:image/png\r\n\r\n");
					sendString.append(new String(com.file2buf("folderImg.png"), "UTF-8")).append("\r\n");
					break;
				case "watch":
					sendString.append(httpHeader);
					sendString.append("Access-Control-Allow-Origin:*\r\n\r\n");
					sendString.append(com.getFolderList(path)).append("\r\n");
					break;
				case "update":
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
				case "add":
					break;
				case "file":
					sendString.append(httpHeader);
					sendString.append("Content-Type:text/html\r\n\r\n");
					sendString.append(new DelFileDao().getFile(path)).append("\r\n");
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
		return new String(sendString);
	}
}
