package geektime.practice.singleindex;

import static primary.CommonUtil.*;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * @create 2020-06-28
 */
public class MoveZeroes {

	public static void main(String[] args) {
		final int[] ints = genRandomIntArr(10);
		printIntArr(ints);
		moveZeroes(ints);
		printIntArr(ints);
	}

	static void moveZeroes(int[] nums) {
		if (nums == null) return;
		int j = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				swap(nums, i, j);
				j++;
			}
		}
	}
}
