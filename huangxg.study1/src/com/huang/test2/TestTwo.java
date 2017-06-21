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
	/**
	 * 20170621 huangxg
	 * 修改字符串的拼接带来的内存浪费问题
	 * 
	 * @param num
	 * @return num=0,直接返回"0"，num=Integer.MIN_VALUE,直接返回"-80000000"
	 */
	public String intToHex(int num) {
		char[] charnum = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char plusMinusFlag = ' ';
		//取0和取1都可以的情况下，取与1可避免1位数的循环
		int len = 1;
		if (num < 0) {
			/**
			 * 解决最小int值-2147483648的绝对值超出int上限
			 */
			if (num == Integer.MIN_VALUE) {
				return "-80000000";
			}
			len++;
			plusMinusFlag = '-';
			num = Math.abs(num);
		}
		/**
		 * 解决num为0时，while不循环导致程序不能输出的错误
		 */
		if (num == 0)
			return "0";
		int num1 = num;
		while (num1 / 16 != 0) {
			num1 = num1 / 16;
			len++;
		}
		char[] hex = new char[len];
		hex[0] = plusMinusFlag;
		while (num != 0) {
			len--;
			hex[len] = charnum[num % 16];
			num = num / 16;
		}
		return new String(hex);
	}
}
