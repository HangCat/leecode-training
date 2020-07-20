package geektime.practice.linkedList;

/**
 * @author zhouyp
 * @program leecodetraing
 * @description
 * @create 2020-06-26
 */
public class MergeTwoLists {

	public static void main(String[] args) {
		final ListNode one = new ListNode(1);
		final ListNode three = new ListNode(3);
		final ListNode five = new ListNode(5);
		one.next = three;
		three.next = five;

		final ListNode two = new ListNode(2);
		final ListNode four = new ListNode(4);
		final ListNode six = new ListNode(6);
		two.next = four;
		four.next = six;

		System.out.println("one = " + one);
		System.out.println("two = " + two);

		final ListNode node = mergeTwoListsR(one, two);

		System.out.println("node = " + node);
	}

	public static ListNode mergeTwoListsR(ListNode l1, ListNode l2) {
		if (l1 == null) return l2;
		else if (l2 == null) return l1;
		else if (l1.val <= l2.val) {
			l1.next = mergeTwoListsR(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoListsR(l2.next, l1);
			return l2;
		}
	}

	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		final ListNode node = new ListNode();
		ListNode pre = node;
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				pre.next = l1;
				l1 = l1.next;
			} else {
				pre.next = l2;
				l2 = l2.next;
			}
			pre = pre.next;
		}
		pre.next = l1 == null ? l2 : l1;
		return node.next;
	}


	/**
	 * 这个方法借鉴了归并排序的合并
	 */
	public static ListNode m1(ListNode l1, ListNode l2) {
		ListNode l1Index = l1;
		ListNode l2Index = l2;

		final ListNode node = new ListNode();
		ListNode curr = node;

		while (l1Index != null || l2Index != null) {
			if (l1Index == null) {
				curr.val = l2Index.val;
				l2Index = l2Index.next;
				if (l2Index != null) {
					curr.next = new ListNode();
				}
				curr = curr.next;
				continue;
			}
			if (l2Index == null) {
				curr.val = l1Index.val;
				l1Index = l1Index.next;
				if (l1Index != null) {
					curr.next = new ListNode();
				}
				curr = curr.next;
				continue;
			}

			if (l1Index.val <= l2Index.val) {
				curr.val = l1Index.val;
				l1Index = l1Index.next;
			} else {
				curr.val = l2Index.val;
				l2Index = l2Index.next;
			}
			curr.next = new ListNode();
			curr = curr.next;
		}
		return node;
	}

	public static class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}

		@Override
		public String toString() {
			return "ListNode{" +
					"val=" + val +
					", next=" + next +
					'}';
		}
	}
}
