package geektime.practice.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description 括号生成
 * @create 2020-07-06
 */
public class GenerateParenthesis {

	public static void main(String[] args) {
		generateParenthesis(2);
	}


	static List<String> generateParenthesis(int n) {
		final List<String> res = new ArrayList<>();
		generateParenthesis(n, 0, 0, "", res);
		return res;
	}

	static void generateParenthesis(int n, int left, int right, String s, List<String> res) {
		if (left == n && right == n) {
			System.out.println(s);
			res.add(s);
			return;
		}
		if (left < n) {
			generateParenthesis(n, left + 1, right, s + "(", res);
		}
		if (right < left) {
			generateParenthesis(n, left, right + 1, s + ")", res);
		}
	}

	ArrayList[] cache = new ArrayList[100];

	public List<String> generate(int n) {
		if (cache[n] != null) {
			return cache[n];
		}
		ArrayList<String> ans = new ArrayList();
		if (n == 0) {
			ans.add("");
		} else {
			for (int c = 0; c < n; ++c)
				for (String left : generate(c))
					for (String right : generate(n - 1 - c))
						ans.add("(" + left + ")" + right);
		}
		cache[n] = ans;
		return ans;
	}

	final List<String> res = new ArrayList<>();

	List<String> gen(int n) {

		gen(n, 0, 0, "");
		return res;
	}

	private void gen(int n, int left, int right, String s) {
		if (left == n && right == n) {
			res.add(s);
			return;
		}
		if (left < n) {
			gen(n, left + 1, right, s + "(");
		}
		if (right < left) {
			gen(n, left, right + 1, s + ")");
		}
	}


	// 回溯法
	public List<String> generateParenthesisBack(int n) {
		return generate(n);
	}

	public List<String> generateParenthesis1(int n) {
		List<String> combinations = new ArrayList<>();
		generateAll(new char[2 * n], 0, combinations);
		return combinations;
	}

	public void generateAll(char[] current, int pos, List<String> result) {
		if (pos == current.length) {
			if (valid(current))
				result.add(new String(current));
		} else {
			current[pos] = '(';
			generateAll(current, pos + 1, result);
			current[pos] = ')';
			generateAll(current, pos + 1, result);
		}
	}

	public boolean valid(char[] current) {
		int balance = 0;
		for (char c : current) {
			if (c == '(') balance++;
			else balance--;
			if (balance < 0) return false;
		}
		return (balance == 0);
	}

}
