package sort;

import static sort.CommonUtil.genRandomIntArr;
import static sort.CommonUtil.printIntArr;

/**
 * @author zhouyp
 * @program algorithm
 * @description 选择排序
 * @create 2020-03-21
 */
public class SelectSorting {

	public static void main(String[] args) {
		final int[] ints1 = genRandomIntArr(10);
		printIntArr(ints1);
		selectSort(ints1);
		printIntArr(ints1);
	}

	public static void selectSortBase(int[] array) {
		selectSortBase(array, array.length);
	}

	public static void selectSort(int[] array) {
		selectSort(array, array.length);
	}

	/**
	 * 因为它重复选择最小的元素，所以叫选择排序
	 */
	public static void selectSort(int[] arr, int n) {
		int min;
		for (int i = 0; i < n - 1; i++) {
			min = i;
			for (int j = i + 1; j < n; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			int temp = arr[min];
			arr[min] = arr[i];
			arr[i] = temp;
		}
	}

	/**
	 * 这个应该也属于选择排序。
	 * 每依次最外层拿出一个元素，和内层的所有元素比较。
	 * 第一次拿出的一定是最大（或最小）的，最后一次一定是最小（或最大）的
	 * <p>
	 * 这个swapped应该是没有优化到,在冒泡排序的时候，swapped是为了确认内层循环有序的而使用
	 * 这里，更本无法判断内层循环是否有序，只能判断int[i]是不是比别人小
	 */
	public static void selectSortBase(int[] array, int n) {
		boolean swapped = true;
		for (int i = 0; i < n && swapped; i++) {
			swapped = false;
			for (int j = i; j < n; j++) {
				if (array[i] > array[j]) {
					final int temp = array[j];
					array[j] = array[i];
					array[i] = temp;
					swapped = true;
				}
			}
		}
	}
}
