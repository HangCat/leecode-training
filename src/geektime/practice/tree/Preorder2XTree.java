package geektime.practice.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description inorder 二叉树
 * @create 2020-07-05
 */
public class Preorder2XTree {

	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		node1.right = node2;
		node2.left = node3;

		System.out.println("inorderTraversal = " + preorderByRecursion(node1));
		System.out.println("inorderByIterator = " + preorderByIterator(node1));

	}

	static List<Integer> res = new ArrayList<>();

	static List<Integer> preorderByRecursion(TreeNode root) {
		if (root == null) return res;
		res.add(root.val);
		preorderByRecursion(root.left);
		preorderByRecursion(root.right);
		return res;
	}

	static List<Integer> preorderByIterator(TreeNode root) {
		final List<Integer> res = new ArrayList<>();
		final Deque<TreeNode> stack = new LinkedList<>();
		TreeNode current = root;
		while (!stack.isEmpty() || current != null) {
			if (current != null) {
				stack.push(current);
				res.add(current.val);
				current = current.left;
			} else {
				current = stack.pop();
				current = current.right;
			}
		}
		return res;

	}
}
