package com.huaang.Test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.huang.test3.TNode;
import com.huang.test3.TestThree;

public class ThreeTest {
	TestThree tThree = new TestThree();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void completeTreeTreeTest() {
		TNode tree8 = new TNode("I", null, null);
		TNode tree7 = new TNode("H", null, null);
		TNode tree6 = new TNode("G", null, null);
		TNode tree5 = new TNode("F", null, null);
		TNode tree4 = new TNode("E", null, null);
		TNode tree3 = new TNode("D", tree7, tree8);
		TNode tree2 = new TNode("C", tree5, tree6);
		TNode tree1 = new TNode("B", tree3, tree4);
		TNode CompleteTree = new TNode("A", tree1, tree2);
		assertEquals("Lvl1", "A", tThree.treeLevel(CompleteTree, 1));
		assertEquals("Lvl2", "B-C", tThree.treeLevel(CompleteTree, 2));
		assertEquals("Lvl3", "D-E-F-G", tThree.treeLevel(CompleteTree, 3));
		assertEquals("Lvl4", "H-I", tThree.treeLevel(CompleteTree, 4));
		assertEquals("超出树的层数", "", tThree.treeLevel(CompleteTree, 5));
	}

	@Test
	public void notCompleteTree() {
		TNode tree8 = new TNode("I", null, null);
		TNode tree7 = new TNode("H", null, null);
		TNode tree6 = new TNode("G", null, null);
		TNode tree3 = new TNode("D", tree7, tree8);
		TNode tree2 = new TNode("C", null, tree6);
		TNode tree1 = new TNode("B", tree3, null);
		TNode CompleteTree = new TNode("A", tree1, tree2);
		assertEquals("Lvl1", "A", tThree.treeLevel(CompleteTree, 1));
		assertEquals("Lvl2", "B-C", tThree.treeLevel(CompleteTree, 2));
		assertEquals("Lvl3", "D-G", tThree.treeLevel(CompleteTree, 3));
		assertEquals("Lvl4", "H-I", tThree.treeLevel(CompleteTree, 4));
		assertEquals("超出树的层数", "", tThree.treeLevel(CompleteTree, 5));
	}

	@Test
	public void nullTree() {
		assertEquals("传入null树", "", tThree.treeLevel(null, 1));
	}

}
