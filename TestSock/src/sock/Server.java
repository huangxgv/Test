package sock;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器
 */
public class Server extends ServerSocket {

	private static final int PORT = 2016;

	private ServerSocket server;

	private Socket client;
	
	private FileInputStream fis;

	private DataOutputStream dos;


	public Server() throws Exception {
		try {
			try {
				server = new ServerSocket(PORT);

				while (true) {
					client = server.accept();

					//向客户端传送文件
					File file = new File("D:/download/test.txt");
					fis = new FileInputStream(file);
					dos = new DataOutputStream(client.getOutputStream());
					
					//文件名和长度
					dos.writeUTF(file.getName());
					dos.flush();
					dos.writeLong(file.length());
					dos.flush();

					//传输文件
					byte[] sendBytes = new byte[1024];
					int length = 0;
					while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0) {
						dos.write(sendBytes, 0, length);
						dos.flush();
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (dos != null)
					dos.close();
				if (fis != null)
					fis.close();
				server.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		new Server();
	}
}