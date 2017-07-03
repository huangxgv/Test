package com.huang.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;

import com.huang.beans.DoInfoBean;
import com.huang.dao.DelFileDao;

/**
 * 用socket来收发http协议报文
 */
public class SocketHttp {
	public static void main(String[] args) {
		Thread threadReceive = new Thread(new TestReceiveHttp());
		threadReceive.start();
	}
}

class TestReceiveHttp implements Runnable {
	@Override
	public void run() {
		ServerSocket server;
		Socket socket;
		try {
			server = new ServerSocket(8080);
			System.out.println("正在等待8080端口的请求");
			while (true) {
				socket = server.accept();
				if (socket != null) {
					new Thread(new TestReveiveThread(socket)).start();
				}
			}
		}
		catch (Exception e) {
			System.out.println("异常");
		}
	}
}

class TestReveiveThread implements Runnable {
	private Socket socket;

	public TestReveiveThread(Socket s) {
		socket = s;
	}

	public void run() {
		BufferedReader bufferedReader = null;
		OutputStreamWriter osw = null;
		DoInfoBean doBean = new DoInfoBean();
		DelFileDao fileDao = new DelFileDao();
		String result = new String();
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			osw = new OutputStreamWriter(socket.getOutputStream(), "utf-8");
			String line = bufferedReader.readLine();
			String orderString = URLDecoder.decode(line.split(" ")[1].substring(1), "utf-8");
			System.out.println(orderString);
			if (doBean.setDoInfo(orderString)) {
				switch (doBean.getType()) {
					case "search":
						result = fileDao.searchFile(doBean);
						break;
					case "delete":
						result = fileDao.delFile(doBean);
						break;
					case "update":
						result = fileDao.updateFile(doBean);
						break;
					case "add":
						result = fileDao.addFile(doBean);
						break;
					case "download":
						result = fileDao.addFile(doBean);
						break;
					case "watch":
						result = fileDao.getFolderList(doBean);
						break;
					default:
						break;
				}
			}
			//使用append代替多次write可以提升服务器响应速度
			StringBuffer sb = new StringBuffer("HTTP/1.1 200 OK\r\n");
			sb.append("Access-Control-Allow-Origin:*\r\n\r\n");
			sb.append(result);
			osw.write(new String(sb));
			osw.flush();
		}
		catch (Exception e) {
			System.out.println("客户端接受异常" + e.getMessage());
		}
		finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			}
			catch (IOException e) {
			}
			try {
				if (osw != null) {
					osw.close();
				}
			}
			catch (IOException e) {
			}
			try {
				if (socket != null) {
					socket.close();
				}
			}
			catch (IOException e) {
			}
		}
	}
}