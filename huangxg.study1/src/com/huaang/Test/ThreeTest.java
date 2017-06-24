package com.huaang.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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
		System.out.println(nodes);
		//树的层数
		int lvlTreeNumber = 1;
		//每一层包含的节点数
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

	//获取层节点字符串
	String treeString(TNode tree, int n) {
		ArrayList<TNode> nodeArr = new ArrayList<TNode>();
		ArrayList<TNode> nodeArrS = new ArrayList<TNode>();
		nodeArr.add(tree);
		for (int i = 1; i < n; i++) {
			nodeArrS.clear();
			nodeArrS.addAll(nodeArr);
			nodeArr.clear();

			for (int j = 0; j < nodeArrS.size(); j++) {
				if (nodeArrS.get(j).getLeft() != null)
					nodeArr.add(nodeArrS.get(j).getLeft());
				if (nodeArrS.get(j).getRight() != null)
					nodeArr.add(nodeArrS.get(j).getRight());
			}
		}
		String resultStringArr = new String();
		for (TNode tNode : nodeArr) {
			resultStringArr += tNode.getValue()==""?"": tNode.getValue()+ '-';
		}
		return resultStringArr.length() > 0 ? resultStringArr.substring(0, resultStringArr.length() - 1) : "";
	}

	@Test
	public void TreeTreeTest() {
		TNode tree = createTree();
		assertEquals(treeString(tree, 1), tThree.treeLevel(tree, 1));
		assertEquals(treeString(tree, 2), tThree.treeLevel(tree, 2));
		assertEquals(treeString(tree, 3), tThree.treeLevel(tree, 3));
		assertEquals(treeString(tree, 4), tThree.treeLevel(tree, 4));
		assertEquals(treeString(tree, 5), tThree.treeLevel(tree, 5));
	}

	@Test
	public void nullTree() {
		assertEquals("传入null树", "", tThree.treeLevel(null, 1));
	}

}
