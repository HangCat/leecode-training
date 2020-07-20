package sort;

import static sort.CommonUtil.genRandomIntArr;
import static sort.CommonUtil.isSorted;

/**
 * @author zhouyp
 * @program algorithm
 * @description 希尔排序
 * @create 2020-03-21
 */
public class ShellSorting {

	public static void main(String[] args) {
		for (int i = 0; ; i++) {
			final int[] ints1 = genRandomIntArr(100);
			shellSort(ints1);
			if (!isSorted(ints1)) {
				System.out.println("第" + i + "次排序没有成功");
				break;
			}
		}
	}

	public static void shellSort(int[] array) {
		shellSort(array, array.length);
	}


	/**
	 * 分三次循环
	 * 最外层循环目的是让每隔h位的元素形成的数组进行一次插入排序
	 * 内层循环就是插入排序
	 * <p>
	 * 希尔排序能让距离较远的元素，移动的次数更少
	 * 比如，数字0在第200位，如果用常规插入排序，则需要移动200次
	 * 在希尔排序中，数字0最多需要移动 200/h - 1次
	 * <p>
	 * 插入排序在原本有序的情况下，效率本身就会高一些
	 * 希尔排序保证了最后一次排序的比较和交换次数更少
	 */
	public static void shellSort(int[] arr, int n) {
		int h = 1;
		while (h < n / 9) {
			h = 3 * h + 1;
		}
		for (; h > 0; h = h / 3) {
			for (int i = h; i < n; i++) {
				int v = arr[i];
				int j = i;
				while (j >= h && arr[j - h] > v) {
					arr[j] = arr[j - h];
					j -= h;
				}
				arr[j] = v;
			}
		}
	}

}
