package geektime.practice.string;

import java.util.*;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description 写一个程序，输出从 1 到 n 数字的字符串表示。
 * 1. 如果 n 是3的倍数，输出“Fizz”；
 * 2. 如果 n 是5的倍数，输出“Buzz”；
 * 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 * 示例：
 * n = 15,
 * 返回:
 * [ "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8",
 * "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz"]
 */
public class FizzBuzz {

	public static void main(String[] args) {
		System.out.println(fizzBuzz(10));
	}

	static List<String> fizzBuzz(int n) {
		final List<String> list = new ArrayList<>();
		final Map<Integer, String> map = new HashMap<>();
		map.put(3, "Fizz");
		map.put(5, "Buzz");
		for (int i = 1; i <= n; i++) {
			String ans = "";
			final Set<Integer> set = map.keySet();
			for (Integer j : set) {
				if (i % j == 0) ans += map.get(j);
			}
			if (ans == "") ans = i + "";
			list.add(ans);
		}
		return list;

	}
}
