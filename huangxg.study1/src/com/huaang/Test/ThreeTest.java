package com.huaang.Test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.huang.test3.TNode;
import com.huang.test3.TestThree;

public class ThreeTest {
	TestThree tThree = null;
	@Before
	public void setUp() throws Exception {
		System.out.println("测试开始！");
		tThree = new TestThree();
		System.out.println("testthree对象已经被实例化！");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("测试结束！");
		tThree = null;
		System.out.println("testthree对象已经被销毁！");
	}

	@Test
	public void test() {
		TNode tree8 = new TNode("I", null, null);
		TNode tree7 = new TNode("H", null, null);
		TNode tree6 = new TNode("G", null, null);
		TNode tree5 = new TNode("F", null, null);
		TNode tree4 = new TNode("E", null, null);
		TNode tree3 = new TNode("D", tree7, tree8);
		TNode tree2 = new TNode("C", tree5, tree6);
		TNode tree1 = new TNode("B", tree3, tree4);
		TNode tree = new TNode("A", tree1, tree2);
		assertArrayEquals(new String[] { "A" }, tThree.treeLevel(tree, 1));
		assertArrayEquals(new String[] { "B", "C" }, tThree.treeLevel(tree, 2));
		assertArrayEquals(new String[] { "D", "E", "F", "G" }, tThree.treeLevel(tree, 3));
		assertArrayEquals(new String[] { "H", "I" }, tThree.treeLevel(tree, 4));
		assertArrayEquals(new String[] {}, tThree.treeLevel(tree, 5));
	}

}
