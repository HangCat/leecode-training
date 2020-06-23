package geektime.daily;

import static primary.CommonUtil.printIntArr;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * @create 2020-06-23
 */
public class PlusOne {

	public static int[] plusOne(int[] digits) {
		for (int i = digits.length - 1; i >= 0; i--) {
			boolean plus = digits[i] == 9;
			if (i == 0 && plus) {
				digits = new int[digits.length + 1];
				digits[0] = 1;
				break;
			}
			if (plus) {
				digits[i] = 0;
				continue;
			}
			digits[i] = ++digits[i];
			break;
		}
		return digits;
	}
	public static int[] plusOne1(int[] digits) {
		for (int i = digits.length - 1; i >= 0; i--) {
			digits[i]++;
			digits[i] = digits[i] % 10;
			if (digits[i] != 0) return digits;
		}
		digits = new int[digits.length + 1];
		digits[0] = 1;
		return digits;
	}

	public static void main(String[] args) {
//		final int[] ints = genRandomIntArr(10);
		final int[] ints = {8, 9, 9, 9, 9, 9, 9};
		printIntArr(ints);
		printIntArr(plusOne(ints));

	}
}
