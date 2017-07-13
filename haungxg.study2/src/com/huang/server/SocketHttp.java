package com.huang.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.huang.beans.FileBean;
import com.huang.common.Common;

/**
 * 用socket来收发http协议报文
 */
public class SocketHttp {
	public static void main(String[] args) {
		//		ThreadPool t = ThreadPool.getThreadPool(3);
		//		t.execute(new Runnable[] { new TestReceiveHttp(), new TestReceiveHttp(), new TestReceiveHttp() });
		//		//		t.execute(new Runnable[] { new Task(), new Task(), new Task() });
		//		t.destroy();// 所有线程都执行完成才destory  
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
		InputStream input = null;
		OutputStream output = null;
		try {
			input = socket.getInputStream();
			output = socket.getOutputStream();
			Request request = new Request(socket.getInputStream());
			Response response = new Response(socket.getOutputStream());
			FileBean bean = new FileBean();
			request.parse(bean);
			response.setBean(request.getBean());
			response.sendStaticResource();
		}
		catch (Exception e) {
			System.out.println("客户端接受异常" + e.getMessage());
			e.printStackTrace();
		}
		finally {
			new Common().closeSource(input, output, socket);
		}
	}
}