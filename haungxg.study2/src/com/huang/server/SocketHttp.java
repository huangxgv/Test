package com.huang.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.huang.common.Common;

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

	protected void doGet(Request request, Response response) {
		request.parse();
		response.setRequest(request);
		response.sendStaticResource();
	}

	protected void doPost(Request request, Response response) {
		doGet(request, response);
	}

	public void run() {
		BufferedReader input = null;
		OutputStreamWriter output = null;
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			output = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
			Request request = new Request(input);
			Response response = new Response(output);
			doGet(request, response);

		}
		catch (Exception e) {
			System.out.println("客户端接受异常" + e.getMessage());
		}
		finally {
			new Common().closeSource(input, output, socket);
		}
	}
}