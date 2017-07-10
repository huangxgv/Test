package com.huang.action;

import com.huang.common.Common;
import com.huang.dao.DelFileDao;

public class FileAction {

	private String uri;

	private String path;

	private String name;

	private String context;

	private String isFile;

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

	public void setName(String name) {
		this.name = "D:/TestTwo/" + name;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public void setIsFile(String isFile) {
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
		//		String select;
		//		System.out.println(uri);
		//		if (uri == "" || uri == null) {
		//			select = "";
		//		}
		//		else {
		//			String[] selectArr = uri.split("");
		//			select = selectArr[selectArr.length - 1];
		//		}
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
					sendString.append("folderImg.png").append("\r\n");
					break;
				case "watch":
					sendString.append(httpHeader);
					sendString.append("Access-Control-Allow-Origin:*\r\n\r\n");
					sendString.append(com.getFolderList(path)).append("\r\n");
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
