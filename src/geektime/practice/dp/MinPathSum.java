package geektime.practice.dp;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * 示例:
 * 输入:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * @create 2020-07-27
 */
public class MinPathSum {

	public static void main(String[] args) {
		int[][] grid = {{1}};
//		int[][] grid = {{3, 8, 6, 0, 5, 9, 9, 6, 3, 4, 0, 5, 7, 3, 9, 3}, {0, 9, 2, 5, 5, 4, 9, 1, 4, 6, 9, 5, 6, 7, 3, 2}, {8, 2, 2, 3, 3, 3, 1, 6, 9, 1, 1, 6, 6, 2, 1, 9}, {1, 3, 6, 9, 9, 5, 0, 3, 4, 9, 1, 0, 9, 6, 2, 7}, {8, 6, 2, 2, 1, 3, 0, 0, 7, 2, 7, 5, 4, 8, 4, 8}, {4, 1, 9, 5, 8, 9, 9, 2, 0, 2, 5, 1, 8, 7, 0, 9}, {6, 2, 1, 7, 8, 1, 8, 5, 5, 7, 0, 2, 5, 7, 2, 1}, {8, 1, 7, 6, 2, 8, 1, 2, 2, 6, 4, 0, 5, 4, 1, 3}, {9, 2, 1, 7, 6, 1, 4, 3, 8, 6, 5, 5, 3, 9, 7, 3}, {0, 6, 0, 2, 4, 3, 7, 6, 1, 3, 8, 6, 9, 0, 0, 8}, {4, 3, 7, 2, 4, 3, 6, 4, 0, 3, 9, 5, 3, 6, 9, 3}, {2, 1, 8, 8, 4, 5, 6, 5, 8, 7, 3, 7, 7, 5, 8, 3}, {0, 7, 6, 6, 1, 2, 0, 3, 5, 0, 8, 0, 8, 7, 4, 3}, {0, 4, 3, 4, 9, 0, 1, 9, 7, 7, 8, 6, 4, 6, 9, 5}, {6, 5, 1, 9, 9, 2, 2, 7, 4, 2, 7, 2, 2, 3, 7, 2}, {7, 1, 9, 6, 1, 2, 7, 0, 9, 6, 6, 4, 4, 5, 1, 0}, {3, 4, 9, 2, 8, 3, 1, 2, 6, 9, 7, 0, 2, 4, 2, 0}, {5, 1, 8, 8, 4, 6, 8, 5, 2, 4, 1, 6, 2, 2, 9, 7}};
//		int[][] grid = {{1, 2, 5}, {3, 2, 1}};
//		int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
		System.out.println("minPathSum = " + minPathSum(grid));
	}

	static int minPathSum(int[][] grid) {
		int col = grid.length - 1;
//		if (col <= 0) return 0;

		final int row = grid[0].length - 1;
//		if (row <= 0) return 0;

		int[][] memo = new int[col + 1][row + 1];
//		return minPathSumDP(grid);
		return minPathSumStupidRecursion(grid, row, col, memo);
//		return minPathSumStupidRecursion(grid, row, col);
	}


	private static int minPathSumStupidRecursion(int[][] grid, int row, int col, int[][] memo) {
		if (col < 0 || row < 0) return 0;
		if (memo[col][row] > 0) return memo[col][row];
		if (row == 0 && col == 0) memo[col][row] = grid[0][0];
		if (row == 0) {
			memo[col][row] = minPathSumStupidRecursion(grid, row, col - 1, memo) + grid[col][row];
		} else if (col == 0) {
			memo[col][row] = minPathSumStupidRecursion(grid, row - 1, col, memo) + grid[col][row];
		} else {
			final int upMin = minPathSumStupidRecursion(grid, row, col - 1, memo);
			final int leftMin = minPathSumStupidRecursion(grid, row - 1, col, memo);
			memo[col][row] = Math.min(leftMin, upMin) + grid[col][row];
		}
		return memo[col][row];
	}

	private static int minPathSumStupidRecursion(int[][] grid, int row, int col) {
		if (row == 0 && col == 0) return grid[0][0];
		if (row == 0) {
			return minPathSumStupidRecursion(grid, row, col - 1) + grid[col][row];
		}
		if (col == 0) {
			return minPathSumStupidRecursion(grid, row - 1, col) + grid[col][row];
		}
		int upMin = minPathSumStupidRecursion(grid, row, col - 1);
		int leftMin = minPathSumStupidRecursion(grid, row - 1, col);
		return Math.min(leftMin, upMin) + grid[col][row];
	}

	private static int minPathSumDP(int[][] grid) {
		if (grid == null || grid.length < 1 || grid[0] == null || grid[0].length < 1) {
			return 0;
		}
		int row = grid.length;
		int col = grid[row - 1].length;

		int[][] dp = new int[row][col];

		dp[0][0] = grid[0][0];

		for (int i = 1; i < row; i++) {
			dp[i][0] = dp[i - 1][0] + grid[i][0];
		}

		for (int i = 1; i < col; i++) {
			dp[0][i] = dp[0][i - 1] + grid[0][i];
		}

		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
			}
		}

		return dp[row - 1][col - 1];
	}
}
