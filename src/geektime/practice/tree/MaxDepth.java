package geektime.practice.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description
 * @create 2020-07-11
 */
public class MaxDepth {

	public static void main(String[] args) {
		final TreeNode node = TreeNode.stringToTreeNode("[3,9,20,null,null,15,7]");
		System.out.println(maxDepthRecursion(node));
		System.out.println(maxDepthBFS(node));
		System.out.println(maxDepthDFS(node));
	}

	static int maxDepthRecursion(TreeNode root) {
		if (root == null) return 0;
		int maxLeft = maxDepthRecursion(root.left);
		int maxRight = maxDepthRecursion(root.right);
		return Math.max(maxLeft, maxRight) + 1;
	}

	static int maxDepthBFS(TreeNode root) {
		if (root == null) return 0;
		final Deque<TreeNode> queue = new LinkedList<>();
		int maxDepth = 0;
		queue.add(root);
		while (!queue.isEmpty()) {
			for (int i = queue.size(); i > 0; i--) {
				final TreeNode node = queue.poll();
				if (node.left != null) queue.add(node.left);
				if (node.right != null) queue.add(node.right);
			}
			maxDepth++;
		}
		return maxDepth;
	}

	static int maxDepthDFS(TreeNode root) {
		int res = 0;
		if (root == null) return res;
		final Deque<TMP> stack = new LinkedList<>();
		TMP curr = new TMP(root, 1);
		while ((!stack.isEmpty()) || (curr != null)) {

//			if (curr.)
			if (curr.node != null) {
				stack.offerFirst(curr);
				curr = new TMP(curr.node.left, curr.depth + 1);
				/*if (curr.node.left == null) {
					curr = null;
				} else {
					curr = new TMP(curr.node.left, curr.depth + 1);
				}*/
			} else{
				curr = stack.pollFirst();
				if (curr.node == null || curr.node.right == null) {
					curr = new TMP(null, curr.depth);
				} else {
					curr = new TMP(curr.node.right, curr.depth + 1);
				}
				/*if (curr.node.right == null) {
					curr = null;
				} else {
					curr = new TMP(curr.node.right, curr.depth + 1);
				}*/
			}
			res = Math.max(res, curr.depth);
		}
		return res;
	}

	private static class TMP {
		TreeNode node;
		int depth;

		TMP(TreeNode node, int depth) {
			this.node = node;
			this.depth = depth;
		}
	}


}
