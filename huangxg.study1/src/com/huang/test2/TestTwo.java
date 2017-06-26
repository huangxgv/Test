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
	 * 将int类型数转换成16进制字符串
	 * 
	 * <pre>
	 * intToHex(0)="0"
	 * intToHex(-1)="-1"
	 * intToHex(15)="F"
	 * intToHex(Integer.MIN_VALUE)="-80000000"
	 * intToHex(Integer.MAX_VALUE)="7FFFFFFF"
	 * </pre>
	 * @param num
	 * @return
	 */
	public String intToHex(int num) {
		/**
		 * 解决最小int值-2147483648的绝对值超出int上限
		 */
		if (num == Integer.MIN_VALUE) {
			return "-80000000";
		}
		if (num == 0)
			return "0";
		boolean plusMinusFlag = false;
		int len = 8;
		if (num < 0) {
			len++;
			plusMinusFlag = true;
			num = Math.abs(num);
		}
		char[] hex = new char[len];
		while (num != 0) {
			hex[--len] = CHARNUM[num & 0xF];
			num = num >>> 4;
		}
		if (plusMinusFlag) {
			hex[--len] = '-';
		}
		return new String(hex, len, plusMinusFlag ? 9 - len : 8 - len);
	}
}
