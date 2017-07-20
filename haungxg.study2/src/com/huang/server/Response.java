package com.huang.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Properties;

public class Response {
	private static final int BUFFER_SIZE = 1024;

	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webRoot";

	private String contentType;

	Request request;

	OutputStream output;

	PrintWriter writer;

	public Response(OutputStream output) {
		this.output = output;
	}

	public Response() {
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public void sendStaticResource() throws IOException {
		byte[] bytes = new byte[BUFFER_SIZE];
		FileInputStream fis = null;
		try {
			String uri = request.getUri();
			File file = new File(WEB_ROOT, uri);
			fis = new FileInputStream(file);
			int ch = fis.read(bytes, 0, BUFFER_SIZE);
			setContentType(uri);
			String contentType = getContentType();
			output.write(("HTTP/1.1 200 OK\r\n" + contentType + "\r\n\r\n").getBytes());
			while (ch != -1) {
				output.write(bytes, 0, ch);
				ch = fis.read(bytes, 0, BUFFER_SIZE);
			}
		}
		catch (FileNotFoundException e) {
			String errorMessage = "HTTP/1.1 404 File Not Found\r\n" + "Content-Type: text/html\r\n"
					+ "Content-Length: 23\r\n" + "\r\n" + "<h1>File Not Found</h1>";
			output.write(errorMessage.getBytes());
		}
		finally {
			if (fis != null)
				fis.close();
		}
	}

	/** implementation of ServletResponse */
	public void flushBuffer() throws IOException {
	}

	public int getBufferSize() {
		return 0;
	}

	public String getCharacterEncoding() {
		return null;
	}

	public Locale getLocale() {
		return null;
	}

	public OutputStream getOutputStream() throws IOException {
		return output;
	}

	public PrintWriter getWriter() throws IOException {
		writer = new PrintWriter(output, true);
		return writer;
	}

	public boolean isCommitted() {
		return false;
	}

	public void reset() {
	}

	public void resetBuffer() {
	}

	public void setBufferSize(int size) {
	}

	public void setContentLength(int length) {
	}

	public void setContentType(String type) {
		if (type == null) {
			return;
		}
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = getClass().getResourceAsStream("/data.properties");
			prop.load(in);
			contentType = prop.getProperty(type);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getContentType() {
		return contentType;
	}

}