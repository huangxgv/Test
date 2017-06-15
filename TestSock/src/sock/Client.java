package sock;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端
 */
public class Client extends Socket {

	private int SERVER_PORT;

	private Socket client;

	private DataInputStream dis;

	private FileOutputStream fos;
	
	public Client(String SERVER_IP,int SERVER_PORT) {

		try {
			try {
				client = new Socket(SERVER_IP, SERVER_PORT);
				
				dis = new DataInputStream(client.getInputStream());
				//文件名和长度
				String fileName = dis.readUTF();
				long fileLength = dis.readLong();
				fos = new FileOutputStream(new File("d:/" + fileName));

				byte[] sendBytes = new byte[1024];
				int transLen = 0;
				System.out.println("----开始接收文件<" + fileName + ">,文件大小为<" + fileLength + ">----");
				while (true) {
					int read = 0;
					if ((read = dis.read(sendBytes)) == -1)
						break;
					transLen += read;
					System.out.println("接收文件进度" + 100 * transLen / fileLength + "%...");
					fos.write(sendBytes, 0, read);
					fos.flush();
				}
				System.out.println("----接收文件<" + fileName + ">成功-------");
				client.close();
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (fos != null)
					fos.close();
				if (dis != null)
					dis.close();
				client.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.out.print("请输入文件下载地址:");
		Scanner out=new Scanner(System.in);
		String url=out.nextLine();
		String[] strArr=url.split(":");
		new Client(strArr[0],Integer.parseInt(strArr[1]));
	}
}