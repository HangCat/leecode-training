package geektime.practice.doubleindex;

import java.util.Arrays;

import static primary.CommonUtil.genRandomIntArr;
import static primary.CommonUtil.printIntArr;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description 给定两个数组，编写一个函数来计算它们的交集。
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * @create 2020-06-29
 */
public class IntersectWithRepeat {

	public static void main(String[] args) {
		final int[] arr1 = genRandomIntArr(10);
		printIntArr(arr1);
		final int[] arr2 = genRandomIntArr(10);
		printIntArr(arr2);

		printIntArr(intersect(arr1, arr2));
	}

	static int[] intersect(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int i = 0, j = 0, k = 0;
		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] < nums2[j]) {
				++i;
			} else if (nums1[i] > nums2[j]) {
				++j;
			} else {
				nums1[k++] = nums1[i++];
				++j;
			}
		}
		return Arrays.copyOfRange(nums1, 0, k);
	}

	static int[] i1(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int i = 0, j = 0, k = 0;
		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] < nums2[j]) {
				++i;
			} else if (nums1[i] > nums2[j]) {
				++j;
			} else {
				nums1[k++] = nums1[i++];
				++j;
			}
		}
		return Arrays.copyOfRange(nums1, 0, k);
	}
}
