package geektime.practice.tree;

/**
 * @author zhouyp
 * @program leecode-training
 * @description
 * @create 2020-08-11
 */
public class Pair<T, T1> {


	private final T key;
	private final T1 value;

	public Pair(T key, T1 value) {
		this.key = key;
		this.value = value;
	}

	public T getKey() {
		return this.key;

	}

	public T1 getValue() {
		return this.value;
	}
}
