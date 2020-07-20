package geektime.practice.tree;

import java.util.*;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description LevelOrder 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 * @create 2020-07-05
 */
public class LevelOrder2XTree {

	static List<List<Integer>> levelOrder(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		List<List<Integer>> res = new ArrayList<>();
		if(root != null) queue.add(root);
		while(!queue.isEmpty()) {
			List<Integer> tmp = new ArrayList<>();
			for(int i = queue.size(); i > 0; i--) {
				TreeNode node = queue.poll();
				tmp.add(node.val);
				if(node.left != null) queue.add(node.left);
				if(node.right != null) queue.add(node.right);
			}
			res.add(tmp);
		}
		return res;
	}

}
