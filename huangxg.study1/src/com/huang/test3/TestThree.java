package com.huang.test3;

/**
 * 根据输入层次按顺序返回二叉树该层树节点
 * 
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huangxg
 * @createdate 2017年6月19日
 */
public class TestThree {

	/**
	 * 
	 * @param {TNode} tree 遍历树
	 * @param {int} n 树的第n层,n>0的整数
	 * @return 返回处理后的字符串，如"A","C-D"
	 */
	public String treeLevel(TNode tree, int treeLvl) {
		String resultValue = treeString(tree, treeLvl);
		return "".equals(resultValue) ? "" : resultValue.substring(0, resultValue.length() - 1);
	}

	/**
	 * 
	 * @param tree
	 * @param treeLvl
	 * @return 返回节点值+"-"拼接，如"A-","C-D-"
	 */
	String treeString(TNode tree, int treeLvl) {
		if (treeLvl > 1 && tree != null) {
			int parentLvl = treeLvl - 1;
			return treeString(tree.left, parentLvl) + treeString(tree.right, parentLvl);
		}
		else {
			return tree == null ? "" : tree.value + '-';
		}
	}
}
