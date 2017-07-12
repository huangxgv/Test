package com.huang.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

	//	protected void doGet(Request request, Response response) {
	//		FileBean bean = new FileBean();
	//		request.parse(bean);
	//		response.setBean(request.getBean());
	//		response.sendStaticResource();
	//	}
	//
	//	protected void doPost(Request request, Response response) {
	//		doGet(request, response);
	//	}

	public void run() {
		BufferedReader input = null;
		OutputStreamWriter output = null;
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new OutputStreamWriter(socket.getOutputStream());
			Request request = new Request(input);
			Response response = new Response(output);
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