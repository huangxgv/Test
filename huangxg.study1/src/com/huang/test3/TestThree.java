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
		//空树和1层以下的都不存在节点
		if (tree == null || treeLvl < 1) {
			return "";
		}
		StringBuilder resultStr = new StringBuilder();
		treeString(tree, treeLvl, resultStr);
		return "".equals(resultStr) ? "" : resultStr.substring(0, resultStr.length() - 1);
	}

	/**
	 * 
	 * @param tree
	 * @param treeLvl
	 * @return 返回节点值+"-"拼接，如"A-","C-D-",tree=null时返回""
	 */
	void treeString(TNode tree, int treeLvl, StringBuilder resultStr) {
		if (treeLvl > 1 && tree != null) {
			treeString(tree.left, --treeLvl, resultStr);
			treeString(tree.right, --treeLvl, resultStr);
		}
		else {
			resultStr.append(tree == null ? "" : tree.value+'-');
		}
	}
}
