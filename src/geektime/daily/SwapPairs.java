package geektime.daily;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例: 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * @create 2020-06-25
 */
public class SwapPairs {

	public static void main(String[] args) {
		final ListNode one = new ListNode(1);
		final ListNode two = new ListNode(2);
		final ListNode three = new ListNode(3);
		final ListNode four = new ListNode(4);
		final ListNode five = new ListNode(5);
		final ListNode six = new ListNode(6);
		one.next = two;
		two.next = three;
		three.next = four;
		four.next = five;
		five.next = six;

		System.out.println(one);

		final ListNode node = s2(one);

		System.out.println(node);
	}

	public static ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) return head;
		final ListNode second = head.next;
		head.next = swapPairs(second.next);
		second.next = head;
		return second;
	}


	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}

		@Override
		public String toString() {
			return "ListNode{" +
					"val=" + val +
					", next=" + next +
					'}';
		}
	}


	public static ListNode s1(ListNode head) {
		// Dummy node acts as the prevNode for the head node
		// of the list and hence stores pointer to the head node.
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode prevNode = dummy;
		while ((head != null) && (head.next != null)) {
			// Nodes to be swapped
			ListNode firstNode = head;
			ListNode secondNode = head.next;
			// Swapping
			prevNode.next = secondNode;
			firstNode.next = secondNode.next;
			secondNode.next = firstNode;
			// Reinitializing the head and prevNode for next swap
			prevNode = firstNode;
			head = firstNode.next; // jump
		}
		// Return the new head node.
		return dummy.next;
	}

	public static ListNode s2(ListNode head) {
		final ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode pre = dummy;

		while (head != null && head.next != null && head.next.next != null) {
			final ListNode first = head;
			final ListNode second = head.next;
			final ListNode third = head.next.next;

			pre.next = third;
			first.next = third.next;
			third.next = second;
			second.next = first;

			pre = first;
			head = first.next;
		}
		return dummy.next;
	}
}