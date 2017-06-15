package com.huang.test2;

import java.util.Scanner;

public class Testtwo {
	/**
	 * 将输入的整数转化为16进制数以字符串形式输出
	 * 
	 * @author huangxg@succez.com
	 * @createdate 2017/6/14
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner out = new Scanner(System.in);
		int numInt = out.nextInt();
		String result = intToHex(numInt);
		System.out.println(result);
	}

	/**
	 * 
	 * @param {num}
	 *			num 原十进制数
	 * @return
	 */
	static String intToHex(int num) {
		String[] charnum = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
		String hex1 = "";
		/**
		 * 解决num为0时，while不循环导致程序不能输出的错误
		 */
		if (num == 0)
			return "0";
		while (num != 0) {
			hex1 = hex1 + charnum[num % 16];
			num = num / 16;
		}
		char[] hex = new char[hex1.length()];
		char[] hex2 = new char[hex1.length()];
		hex2 = hex1.toCharArray();
		for (int i = 0; i < hex1.length(); i++) {
			hex[hex1.length() - 1 - i] = hex2[i];
		}
		return new String(hex);
	}
}
