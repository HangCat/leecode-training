package geektime.practice.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 * @create 2020-06-27
 */
public class WordPattern {

	public static void main(String[] args) {

	}

	public static boolean wordPattern(String pattern, String str) {
		final char[] chars = pattern.toCharArray();
		final String[] split = str.split(" ");
		if (chars.length != split.length) return false;

		final Map<Character, Integer> map = new HashMap<>();
		for (Integer i = 0; i < chars.length; i++) {
			if (map.put(pattern.charAt(i), i) != map.put(chars[i], i)) {
				return false;
			}
		}
		return true;

	}

	@SuppressWarnings(value = {"rawtypes","unchecked"})
	static boolean w2(String pattern, String str) {
		String[] words = str.split(" ");
		if (words.length != pattern.length()) return false;
		Map index = new HashMap();
		for (Integer i = 0; i < words.length; ++i)
			if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
				return false;
		return true;
	}

}
