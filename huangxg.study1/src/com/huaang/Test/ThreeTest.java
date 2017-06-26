package com.huaang.Test;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.huang.test3.TNode;
import com.huang.test3.TestThree;

public class ThreeTest {
	private static final String[] nodesValueArr = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	StringBuilder[] sBuilders = new StringBuilder[26];

	TestThree tThree = new TestThree();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//生成随机树
	private TNode createTree() {
		int nodes = new Random().nextInt(25) + 1;
		TNode[] tNodeArr = new TNode[nodes];
		//当前处理节点的索引值
		int doNodeIndex = 0;
		//当前分配节点的索引值
		int giveNodeIndex = 1;
		for (int i = 0; i < nodes; i++) {
			tNodeArr[i] = new TNode(nodesValueArr[i], null, null);
		}
		while (giveNodeIndex < nodes) {
			if (new Random().nextBoolean()) {
				tNodeArr[doNodeIndex].setLeft(tNodeArr[giveNodeIndex++]);
			}
			if (giveNodeIndex < nodes && (tNodeArr[doNodeIndex].getLeft() == null || new Random().nextBoolean())) {
				tNodeArr[doNodeIndex++].setRight(tNodeArr[giveNodeIndex++]);
			}
		}
		return tNodeArr[0];
	}

	String treeString(TNode tree, int n) {
		if (n <= 0 || tree == null) {
			return "";
		}
		StringBuffer str = new StringBuffer();
		treeQuery(tree, n, str);
		return str.toString();
	}

	public void treeQuery(TNode tree, int n, StringBuffer str) {
		if (n <= 0 || tree == null) {
			return;
		}
		if (n == 1) {
			if (str.length() > 0) {
				str.append('-');
			}
			str.append(tree.getValue());
		}
		treeQuery(tree.getLeft(), n - 1, str);
		treeQuery(tree.getRight(), n - 1, str);
	}

	@Test
	public void TreeTreeTest() {
		TNode tree = createTree();
		assertEquals(treeString(tree, -1), tThree.treeLevel(tree, -1));
		assertEquals(treeString(tree, 1), tThree.treeLevel(tree, 1));
		assertEquals(treeString(tree, 2), tThree.treeLevel(tree, 2));
		assertEquals(treeString(tree, 3), tThree.treeLevel(tree, 3));
		assertEquals(treeString(tree, 4), tThree.treeLevel(tree, 4));
		assertEquals(treeString(tree, 5), tThree.treeLevel(tree, 5));
	}

	@Test
	public void nullTree() {
		assertEquals("", tThree.treeLevel(null, 1));
	}

}
