package geektime.practice.backtrace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * @create 2020-07-21
 */
public class Permute {

	public void backtrack(int depth, int first,
	                      ArrayList<Integer> output,
	                      List<List<Integer>> res) {
		if (first == depth) { // 所有数都填完了
			res.add(new ArrayList<>(output));
		}
		for (int i = first; i < depth; i++) {
			// 动态维护数组
			Collections.swap(output, first, i);
			// 继续递归填下一个数
			backtrack(depth, first + 1, output, res);
			// 撤销操作
			Collections.swap(output, first, i);
		}
	}

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new LinkedList<>();
		ArrayList<Integer> output = new ArrayList<>();
		for (int num : nums) {
			output.add(num);
		}
		int n = nums.length;
		backtrack(n, 0, output, res);
		return res;
	}

	public List<List<Integer>> p(int[] nums) {
		final List<List<Integer>> res = new ArrayList<>();
		final List<Integer> output = new ArrayList<>();

		for (int num : nums) {
			output.add(num);
		}
		backt(nums.length, 0, output, res);
		return res;
	}

	private void backt(int depth, int first,
	                   List<Integer> output, List<List<Integer>> res) {
		if (depth == first) {
			res.add(new ArrayList<>(output));
			return;
		}
		for (int i = 0; i < depth; i++) {
			Collections.swap(output, first, i);
			backt(depth, first + 1, output, res);
			Collections.swap(output, first, i);
		}
	}

}
