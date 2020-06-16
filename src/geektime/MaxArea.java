package geektime;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * @create 2020-06-15
 */
public class MaxArea {

	/**
	 * 暴力求解
	 * 先明确宽度，再根据宽度去乘符合此宽度的高度
	 * 需要两层循环   外层循环是明确宽度的  内层循环是遍历符合该宽度的高，并乘以宽度和比较
	 */
	public static int maxAreaInForce(int[] height) {
		int size = height.length - 1;
		int wide = size;
		int area = Math.min(height[0], height[size]) * wide;
		while (wide > 0) {
			wide--;
			for (int i = 0; (i + wide) <= size; i++) {
				int tempHeight = Math.min(height[i], height[i + wide]);
				int temp = wide * tempHeight;
				if (area < temp) {
					area = temp;
				}
			}
		}
		return area;
	}

	/**
	 * 双指针求解，该案例中
	 * 指针分为头指针和尾指针，两个指针移动的方向是相向（从两边向中间移动）
	 * 头指针从0开始index，尾指针从 height.length - 1 开始index
	 * 在循环中，每次移动两个指针中较小的那个指针
	 *
	 */
	public static int maxAreaInDoubleIndex(int[] height) {
		int wide = height.length - 1;

		int p = 0, q = wide;
		int minHeight, area = 0;

		for (; wide >= 0; wide--) {
			minHeight = height[p];
			if (height[p] > height[q]) {
				minHeight = height[q];
				q--;
			} else {
				p++;
			}
			int temp = minHeight * wide;
			if (temp > area) {
				area = temp;
			}
		}
		return area;
	}

	public static void main(String[] args) {
//		int[] aa = {1, 8, 6, 2, 5, 4, 8, 3, 7};
		int[] aa = {2, 1};
//		int[] aa = {1, 2, 4, 3};
		System.out.println("maxArea(aa) = " + maxAreaInForce(aa));
		System.out.println("maxAreaInDoubleIndex(aa) = " + maxAreaInDoubleIndex(aa));
	}

}
