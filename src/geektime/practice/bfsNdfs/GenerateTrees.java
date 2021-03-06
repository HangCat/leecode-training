package geektime.practice.bfsNdfs;

import geektime.practice.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description
 * @create 2020-07-21
 */
public class GenerateTrees {
	public List<TreeNode> generateTrees(int n) {
		if (n == 0) {
			return new LinkedList<>();
		}
		return generateTrees(1, n);
	}

	public List<TreeNode> generateTrees(int start, int end) {
		List<TreeNode> allTrees = new LinkedList<>();
		if (start > end) {
			allTrees.add(null);
			return allTrees;
		}
		// 枚举可行根节点
		for (int i = start; i <= end; i++) {
			// 获得所有可行的左子树集合
			List<TreeNode> leftTrees = generateTrees(start, i - 1);
			// 获得所有可行的右子树集合
			List<TreeNode> rightTrees = generateTrees(i + 1, end);
			// 从左子树集合中选出一棵左子树，
			// 从右子树集合中选出一棵右子树，
			// 拼接到根节点上
			for (TreeNode left : leftTrees) {
				for (TreeNode right : rightTrees) {
					TreeNode currTree = new TreeNode(i, left, right);
					allTrees.add(currTree);
				}
			}
		}
		return allTrees;
	}

	public List<TreeNode> generateTreesDP(int n) {
		//通过former和next进行动态规划
		List<TreeNode> former = new ArrayList<>();
		if (n == 0) return former;
		former.add(new TreeNode(1));
		if (n == 1) return former;
		for (int i = 2; i <= n; i++) {
			List<TreeNode> next = new ArrayList<>();
			for (TreeNode node : former) {
				//第一种情况，新值作为根节点，former中的node为其左孩子
				next.add(new TreeNode(i, cloneTree(node), null));
				//下面开始枚举其他情况，也就是former中的每个node除了根节点，将其余右孩子全部替换成新值一次，并将原
				//右孩子作为新值的左孩子
				//因为原node需要使用很多次，所以不能直接操作，需要用cur来代替操作
				TreeNode cur = node;
				//k为本次替换需要替换的是node的根节点往右的第(k+1)个右孩子
				int k = 0;
				while (cur != null) {
					//不能直接操作原node，只能复制一份进行替换操作
					TreeNode clone = cloneTree(node);
					//因为要将替换后的clone加入到next中，所以也不能对clone进行直接的迭代，需要临时树temp来操作clone
					TreeNode temp = clone;
					int save = k;
					//循环k次，找到需要更改其右孩子的那个节点
					while (temp != null && save > 0) {
						temp = temp.right;
						save--;
					}
					//将其右孩子替换成新值，并将原右孩子作为新节点的左孩子
					temp.right = new TreeNode(i, cloneTree(cur.right), null);
					next.add(clone);
					//寻找下一个待替换节点(实际上是替换它的右孩子)
					cur = cur.right;
					//更新循环次数
					k++;
				}
			}
			//让动态规划动起来
			former = next;
		}
		return former;
	}

	public TreeNode cloneTree(TreeNode node) {
		if (node == null) return null;
		TreeNode clone = new TreeNode(node.val);
		clone.left = cloneTree(node.left);
		clone.right = cloneTree(node.right);
		return clone;
	}
}
