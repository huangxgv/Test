package com.huang.test2;

/**
 * 将输入的整数转化为16进制数以字符串形式输出
 * 
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huangxg
 * @createdate 2017年6月21日
 */
public class TestTwo {
	private static final char[] CHARNUM = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	/**
	 * 20170621 huangxg
	 * 修改字符串的拼接带来的内存浪费问题
	 * 
	 * @param num
	 * @return num=0,直接返回"0"，num=Integer.MIN_VALUE,直接返回"-80000000"
	 */
	public String intToHex(int num) {
		/**
		 * 解决最小int值-2147483648的绝对值超出int上限
		 */
		if (num == Integer.MIN_VALUE) {
			return "-80000000";
		}
		/**
		 * 解决num为0时，while不循环导致程序不能输出的错误
		 */
		if (num == 0)
			return "0";
		char plusMinusFlag = ' ';
		int len = 8;
		if (num < 0) {
			len++;
			plusMinusFlag = '-';
			num = Math.abs(num);
		}
		char[] hex = new char[len];
		while (num != 0) {
			hex[--len] = CHARNUM[num & 0xF];
			num = num >>> 4;
		}
		if (plusMinusFlag == '-') {
			hex[--len] = plusMinusFlag;
		}
		return new String(hex, len, plusMinusFlag == '-' ? 9 - len : 8 - len);
	}
}
