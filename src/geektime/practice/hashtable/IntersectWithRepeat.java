package geektime.practice.hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
		if (nums1.length > nums2.length) {
			return intersect(nums2, nums1);
		}
		HashMap<Integer, Integer> m = new HashMap<>();
		for (int n : nums1) {
			m.put(n, m.getOrDefault(n, 0) + 1);
		}
		int k = 0;
		for (int n : nums2) {
			int cnt = m.getOrDefault(n, 0);
			if (cnt > 0) {
				nums1[k++] = n;
				m.put(n, cnt - 1);
			}
		}
		return Arrays.copyOfRange(nums1, 0, k);
	}
}
