package sort;

import static sort.CommonUtil.*;


/**
 * @author zhouyp
 * @program algorithm
 * @description
 * @create 2020-03-22
 */

public class HeapSort {

	public static void main(String[] args) {
		int[] arr = genRandomIntArr(21);
		printIntArr(arr);
		// 堆排序
		heapSort(arr);
		printIntArr(arr);
		System.out.println(isSorted(arr));
	}

	/**
	 * 堆排序
	 */
	private static void heapSort(int[] arr) {
		heapSort(arr, arr.length);
	}

	/**
	 * 堆排序
	 * 先是把整个数组构造成一个最大堆，再把最大堆的头节点，与数组最后一个位置交换
	 * 重复上面操作，完成排序
	 */
	private static void heapSort(int[] arr, int size) {
		// 将待排序的序列构建成一个大顶堆
		// 不用去遍历叶子节点  因为父节点heapify()之后，子节点一定是遵守堆的规则
		// heapify()的时候，从下往上heapify()，这样能保证最后一次heapify()之后，整个数组是一个最大堆
		int last_node_pos = size - 1;
		int parent = last_node_pos / 2;//最后一个节点的位置除2就是它的父节点
		for (int i = parent; i >= 0; i--) {
			heapify(arr, i, size);
		}

		// 让每次循环的时候，将本次循环最大的数据放在数组尾
		// 下一次循环不去处理数组尾的元素
		// 将堆顶元素与本次循环最后一个元素交换
		// 交换之后，重新将 0 到 i-- 位置的数组搞成一个最大堆
		for (int i = last_node_pos; i > 0; i--) {
			swap(arr, 0, i);
			heapify(arr, 0, i);
		}
	}

	/**
	 * 构建堆的过程
	 * 左子节点等于 2i + 1,
	 * 右子节点等于 2i + 2
	 * 父节点等于 (node_pos - 1)/2
	 *
	 * @param arr      需要排序的数组
	 * @param node_pos 需要构建堆的根节点的序号
	 * @param n        数组的长度
	 */
	private static void heapify(int[] arr, int node_pos, int n) {
		if (node_pos >= n) return;

		int left_child = 2 * node_pos + 1;
		int right_child = 2 * node_pos + 2;
		int max = node_pos;

		if (left_child < n && arr[left_child] > arr[max]) {
			max = left_child;
		}

		if (right_child < n && arr[right_child] > arr[max]) {
			max = right_child;
		}

		// 保证了，父节点的位置，一定是最大的。两边子节点，大小无所谓
		if (max != node_pos) {
			swap(arr, max, node_pos);
			heapify(arr, max, n);
		}

	}

}