package com.huang.test3;

import java.util.ArrayList;

/**
 * 根据输入层次按顺序返回二叉树该层树节点
 * 
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huangxg
 * @createdate 2017年6月19日
 */
public class testthree {

	/**
	 * 
	 * @param {TNode} tree 遍历树
	 * @param {int} n 树的第n层,n>0的整数
	 * @return
	 */
	String[] TreeLevel(TNode tree, int n) {
		ArrayList<TNode> nodeArr = new ArrayList<TNode>();
		ArrayList<TNode> nodeArrS = new ArrayList<TNode>();
		int strIndex = 0;
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
		String[] resultStringArr = new String[nodeArr.size()];
		for (TNode tNode : nodeArr) {
			resultStringArr[strIndex] = tNode.value;
			strIndex++;
		}
		return resultStringArr;
	}
}
