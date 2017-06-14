package com.huang.test3;

import java.util.ArrayList;
import java.util.Scanner;
/*
 * 根据输入层次按顺序返回二叉树该层树节点
 * @author huangxg@succez.com
 * @createdate 2017/6/14
 */
public class test3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//测试树
		TNode tree8=new TNode("I",null,null);
		TNode tree7=new TNode("H",null,null);
		TNode tree6=new TNode("G",null,null);
		TNode tree5=new TNode("F",null,null);
		TNode tree4=new TNode("E",null,null);
		TNode tree3=new TNode("D",tree7,tree8);
		TNode tree2=new TNode("C",tree5,tree6);
		TNode tree1=new TNode("B",tree3,tree4);
		TNode tree=new TNode("A",tree1,tree2);
		ArrayList<TNode> tArr=new ArrayList<TNode>();
		Scanner out = new Scanner(System.in);
		int numInt=out.nextInt();
		tArr=TreeLevel(tree,numInt);
		for(int i=0;i<tArr.size();i++){
			System.out.print(tArr.get(i).value);
		}
	}
	static ArrayList<TNode> TreeLevel(TNode tree, int n){
		ArrayList<TNode> nodeArr=new ArrayList<TNode>();
		ArrayList<TNode> nodeArrS=new ArrayList<TNode>();
		//当n=1，直接返回根节点
		nodeArr.add(tree);
		/*
		 * 从树根节点循环去节点的左右子节点
		 */
		for(int i=1;i<n;i++){
			//清除nodeArrS里面原有的节点
			nodeArrS.clear();
			nodeArrS.addAll(nodeArr);
			nodeArr.clear();
			for(int j=0;j<nodeArrS.size();j++){
				if(nodeArrS.get(j).left!=null)
				nodeArr.add(nodeArrS.get(j).left);
				if(nodeArrS.get(j).right!=null)
				nodeArr.add(nodeArrS.get(j).right);
			}
		}
		return nodeArr;
	}
}
class TNode  {
        String  value;
        TNode   left,right;
        TNode(){  }
        TNode(String value,TNode left,TNode right){
        	this.value=value;
        	this.left=left;
        	this.right=right;
        }
    }