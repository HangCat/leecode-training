package sort;

import static sort.CommonUtil.genRandomIntArr;
import static sort.CommonUtil.printIntArr;

/**
 * @author zhouyp
 * @program algorithm
 * @description 冒泡排序
 * @create 2020-03-18
 */
public class BubbleSorting {

	public static void main(String[] args) {

		final int[] ints1 = genRandomIntArr(10);
		printIntArr(ints1);
		unknownSort2(ints1);
		printIntArr(ints1);
	}

	public static void bubbleSortBasePutSmallLeft(int[] array) {
		bubbleSortBasePutSmallLeft(array, array.length);
	}

	public static void bubbleSortOptimized(int[] array) {
		bubbleSortOptimized(array, array.length);
	}

	public static void bubbleSortBasePutBigRight(int[] array) {
		bubbleSortBasePutBigRight(array, array.length);
	}

	public static void unknownSort2(int[] array) {
		unknownSort2(array, array.length);
	}

	/**
	 * 每次内层循环结束之后，都能保证本次循环（内层）的数据是排好序的
	 * 内层循环必须是，从没有排序的开始作判断。
	 * 因为内层循环是排好序了，没有排序的，判断到应该交换的位置后，后面的位置才能顺延
	 * 被注释的方法不行，因为它是从排好序的数组中，从头到尾的重新判断，这是没必要的
	 */
	public static void unknownSort2(int[] array, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j > 0; j--) {
				if (array[j] < array[j - 1]) {
					final int temp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = temp;
				}
			}
			/*for (int j = 0; j < i; j++) {
				if (array[j] > array[j + 1]) {
					final int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}*/
		}
	}


	public static void bubbleSortBasePutSmallLeft(int[] array, int n) {
		boolean swapped = true;
		for (int i = 0; i < n && swapped; i++) {
			swapped = false;
			for (int j = n - 1; j > i; j--) {
				if (array[j] < array[j - 1]) {
					final int temp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = temp;
					swapped = true;
				}
			}
		}
	}

	/**
	 * 冒泡排序，两两比较，比较后交换。
	 * 内层循环，两两比较，比较后交换，比较出本次范围内最大或最小的值。
	 * 外层循环，内层循环找出最大（或最小的）之后，放到最前面或最后面，比较范围减少一个。
	 */
	public static void bubbleSortBasePutBigRight(int[] array, int n) {
		for (int i = n; i >= 0; i--) {
			for (int j = 0; j < i - 1; j++) {
				if (array[j] > array[j + 1]) {
					final int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * 优化的内容是，内层循环。
	 * 假如内层循环是有序（没有把swapped修改为true，说明就是有序的），那整体都是有序的
	 */
	public static void bubbleSortOptimized(int[] array, int n) {
		boolean swapped = true;
		for (int i = n; i >= 0 && swapped; i--) {
			swapped = false;
			for (int j = 0; j < i - 1; j++) {
				if (array[j] > array[j + 1]) {
					final int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					swapped = true;
				}
			}
		}
	}


}
