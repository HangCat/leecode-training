package geektime.practice.linkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description
 * @create 2020-07-03
 */
public class LRUCache {

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(10);
		int i = cache.get(2);
		System.out.println("i = " + i);
		/*cache.put(1, 1);
		cache.put(2, 2);
		cache.put(3, 3);
		cache.put(4, 4);
		cache.put(2, 5);*/

		String aa = "";
		Node node = cache.head.next;
		while (node != cache.tail) {
			aa += " " + node.key + ":";
			aa += " " + node.value + " ";
			node = node.next;
		}
		System.out.println("aa = " + aa);
	}

	Map<Integer, Node> map = new HashMap<>();
	Node head, tail;
	int capacity;
	int size;

	public LRUCache(int capacity) {
		size = 0;
		this.capacity = capacity;
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.pre = head;
	}

	public int get(int key) {
		final Node node = map.get(key);
		if (node == null) return -1;
		moveToHead(node);
		return node.value;
	}

	public void put(int key, int value) {
		final Node oldNode = map.get(key);
		if (oldNode == null) {
			final Node node = new Node(key, value);
			if (size == capacity) {
				Node tailNode = tail.pre;
				map.remove(tailNode.key);
				map.put(key, node);
				addToHead(node);
				removeTail();
			} else {
				addToHead(node);
				map.put(key, node);
				size++;
			}
		} else {
			oldNode.value = value;
			moveToHead(oldNode);
		}
	}

	private void removeNode(Node node) {
		node.pre.next = node.next;
		node.next.pre = node.pre;
	}

	private void moveToHead(Node node) {
		removeNode(node);
		addToHead(node);
	}

	private void addToHead(Node node) {
		node.pre = head;
		node.next = head.next;
		head.next.pre = node;
		head.next = node;
	}

	private void removeTail() {
		removeNode(tail.pre);
	}

	class Node {

		int key;
		int value;
		Node next;
		Node pre;

		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}

		public Node() {
		}

	}
}
