package geektime.practice.recursion;

import static sort.CommonUtil.printIntArr;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description
 * @create 2020-07-30
 */
public class HeapSort {

	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 10, 9, 12, 11, 13, 14, 16};
		heapSort(nums, nums.length);
		printIntArr(nums);
	}

	static int[] heapSort(int[] nums, int size) {

		final int last_pos = size - 1;
		final int last_parent = last_pos >>> 1;

		for (int i = last_parent; i >= 0; i--) {
			heapify(nums, i, size);
		}
		printIntArr(nums);

		for (int i = last_pos; i > 0; i--) {
			swap(nums, 0, i);
			heapify(nums, 0, i);
		}

		return nums;
	}

	private static void heapify(int[] nums, int index, int size) {
		if (index >= size) return;

		final int left_child_idx = 2 * index + 1;
		final int right_child_idx = 2 * index + 2;
		int max_idx = index;

		if (left_child_idx < size && nums[left_child_idx] > nums[max_idx]) {
			max_idx = left_child_idx;
		}

		if (right_child_idx < size && nums[right_child_idx] > nums[max_idx]) {
			max_idx = right_child_idx;
		}

		if (max_idx != index) {
			swap(nums, index, max_idx);
			heapify(nums, max_idx, size);
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	static int[] heap = new int[10];

	private static void insert(int num) {
		int index = heap.length;
		insert(num, index);
	}

	private static void insert(int num, int index) {
		final int parent = num / 2;
		int max = parent;
		if (num > heap[parent]) {
			max = index;
		}
		if (max != parent) {
			swap(heap, parent, index);
			insert(num, parent);
		}
	}
}
