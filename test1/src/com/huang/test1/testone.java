package com.huang.test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
/*
 * 读取文件内容并以byte数组返回，若文件为空或者读取文件错误则返回null
 * @author huangxg@succez.com
 * createdate 2017/6/14
 */
public class testone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 File file = new File("D:/workSpace/test1", "fileread.txt"); 
		 byte[] byts=file2buf(file);
		 for(int i=0;i<byts.length;i++){
			 System.out.print(byts[i]+"  ");
			 if(i%5==0){
				 System.out.println();
			 }
		 }
	}
	static byte[] file2buf(File fobj) {
		//获取文件长度，动态设置byte数组长度，避免数组过长带来的内存浪费或者过短造成数组越界问题
		int len=(int) fobj.length();
		byte[] bts=new byte[len];
		
		try {
			FileInputStream fis=new FileInputStream(fobj);
			InputStreamReader isr = new InputStreamReader(fis);  
            int ch = 0;
            int index=0;
            while ((ch = isr.read()) != -1) {  
               bts[index]=(byte)ch;
               index++;
            }  
		} catch (Exception e) {
			return null;
		}
		if(bts.length==0){
			return null;
		}
		return bts;
	}
}
