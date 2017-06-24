package com.huang.test3;

/**
 * 
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huangxg
 * @createdate 2017年6月19日
 */
public class TestThree {

	/**
	 * 根据输入层次按顺序返回二叉树该层树节点
	 * <pre>
	 * 树：
	 * treeLvl		tree
	 * 1			A
	 * 2		B		C
	 * 3	D	E		F	G
	 * treeLevel(A,1)="A"
	 * treeLevel(A,3)="D-E-F-G"
	 * treeLevel(A,-1)=""
	 * treeLevel(null,1)=""
	 * </pre>
	 * @param {TNode} tree 遍历树
	 * @param {int} n 树的第n层,n>0的整数
	 * @return 返回处理后的字符串，如"A","C-D"
	 */
	public String treeLevel(TNode tree, int treeLvl) {
		//空树和1层以下的都不存在节点
		if (tree == null || treeLvl < 1) {
			return "";
		}
		StringBuilder resultStr = new StringBuilder();
		treeString(tree, treeLvl, resultStr);
		return resultStr.substring(0, resultStr.length() > 0 ? resultStr.length() - 1 : 0);
	}

	/**
	 *
	 * @param {TNode} tree 
	 * @param {int} treeLvl 所要输出节点的树层数
	 * @return 
	 */
	private void treeString(TNode tree, int treeLvl, StringBuilder resultStr) {
		if (treeLvl > 1 && tree != null) {
			treeLvl--;
			treeString(tree.left, treeLvl, resultStr);
			treeString(tree.right, treeLvl, resultStr);
		}
		else {
			resultStr.append(tree == null ? "" : tree.value + '-');
		}
	}
}
