package sort;

import static sort.CommonUtil.genRandomIntArr;
import static sort.CommonUtil.printIntArr;

/**
 * @author zhouyp
 * @program algorithm
 * @description
 * @create 2020-03-21
 */
public class InsertSorting {

	public static void main(String[] args) {
		final int[] ints1 = genRandomIntArr(10);
		printIntArr(ints1);
		insertSort(ints1);
		printIntArr(ints1);
	}

	public static void insertSort(int[] array) {
		insertSort(array, array.length);
	}


	/**
	 * 插入排序，外层循环
	 * 拿到一个元素，就和前面的比较
	 * 如果前面的元素更大，就往后依次顺延
	 */
	public static void insertSort(int[] arr, int n) {
		/*
		for (int i = 1, j; i <= n - 1; i++) {
			int v = arr[i];
			j = i;
			while (j > 0 && arr[j - 1] > v) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = v;
		}
		 */
		for (int i = 0; i < n; i++) {
			int cmp = arr[i], j = i;
			while (j > 0 && arr[j - 1] > cmp) {
				arr[j] = arr[--j];
			}
			arr[j] = cmp;
		}
	}
}