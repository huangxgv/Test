package com.huang.test2;

public class Testtwo {
	/**
	 * 将输入的整数转化为16进制数以字符串形式输出
	 * 
	 * @author huangxg@succez.com
	 * @createdate 2017/6/14
	 */
	String intToHex(int num) {
		String[] charnum = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
		String hex1 = "";
		String plusMinusFlag = "";
		if (num < 0) {
			/**
			 * 解决最小int值-2147483648的绝对值超出int上限
			 */
			if (num == Integer.MIN_VALUE) {
				return "-80000000";
			}
			plusMinusFlag = "-";
			num = Math.abs(num);
		}
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
		return plusMinusFlag + new String(hex);
	}
}
