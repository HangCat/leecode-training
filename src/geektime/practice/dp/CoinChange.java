package geektime.practice.dp;

import java.util.Arrays;

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
		System.out.println("coinChangeIterate = " + coinChangeIterate(coins, target));
	}

	static int coinChange(int[] coins, int amount) {
		if (amount < 1) return 0;
		return coinChangeMemo(coins, amount, new int[amount]);
	}

	static int coinChangeIterate(int[] coins, int amount) {
		if (amount < 1) return 0;
		final int[] dp = new int[amount + 1];
		Arrays.fill(dp, amount + 1);
		dp[0] = 0;
		for (int i = 0; i < amount; i++) {
			for (int coin : coins) {
				if (coin < i) {
					dp[i] = Math.min(dp[i], dp[i - coin] + 1);
				}
			}
		}
		return dp[amount] > amount ? -1 : dp[amount];
	}

	private static int coinChangeMemo(int[] coins, int amount, int[] memo) {
		if (amount < 0) return -1;
		if (amount == 0) return 0;
		if (memo[amount - 1] != 0) return memo[amount - 1];

		int min = Integer.MAX_VALUE;
		for (int coin : coins) {
			int res = coinChangeMemo(coins, amount - coin, memo);
			if (res >= 0 && res < min)
				min = 1 + res;
		}
		memo[amount - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
		return memo[amount - 1];
	}

}
