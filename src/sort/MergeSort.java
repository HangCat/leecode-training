package sort;

import static sort.CommonUtil.*;

/**
 * @author zhouyp
 * @program algorithm
 * @description 归并排序
 * @create 2020-03-21
 */
public class MergeSort {

	public static void main(String[] args) {
		final int[] arr = genRandomIntArr(113);
		printIntArr(arr);
		mergeSort(arr);
		printIntArr(arr);
		System.out.println(isSorted(arr));
	}

	public static void mergeSort(int[] array) {
		final int size = array.length;
		final int[] temp = new int[size];
		mergeSort(array, temp, 0, size - 1);
	}

	/**
	 * 归并排序，递归到左边和右边的元素只有一个的时候终止，归并执行的核心函数是merge
	 * left和right是作为这个递归函数，必须要的两个参数，以供下层递归作参数使用
	 *
	 * @param arr   原始需要排序的数组
	 * @param temp  临时数组，作为中间缓存。为什么需要，在merge方法有说
	 * @param left  merge的左边位置
	 * @param right merge的右边位置
	 */
	static void mergeSort(int[] arr, int[] temp, int left, int right) {
		// left == right 作为递归的终止条件
		// 意味着，上一次的合并，左边和右边分别只有一个元素。
		// 上次的合并排序完成之后就可以终止递归了
		if (right > left) {
			int mid = (right + left) / 2;
			mergeSort(arr, temp, left, mid);
			mergeSort(arr, temp, mid + 1, right);
			merge(arr, temp, left, mid + 1, right);
		}
	}

	/**
	 * 合并方法，通过temp数组。排序的数据依次写到temp数组中，最后再通过right和left参数，把temp中的数据写入到原始arr数组中。
	 * arr数组是原始数组，但是每次merge之后，会修改原始数组数据
	 *
	 * @param arr   原始数组
	 * @param temp  临时数组，用来作拷贝用。这里可以非必传，然后在merge方法内部自己创建。
	 *              但是这样做没必要，如果要合并的次数多的话，每次合并内部都需要创建一个自己的数组，再在方法结束之后销毁。
	 *              这样的开销是没必要的，还不如在递归之前就创建，始终用同一个数组。这个数组只会在排序结束之后销毁。
	 * @param left  最左边的位置
	 * @param mid   中间的位置
	 * @param right 最右边的位置
	 */
	static void merge(int[] arr, int[] temp, int left, int mid, int right) {
		int left_end, size, temp_pos;
		left_end = mid - 1;
		temp_pos = left;
		size = right - left + 1;
		// 这次while循环之后，left会大于left_end 或者 mid会大于right
		// 所以这样个变量虽然是本次方法使用的，但是已经不可靠了
		// 意思是说，这个循环执行之后，left不再是最左边的位置，mid也不是中间的位置了
		// 但是 right 依旧是本次merge数组最右边的位置
		// left_end 依旧是本次merge数组左边数组最后一位
		// temp_pos 依旧是临时数组当前已经排完序的位置
		// size 依旧是本次merge数组的总长度
		while (left <= left_end && mid <= right) {
			if (arr[left] <= arr[mid]) {
				temp[temp_pos] = arr[left];
				left++;
			} else {
				temp[temp_pos] = arr[mid];
				mid++;
			}
			temp_pos++;
		}
		// 如果左边数组left位置的元素大于右边的所有元素
		while (left <= left_end) {
			temp[temp_pos] = arr[left];
			temp_pos++;
			left++;
		}
		// 如果右边数组mid位置的元素大于左边的所有元素
		while (mid <= right) {
			temp[temp_pos] = arr[mid];
			temp_pos++;
			mid++;
		}
		// 把temp排完序的内容，拷贝到arr原始数组中
		// 这里拷贝之后，原始数组的 left（最开始的）到 right 的合并排序就结束了
		// 原始数组的这段位置的数据也是 有序的了
		for (int i = 0; i < size; i++) {
			arr[right] = temp[right];
			right--;
		}
	}

}
