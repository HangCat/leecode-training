package primary.algorithm;

import static primary.CommonUtil.genSortedIntArr;
import static primary.CommonUtil.printIntArr;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description 删除重复项
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用O(1)额外空间的条件下完成
 * @create 2020-06-01
 */
public class DuplicatesRemove {

	/**
	 * 双指针删除重复
	 * 两个指针相同方向移动，分为快慢指针
	 * 两个元素相同的时候，慢指针暂停
	 * 保证了满指针所在位置以及之前的位置前的元素没有重复的
	 * 两个元素不同的时候，把块指针位置上的元素移动到慢指针的位置上来
	 */
	public static int removeDuplicatesWithDoubleIndex(int[] nums) {
		int pos = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[pos] != nums[i]) {
				pos++;
				if (i != pos) {
					nums[pos] = nums[i];
				}
			}
		}
		return pos + 1;
	}

	public static int removeDuplicates2(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		int temp = nums[0];
		int index = 0;
		int[] indexs = new int[nums.length];

		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == temp) {
				index++;
			} else {
				temp = nums[i];
			}
			indexs[i] = index;
		}

		for (int i = 1; i < nums.length; i++) {
			int pos = indexs[i];
			if (pos > 0) {
				temp = nums[i];
				nums[i - pos] = temp;
			}
		}

		return nums.length - indexs[nums.length - 1];
	}

	public static void main(String[] args) {
		final int[] intArr = genSortedIntArr(20);
		printIntArr(intArr);

		removeDuplicatesWithDoubleIndex(intArr);
		printIntArr(intArr);
	}


}
