package geektime.practice.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description
 * @create 2020-07-12
 */
public class MinDepth {

	static int minDepthRecursion(TreeNode root) {
		if (root == null) return 0;
		if (root.left == null && root.right == null) return 1;

		int min = Integer.MAX_VALUE;

		if (root.left != null)
			min = Math.min(minDepthRecursion(root.left), min);
		if (root.right != null)
			min = Math.min(minDepthRecursion(root.right), min);
		return min + 1;
	}


	public int minDepthBFS(TreeNode root) {
		final Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
		if (root == null) {
			return 0;
		} else {
			queue.add(new Pair<>(root, 1));
		}

		int current_depth = 0;
		while (!queue.isEmpty()) {
			Pair<TreeNode, Integer> current = queue.poll();
			root = current.getKey();
			current_depth = current.getValue();
			if ((root.left == null) && (root.right == null)) {
				break;
			}
			if (root.left != null) {
				queue.add(new Pair<>(root.left, current_depth + 1));
			}
			if (root.right != null) {
				queue.add(new Pair<>(root.right, current_depth + 1));
			}
		}
		return current_depth;
	}

	public int minDepthDFS(TreeNode root) {
		final Deque<Pair<TreeNode, Integer>> stack = new LinkedList<>();
		if (root == null) {
			return 0;
		} else {
			stack.add(new Pair<>(root, 1));
		}

		int min_depth = Integer.MAX_VALUE;
		while (!stack.isEmpty()) {
			Pair<TreeNode, Integer> current = stack.pollLast();
			root = current.getKey();
			int current_depth = current.getValue();
			if ((root.left == null) && (root.right == null)) {
				min_depth = Math.min(min_depth, current_depth);
			}
			if (root.left != null) {
				stack.add(new Pair<>(root.left, current_depth + 1));
			}
			if (root.right != null) {
				stack.add(new Pair<>(root.right, current_depth + 1));
			}
		}
		return min_depth;
	}

}
