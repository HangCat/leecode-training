package geektime.practice.doubleindex;

import static primary.CommonUtil.genRandomIntArr;
import static primary.CommonUtil.printIntArr;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * @create 2020-07-07
 */
public class NextPermutation {

	public static void main(String[] args) {
//		int[] arr = {1, 2, 4, 5, 3};
		int[] arr = genRandomIntArr(10);
		printIntArr(arr);
		nextPermutation(arr);
		printIntArr(arr);
	}

	/**
	 * we need two step to accomplish this task:
	 * one is finding the element position where we would work with
	 * the other is modifying elements
	 * find:
	 * 1. find the first element(position is i) which is less than i + 1
	 * looped from tail
	 * after this step, we can sure that i -> n-1 is ordered
	 * <p>
	 * 2. find the first one which is grater than ele(i)/arr[i] from tail
	 * after this step, we can sure that the arr[i] is second smallest
	 * modify:
	 * 1. swap the ele(i) and ele(second smallest position)
	 * 2. reverse the array from i+1 to n-1
	 * finished
	 */
	private static void nextPermutation(int[] nums) {
		int i = nums.length - 2;
		while (i >= 0 && nums[i + 1] <= nums[i]) i--;
		if (i >= 0) {
			int j = nums.length - 1;
			while (j >= i && nums[j] <= nums[i]) j--;
			swap(nums, i, j);
		}
		reverse(nums, i + 1);
	}

	private static void reverse(int[] nums, int start) {
		int i = start, j = nums.length - 1;
		while (i < j) {
			swap(nums, i, j);
			i++;
			j--;
		}
	}

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

}
