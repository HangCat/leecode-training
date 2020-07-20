package geektime.practice.doubleindex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static primary.CommonUtil.genRandomIntArr;
import static primary.CommonUtil.printIntArr;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 注意： 答案中不可以包含重复的四元组。
 * @create 2020-06-28
 */
public class FourSum {

	public static void main(String[] args) {
		final int[] arr = genRandomIntArr(10);
		printIntArr(arr);
		System.out.println(fourSum(arr, 25));
	}

	static List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);
		List<List<Integer>> res = new LinkedList<>();
		for (int i = 0; i < nums.length - 3; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) continue;
			for (int j = i + 1; j < nums.length - 2; j++) {
				if (j > i + 1 && nums[j] == nums[j - 1]) continue;
				int sum = target - nums[i] - nums[j], lo = j + 1, hi = nums.length - 1;
				while (lo < hi) {
					if (nums[lo] + nums[hi] > sum) hi--;
					else if (nums[lo] + nums[hi] < sum) lo++;
					else {
						res.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
						while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
						while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
						lo++;
						hi--;
					}
				}
			}
		}
		return res;
	}


	static List<List<Integer>> t1(int[] nums) {
		Arrays.sort(nums);
		final List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < nums.length - 1; i++) {
			if (i > 0 && nums[i - 1] == nums[i]) continue;
			for (int j = i + 1; j < nums.length - 1; j++) {
				if (j > i + 1 && nums[j - 1] == nums[j]) continue;
				int lo = j + 1, hi = nums.length - 1;
				int sum = nums[i] + nums[j] + nums[lo] + nums[hi];
				while (lo < hi) {
					if (sum > 0) hi--;
					else if (sum < 0) lo++;
					else {
						res.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
						while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
						while (lo < hi && nums[hi] == nums[hi + 1]) hi--;
						hi--;
						lo++;
					}
				}
			}
		}
		return res;
	}

}
