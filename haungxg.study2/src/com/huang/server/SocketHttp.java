package com.huang.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

import com.huang.dao.DelFileDao;

public class Service {
	private int port = 8000;

	private ServerSocket serverSocket;

	public Service() throws IOException {
		serverSocket = new ServerSocket(port);
		System.out.println("服务器启动");
	}

	public PrintWriter getWriter(Socket socket) throws IOException {
		OutputStream socketOut = socket.getOutputStream();
		return new PrintWriter(socketOut, true);
	}

	public BufferedReader getReader(Socket socket) throws IOException {
		InputStream socketIn = socket.getInputStream();
		return new BufferedReader(new InputStreamReader(socketIn));
	}

	private StringBuilder getFolder(String path) {
		File f = new File(path);
		if (!f.exists()) {
			return null;
		}
		File fa[] = f.listFiles();
		int length = fa.length;
		StringBuilder folderNameArr = new StringBuilder();
		for (int i = 0; i < length; i++) {
			folderNameArr.append(fa[i].getName()).append(':');
		}
		return folderNameArr;
	}

	public void service() {
		while (true) {
			Socket socket = null;
			DelFileDao dFile = new DelFileDao();
			try {
				socket = serverSocket.accept();
				BufferedReader br = getReader(socket);
				PrintWriter pw = getWriter(socket);
				String path = br.readLine();
				if (!Objects.equals(path, null)) {
					path = path.split(" ")[1].substring(1);
					StringBuilder reString = getFolder(path);
					pw.write(path);
					System.out.println(reString);
				}

			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				try {
					if (socket != null)
						socket.close();
				}
				catch (IOException e) {
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new Service().service();
	}

}
