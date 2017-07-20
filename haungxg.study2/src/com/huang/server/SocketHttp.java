package com.huang.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketHttp {

	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

	private boolean shutdown = false;

	public static void main(String[] args) {
		SocketHttp server = new SocketHttp();
		server.await();
	}

	public void await() {
		ServerSocket serverSocket = null;
		int port = 8080;
		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		// Loop waiting for a request
		while (!shutdown) {
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			try {
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();
				Request request = new Request(input);
				request.parse();
				Response response = new Response(output);
				response.setRequest(request);
				String uri = request.getUri();
				if (uri == null) {
					continue;
				}
				if (uri.startsWith("/servlet/")) {
					ServletProcessor processor = new ServletProcessor();
					processor.process(request, response);
				}
				else {
					StaticResourceProcessor processor = new StaticResourceProcessor();
					processor.process(request, response);
				}
				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
			}
			catch (Exception e) {
				e.printStackTrace();
				//				System.exit(1);
			}
			finally {
				if (socket != null) {
					try {
						socket.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}