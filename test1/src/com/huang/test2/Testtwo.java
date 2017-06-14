package com.huang.test2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Testtwo {
/*
 * 通过输入一个整数，将其转化为16进制数以字符串形式输出
 * @author huangxg@succez.com
 * @createdate 2017/6/14
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner out = new Scanner(System.in);
		int numInt=out.nextInt();
		String result=intToHex(numInt);
		System.out.println(result);
	}
   static String intToHex(int num){
	   String[] charnum={"0","1","2","3","4","5","6","7"
			   ,"8","9","A","B","C","D","E","F"};
	   String hex1="";
	   //在while循环前处理输入的数字为0，避免程序出错
	   if(num==0)
		   return "0";
	   while(num!=0){
		   hex1=hex1+charnum[num%16];
		   num=num/16;
	   }
	   char[] hex2=new char[hex1.length()];
	   char[] hex=new char[hex1.length()];
	   hex2=hex1.toCharArray();
	   for(int i=0;i<hex1.length();i++){
		   hex[hex1.length()-1-i]=hex2[i];
	   }
	   return new String(hex);
   }
}
