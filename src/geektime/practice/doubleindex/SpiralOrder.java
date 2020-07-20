package geektime.practice.doubleindex;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description 定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * 示例 1:输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * @create 2020-07-09
 */
public class SpiralOrder {

	static List<Integer> spiralOrder(int[][] matrix) {
		final List<Integer> res = new ArrayList<>();
		int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1;
		while (left <= right && top <= bottom) {
			for (int i = left; i <= right; i++) {
				res.add(matrix[top][i]);
			}
			for (int i = top; i <= bottom; i++) {
				res.add(matrix[i][right]);
			}
			if (left < right && top < bottom) {
				for (int i = right - 1; i > left; i--) {
					res.add(matrix[bottom][i]);
				}
				for (int i = bottom; i > top; i--) {
					res.add(matrix[i][left]);
				}
			}
			left++;
			top++;
			right--;
			bottom--;
		}
		return res;
	}
}
