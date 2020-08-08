package geektime.practice.highertree;

import java.util.ArrayList;
import java.util.List;

public class Trie11 {

	public static void main(String[] args) {
		Trie11 obj = new Trie11();
		obj.insert("app");
		obj.insert("word");
		obj.insert("apple");
		obj.insert("hello");
		System.out.println("obj.search(word) = " + obj.search("app"));
		System.out.println("obj.startsWith(prefix) = " + obj.startsWith("app"));
	}


	private final TrieNode root;

	public Trie11() {
		root = new TrieNode();
	}

	public void insert(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char currentChar = word.charAt(i);
			if (!node.containsKey(currentChar)) {
				node.put(currentChar, new TrieNode());
			}
			node = node.get(currentChar);
		}
		node.setEnd();
	}

	public boolean search(String word) {
		TrieNode node = searchPrefix(word);
		return node != null && node.isEnd();
	}

	private TrieNode searchPrefix(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char curLetter = word.charAt(i);
			if (node.containsKey(curLetter)) {
				node = node.get(curLetter);
			} else {
				return null;
			}
		}
		return node;
	}

	public boolean startsWith(String prefix) {
		TrieNode node = searchPrefix(prefix);
		return node != null;
	}

	class TrieNode1{
		
	}

	static class TrieNode {

		private final TrieNode[] links;

		private boolean isEnd;

		public TrieNode() {
			links = new TrieNode[26];
		}

		public boolean containsKey(char ch) {
			return links[ch - 'a'] != null;
		}

		public TrieNode get(char ch) {
			return links[ch - 'a'];
		}

		public void put(char ch, TrieNode node) {
			links[ch - 'a'] = node;
		}

		public void setEnd() {
			isEnd = true;
		}

		public boolean isEnd() {
			return isEnd;
		}

		public TrieNode[] getLinks() {
			return links;
		}
	}
}