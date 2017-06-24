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

	public TNode getLeft() {
		return left;
	}

	public void setLeft(TNode left) {
		this.left = left;
	}

	public TNode getRight() {
		return right;
	}

	public void setRight(TNode right) {
		this.right = right;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public TNode() {
	}

	public TNode(String value, TNode left, TNode right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
}
