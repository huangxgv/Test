package com.huang.test3;

import static org.junit.Assert.assertArrayEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ThreeTest {
	testthree tThree = null;
	@Before
	public void setUp() throws Exception {
		System.out.println("测试开始！");
		tThree = new testthree();
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
		assertArrayEquals(new String[] { "A" }, tThree.TreeLevel(tree, 1));
		assertArrayEquals(new String[] { "B", "C" }, tThree.TreeLevel(tree, 2));
		assertArrayEquals(new String[] { "D", "E", "F", "G" }, tThree.TreeLevel(tree, 3));
		assertArrayEquals(new String[] { "H", "I" }, tThree.TreeLevel(tree, 4));
		assertArrayEquals(new String[] {}, tThree.TreeLevel(tree, 5));
	}

}
