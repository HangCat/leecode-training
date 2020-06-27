package geektime.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description
 * @create 2020-06-24
 */
public class TwoSum {
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i <= nums.length; i++) {
			if (map.get(target - nums[i]) != null) {
				return new int[]{map.get(target - nums[i]), i};
			}
			map.put(nums[i], i);
		}
		return new int[]{0, 0};
	}

	public int[] s1(int[] nums, int target) {
		final Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (map.get(target - nums[i]) != null) {
				return new int[]{map.get(target - nums[i]), i};
			}
			map.put(nums[i], i);
		}
		return new int[]{0, 0};
	}


}
