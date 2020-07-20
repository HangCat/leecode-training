package sort;

import static sort.CommonUtil.*;

/**
 * @author zhouyp
 * @program algorithm
 * @description 快排
 * @create 2020-03-22
 */
public class QuickSorting {

	public static void main(String[] args) {
		final int[] arr = genRandomIntArr(7, 20);
		final int[] start = copyArr(arr);

		printIntArr(start);
		quickSort(arr);
//		System.out.println("partition = " + partitionByFor(arr, 0, arr.length - 1));

		printIntArr(arr);
		System.out.println("isSorted:           " + isSorted(arr));
		System.out.println("isSameElement:      " + isSameElement(arr, start));
		printEleCount(arr);
		printEleCount(start);
	}


	public static void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	public static void quickSort(int[] arr, int low, int high) {
		if (high > low) {
			int pivot = partitionByFor(arr, low, high);
			quickSort(arr, low, pivot - 1);
			quickSort(arr, pivot + 1, high);
		}
	}

	/**
	 * 快速排序的核心算法是partition
	 * 自定义一个 pivot，
	 * 通过传入（int[] arr, int low, int high）
	 * 把数组分为 比 pivot 大的一部分和 pivot 小的一部分
	 **/


	/**
	 * 这是最纯正的快速排序
	 * pivot元素可以随便自定义一个
	 */
	public static int partitionByWhile(int[] arr, int low, int high) {
		int left = low;
		int right = high;
		int pivot_item = arr[high / 2];

		while (left < right) {
			while (left < right && arr[right] >= pivot_item) {
				right--;
			}

			if (left < right) {
				arr[left] = arr[right];
			}

			while (left < right && arr[left] <= pivot_item) {
				left++;
			}

			if (left < right) {
				arr[right] = arr[left];
			} else {
				arr[left] = pivot_item;
			}

		}

		return right;
	}

	/**
	 * 这个partition算法不太纯正，它的 pivot 不能自定义
	 * 这个算法的原理是，假如拿到一个 pivot
	 * 把数组中比 pivot 大的放到一个子数组中，把比 pivot 小的放到一个子数组中
	 * 最后再合并两个数组
	 * 以这种思想，选择数组中最右边的元素作为 pivot， 维护一个变量i
	 * 遍历数组，被遍历的数组元素下标为j，当遇到比pivot大的，交换第j个元素和i个元素
	 * 这样做，保证了 0 到 i 的数据都是小于 pivot的
	 * <p>
	 * 这个算法更简洁，但是有个弊端，只能取两端的元素作为 pivot，
	 * 它的优势在于不需要像纯正的partition算法移动两端的下标以保证 pivot 两边的大小
	 * 只需要维护一边的下标
	 * <p>
	 * 如果它任意取一个元素作为pivot的话，那么也需要维护两边的下标了，也就退化成了纯正的partition算法
	 */
	public static int partitionByFor(int[] arr, int low, int high) {
		/*int pivot = arr[low];
		int i = high;
		for (int j = high; j > low; j--) {
			if (arr[j] >= pivot) {
				swap(arr, i, j);
				i--;
			}
		}
		swap(arr, i, low);*/

		int pivot = arr[high];
		int i = low;

		for (int j = low; j < high; j++) {
			if (arr[j] < pivot) {
				swap(arr, i, j);
				i++;
			}
		}
		// 因为循环中已经判断出来了，第i位之后的元素都比pivot大，所以i和pivot需要swap
		swap(arr, i, high);

		return i;
	}


}