package geektime.practice.recursion;

/**
 * @author zhouyp
 * @program leecode-training
 * @description 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 * @create 2020-07-29
 */
public class CoinChange {

	public static void main(String[] args) {
		int[] coins = {1, 2, 5};
		int target = 11;
		System.out.println("coinChange = " + coinChange(coins, target));
	}

	static int min = Integer.MAX_VALUE;

	static int coinChange(int[] coins, int amount) {
		if (amount < 1) return 0;
		coinChangeRecursion(coins, amount, 0);
		return min;
	}

	private static void coinChangeRecursion(int[] coins, int amount, int count) {
		if (amount < 0) return;
		if (amount == 0)
			min = Math.min(min, count);

		for (int coin : coins) {
			coinChangeRecursion(coins, amount - coin, count + 1);
		}
	}

}
