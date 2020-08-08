package geektime.practice.recursion;

import static sort.CommonUtil.printIntArr;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description
 * @create 2020-07-30
 */
public class GetLeastNumbers {

	public static void main(String[] args) {
		int[] arr = {0, 0, 1, 2, 4, 2, 2, 3, 1, 4};
//		int[] arr = {3, 2, 1};
		int k = 8;
		final int[] ints = getLeastNumbers(arr, k);
		printIntArr(ints);
	}

	static int[] getLeastNumbers(int[] arr, int k) {
		/*int last_pos = arr.length - 1;
		int last_parent = last_pos / 2;
		for (int i = last_parent; i >= 0; i--) {
			heapify(arr, i, arr.length);
		}

		int[] res = new int[k];
		int loop_limit = last_pos - k;
		int index = 0;

		for (int i = last_pos; i > loop_limit; i--) {
			printIntArr(arr);
			res[index++] = arr[0];
			swap(arr, 0, i);
			heapify(arr, 0, i);
		}
		return res;*/
		final int size = arr.length;
		final int last_pos = size - 1;
		final int last_parent = last_pos / 2;

		for (int i = last_parent; i >= 0; i--) {
			heapify(arr, i, size);
		}

		final int loop_time = last_pos - k;
		final int[] res = new int[k];
		int idx = 0;
		for (int i = last_pos; i >= loop_time; i--) {
			res[idx++] = arr[0];
			swap(arr, 0, i);
			heapify(arr, 0, i);
		}
		return res;
	}

	static void heapify(int[] nums, int idx, int size) {
		if (idx >= size) return;
		final int left_idx = 2 * idx + 1;
		final int right_idx = 2 * idx + 2;
		int min = idx;
		if (left_idx < size && nums[left_idx] < nums[idx])
			min = left_idx;
		if (right_idx < size && nums[right_idx] < nums[idx])
			min = right_idx;
		if (min != idx) {
			swap(nums, idx, min);
			heapify(nums, min, size);
		}
	}

	static void swap(int[] arr, int i, int j) {
		final int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
