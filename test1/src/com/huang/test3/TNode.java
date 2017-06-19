package com.huang.test3;

/**
 * 创建树
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>succez</p>
 * @author huangxg
 * @createdate 2017年6月19日
 */
public class TNode {
	String value;

	TNode left, right;

	TNode() {
	}

	TNode(String value, TNode left, TNode right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
}
