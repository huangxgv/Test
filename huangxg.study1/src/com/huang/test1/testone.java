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
public class testone {

	public static void main(String[] args) {
		File file = new File("D:/workSpace/test1", "fileread.txt");
		byte[] byts = file2buf(file);
		for (int i = 0; i < byts.length; i++) {
			System.out.print(byts[i] + "  ");
			if ((i + 1) % 5 == 0) {
				System.out.println();
			}
		}
	}

	/**
	 * 用byte数组替换读取到的文件内容
	 * 
	 * @param fobj
	 * @return
	 */

	static byte[] file2buf(File fobj) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		//利用缓存加快读取速度
		BufferedReader buf = null;
		String context = null;
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
		//通过字符串设置数组长度节约资源空间还不会造成数组脚标越界
		byte[] btyArr = new byte[context.length()];
		if (btyArr.length == 0) {
			return null;
		}
		btyArr = context.getBytes();
		return btyArr;
	}
}
