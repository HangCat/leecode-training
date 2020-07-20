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

	static int maxDepthRecursion(TreeNode root) {
		if (root == null) return 0;
		int maxLeft = maxDepthRecursion(root.left);
		int maxRight = maxDepthRecursion(root.right);
		return Math.max(maxLeft, maxRight) + 1;
	}

	static int maxDepthIterator(TreeNode root) {
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


}
