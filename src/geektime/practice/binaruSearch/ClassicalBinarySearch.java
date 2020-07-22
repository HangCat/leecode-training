package geektime.practice.binaruSearch;

import static primary.CommonUtil.genSortedIntArr;
import static primary.CommonUtil.printIntArr;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description
 * @create 2020-07-21
 */
public class ClassicalBinarySearch {

	public static void main(String[] args) {
		final int[] ints = genSortedIntArr(10);
		printIntArr(ints);
		int target = 5;
		System.out.println(search(ints, target));
		System.out.println(search(ints, 0, ints.length - 1, target));
	}

	static int search(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;

		while (left <= right) {
			int mid = (left + right) >>> 1;
			if (nums[mid] > target) {
				right = mid - 1;
			} else if (nums[mid] < target) {
				left = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	static int search(int[] nums, int left, int right, int target) {
		if (left > right) {
			return -1;
		}
		int mid = (left + right) >>> 1;
		if (nums[mid] > target)
			return search(nums, left, mid - 1, target);
		else if (nums[mid] < target)
			return search(nums, mid + 1, right, target);
		else
			return mid;
	}
}
