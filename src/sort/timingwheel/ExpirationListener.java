package sort.timingwheel;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description
 * @create 2020-08-07
 */
public interface ExpirationListener<E> {

	/**
	 * Invoking when a expired event occurs.
	 *
	 * @param expiredObject
	 */
	void expired(E expiredObject);

}
