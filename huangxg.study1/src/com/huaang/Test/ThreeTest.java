package com.huaang.Test;

import static org.junit.Assert.assertEquals;

import java.util.Objects;
import java.util.Stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.huang.test3.TNode;
import com.huang.test3.TestThree;

public class ThreeTest {
	private String[] nodesValueArr = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	TestThree tThree = new TestThree();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private TNode createTree(String treeString) {
		if (treeString == null || treeString == "") {
			return null;
		}
		String[] delCharArr = treeString.split("");
		int length = treeString.length();
		int arrLength = 0;
		//通过字符数求节点数
		for (int j = 0; j < length; j++) {
			char delChar = treeString.charAt(j);
			if ((delChar >= 65 && delChar <= 90) || delChar == 32) {
				arrLength++;
			}
		}
		TNode[] nodesArr = new TNode[arrLength];
		for (int i = 0; i < arrLength; i++) {
			nodesArr[i] = new TNode(nodesValueArr[i], null, null);
		}
		Stack<String> charArr = new Stack<String>();
		for (int i = length - 1; i > 0; i--) {
			if (!Objects.equals(delCharArr[i], "(")) {
				charArr.push(delCharArr[i]);
			}
			else {
				TNode node = getNode(nodesArr, delCharArr[i - 1]);
				node.setLeft(getNode(nodesArr, charArr.pop()));
				charArr.pop();
				node.setRight(getNode(nodesArr, charArr.pop()));
				charArr.pop();
			}
		}
		return nodesArr[0];
	}

	TNode getNode(TNode[] nodesArr, String val) {
		for (int i = 0, length = nodesArr.length; i < length; i++) {
			if (Objects.equals(nodesArr[i].getValue(), val)) {
				return nodesArr[i];
			}
		}
		return null;
	}

	//创建节点层连接字符串
	private String createTreeString(String treeString, int lvl) {
		if (lvl <= 0 || treeString == null || treeString == "") {
			return "";
		}
		int len = getStringArrLen(treeString);
		if (lvl > len) {
			return "";
		}
		StringBuilder[] lvlTreeArrString = new StringBuilder[len];
		for (int i = 0; i < len; i++) {
			lvlTreeArrString[i] = new StringBuilder();
		}
		int lvlIndex = 0;
		for (int i = 0, length = treeString.length(); i < length; i++) {
			char charFlag = treeString.charAt(i);
			switch (charFlag) {
				case '(':
					lvlIndex++;
					break;
				case ')':
					lvlIndex--;
					break;
				case ',':
					break;
				default:
					if (!(charFlag == ' ')) {
						lvlTreeArrString[lvlIndex].append(charFlag).append('-');
					}
					break;
			}
		}
		StringBuilder retString = lvlTreeArrString[lvl - 1];
		return retString.substring(0, retString.length() - 1);
	}

	//获取树层数
	private int getStringArrLen(String treeString) {
		if (treeString == null || treeString == "") {
			return 0;
		}
		int len = 0;
		for (int i = 0, length = treeString.length(); i < length; i++) {
			if (treeString.charAt(i)=='(') {
				len++;
			}
		}
		return (len / 2) + 1;
	}

	@Test
	public void TreeTest() {
		String[] treeStr = new String[] { "A(B(D(H, ),E(J, )),C(F( ,M),G( ,O)))",
				"A(B(D(H,I),E(J,K)),C(F(L,M),G(N,O)))" };
		for (int i = 0, length = treeStr.length; i < length; i++) {
			TNode tree = createTree(treeStr[i]);
			assertEquals(createTreeString(treeStr[i], -1), tThree.treeLevel(tree, -1));
			assertEquals(createTreeString(treeStr[i], 1), tThree.treeLevel(tree, 1));
			assertEquals(createTreeString(treeStr[i], 2), tThree.treeLevel(tree, 2));
			assertEquals(createTreeString(treeStr[i], 3), tThree.treeLevel(tree, 3));
			assertEquals(createTreeString(treeStr[i], 4), tThree.treeLevel(tree, 4));
			assertEquals(createTreeString(treeStr[i], 5), tThree.treeLevel(tree, 5));
		}
	}

	@Test
	public void nullTree() {
		assertEquals("", tThree.treeLevel(null, 1));
	}

}
