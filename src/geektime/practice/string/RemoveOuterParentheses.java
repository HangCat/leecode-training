package geektime.practice.string;

import java.util.Arrays;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description 有效括号字符串为空 ("")、"(" + A + ")" 或 A + B，
 * 其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。
 * 例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。
 * 如果有效字符串 S 非空，且不存在将其拆分为 S = A+B 的方法，我们称其为原语（primitive），
 * 其中 A 和 B 都是非空有效括号字符串。
 * 给出一个非空有效字符串 S，考虑将其进行原语化分解，
 * 使得：S = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。
 * 对 S 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 S 。
 * @create 2020-06-30
 */
public class RemoveOuterParentheses {

	public static void main(String[] args) {
		String aa = "(()())(())(()(()))";
		String bb = "()()()()(())";
		String cc = "()()()()()";

		String d = "(()())(())(()(()))";
		String e = "()()()()(())";
		System.out.println(removeOuterParentheses(aa));
	}

	static String removeOuterParentheses(String S) {
		StringBuilder sb = new StringBuilder();
		int level = 0;
		for (char c : S.toCharArray()) {
			if (c == ')') --level;
			if (level >= 1) sb.append(c);
			if (c == '(') ++level;

			if (c == '(' && level++ > 0) sb.append(c);
			if (c == ')' && level-- > 1) sb.append(c);
		}
		return sb.toString();
	}

	static String getInside(String S) {
		final char[] chars = S.toCharArray();
		int index = 0;
		char lastTime = '0';
		for (char aChar : chars) {
			if (lastTime == '(' && aChar == ')') {
				chars[index++] = '(';
				chars[index++] = ')';
			}
			lastTime = aChar;
		}
		return new String(Arrays.copyOfRange(chars, 0, index));
	}


}
