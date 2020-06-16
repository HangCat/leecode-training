package primary;

import java.util.*;

/**
 * @author zhouyp
 * @program algorithm
 * @description 工具类，生成随即数组之类的功能
 * @create 2020-03-18
 */
public class CommonUtil {

	private static final Random random = new Random();

	private CommonUtil() {
	}

	public static int[] genRandomIntArr(int length, int bound) {
		int[] arr = new int[length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(bound);
		}
		return arr;
	}

	public static int[] genSortedIntArr(int length, int bound) {
		final int[] arr = genRandomIntArr(length, bound);
		Arrays.sort(arr);
		return arr;
	}

	public static int[] genSortedIntArr(int length) {
		return genSortedIntArr(length, length);
	}

	public static int[] genRandomIntArr(int length) {
		return genRandomIntArr(length, length);
	}

	public static boolean isSorted(int[] arr, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = n - 1; j > i; j--) {
				if (arr[j] < arr[j - 1]) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isSorted(int[] arr) {
		return isSorted(arr, arr.length);
	}

	public static void printIntArr(int[] arr) {
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * 两个数组内容是否一致
	 *
	 * @return
	 */
	public static boolean isSameElement(int[] arrA, int[] arrB) {
		final EleCount<Integer> eleCountA = new EleCount<>();
		for (int value : arrA) {
			eleCountA.addCount(value);
		}

		final EleCount<Integer> eleCountB = new EleCount<>();
		for (int value : arrA) {
			eleCountB.addCount(value);
		}

		final Set<Integer> arrKeySet = eleCountA.map.keySet();
		for (Integer integer : arrKeySet) {
			if (!eleCountA.map.get(integer).equals(eleCountA.map.get(integer))) {
				return false;
			}
		}
		return true;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void printDistinctEle(int[] arr) {
		final EleCount<Integer> eleCount = new EleCount<>();
		for (int value : arr) {
			eleCount.addCount(value);
		}
		System.out.println("distinct element:" + eleCount.toString());
	}


	private static class EleCount<T> {
		final private Map<T, Integer> map = new HashMap<>();

		void addCount(T ele) {
			Integer count = map.get(ele);
			if (count == null) {
				count = 0;
			}
			count++;
			map.put(ele, count);
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("EleCount{");
			final Set<T> keySet = map.keySet();
			final Iterator<T> ketIter = keySet.iterator();
			for (int i = 0; i < keySet.size(); i++) {
				final T integer = ketIter.next();
				sb.append(integer);
				sb.append(":");
				sb.append(map.get(integer));
				if (i != (keySet.size() - 1)) {
					sb.append(";");
				}
			}
			sb.append("}");
			return sb.toString();
		}
	}
}
