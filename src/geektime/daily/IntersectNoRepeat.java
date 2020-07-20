package geektime.daily;

import java.util.HashSet;

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
public class IntersectNoRepeat {

	public static void main(String[] args) {
		final int[] arr1 = genRandomIntArr(10);
		printIntArr(arr1);
		final int[] arr2 = genRandomIntArr(10);
		printIntArr(arr2);

		printIntArr(intersect(arr1, arr2));
	}

	static int[] intersect(int[] nums1, int[] nums2) {
		final HashSet<Integer> set1 = new HashSet<>();
		for (Integer i : nums1) set1.add(i);
		final HashSet<Integer> set2 = new HashSet<>();
		for (Integer i : nums2) set2.add(i);
		set1.retainAll(set2);

		final int[] result = new int[set1.size()];
		int index = 0;
		for (Integer i : set1) result[index++] = i;
		return result;
	}

}
