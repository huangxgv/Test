package com.huang.test1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 读取文件内容并以byte数组返回，若文件为空或者读取文件错误则返回null
 * 
 * @author huangxg@succez.com
 * @createdate 2017/6/14
 */
public class TestOne {

	/**
	 * 用byte数组替换读取到的文件内容
	 * 
	 * @param fobj
	 * @return
	 */
	public byte[] file2buf(File fobj) {
		if (!fobj.exists()) {
			return null;
		}
		FileInputStream fis = null;
		InputStreamReader isr = null;
		//利用缓存加快读取速度
		BufferedReader buf = null;
		String context = "";
		/**
		 * 每次读取一行，将所有读取的String内容连接起来
		 * 将最终字符串一次转换为byte数组
		 */
		try {
			try {
				fis = new FileInputStream(fobj);
				isr = new InputStreamReader(fis);
				buf = new BufferedReader(isr);
				String lins = null;
				while ((lins = buf.readLine()) != null) {
					context += lins;
				}
			}
			catch (Exception e) {
				return null;
			}
			finally {
				if (fis != null) {
					fis.close();
				}
				if (isr != null) {
					isr.close();
				}
				if (buf != null) {
					buf.close();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return context.getBytes();
	}
}
