package geektime.practice.doubleindex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static primary.CommonUtil.genRandomIntArr;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description 给你一个包含 n 个整数的数组 nums
 * 判断 nums 中是否存在三个元素 a，b，c
 * 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * @create 2020-06-28
 */
public class ThreeSum {

	public static void main(String[] args) {
		final int[] arr = genRandomIntArr(10);
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i] - 5;
		}

		System.out.println(threeSum(arr));
	}

	static List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> res = new LinkedList<>();
		for (int i = 0; i < nums.length - 2; i++) {
			if (i == 0 || nums[i] != nums[i - 1]) {
				int lo = i + 1, hi = nums.length - 1, sum = -nums[i];
				while (lo < hi) {
					if (nums[lo] + nums[hi] == sum) {
						res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
						while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
						while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
						lo++;
						hi--;
					} else if (nums[lo] + nums[hi] < sum) lo++;
					else hi--;
				}
			}
		}
		return res;
	}


	static List<List<Integer>> t1(int[] nums) {
		Arrays.sort(nums);
		final List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < nums.length - 1; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) continue;
			int sum = -i, left = i + 1, right = nums.length - 1;
			while (left < right) {
				if (nums[left] + nums[right] > sum) right--;
				else if (nums[left] + nums[right] < sum) left++;
				else {
					list.add(Arrays.asList(nums[i], nums[left], nums[right]));
					while (left < right && nums[left] == nums[left + 1])
						left++;
					while (left < right && nums[right] == nums[right - 1])
						right--;
					left++;
					right--;
				}
			}
		}
		return list;
	}

}
