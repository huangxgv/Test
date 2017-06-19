package com.huang.test3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 根据输入层次按顺序返回二叉树该层树节点
 * 
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huangxg
 * @createdate 2017年6月19日
 */
public class testthree {

	public static void main(String[] args) {
		//测试树
		TNode tree8 = new TNode("I", null, null);
		TNode tree7 = new TNode("H", null, null);
		TNode tree6 = new TNode("G", null, null);
		TNode tree5 = new TNode("F", null, null);
		TNode tree4 = new TNode("E", null, null);
		TNode tree3 = new TNode("D", tree7, tree8);
		TNode tree2 = new TNode("C", tree5, tree6);
		TNode tree1 = new TNode("B", tree3, tree4);
		TNode tree = new TNode("A", tree1, tree2);
		ArrayList<TNode> tArr = new ArrayList<TNode>();
		Scanner out = new Scanner(System.in);
		int numInt = out.nextInt();
		tArr = TreeLevel(tree, numInt);
		for (int i = 0; i < tArr.size(); i++) {
			if (i < tArr.size() - 1) {
				System.out.print(tArr.get(i).value + "-");
			}
			else {
				System.out.print(tArr.get(i).value);
			}

		}
	}

	/**
	 * 
	 * @param {TNode} tree 遍历树
	 * @param {int} n 树的第n层
	 * @return
	 */
	static ArrayList<TNode> TreeLevel(TNode tree, int n) {
		ArrayList<TNode> nodeArr = new ArrayList<TNode>();
		ArrayList<TNode> nodeArrS = new ArrayList<TNode>();
		/**
		 * 当n=1，直接返回根节点
		 */
		nodeArr.add(tree);
		for (int i = 1; i < n; i++) {
			//清除nodeArrS里面原有的节点
			nodeArrS.clear();
			nodeArrS.addAll(nodeArr);
			nodeArr.clear();
			/**
			 * 从根向下查找left与right子节点，得到left与right子节点再进行下一层节点的查找
			 */
			for (int j = 0; j < nodeArrS.size(); j++) {
				if (nodeArrS.get(j).left != null)
					nodeArr.add(nodeArrS.get(j).left);
				if (nodeArrS.get(j).right != null)
					nodeArr.add(nodeArrS.get(j).right);
			}
		}
		return nodeArr;
	}
}
