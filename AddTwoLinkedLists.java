/**
 * https://leetcode.com/problems/add-two-numbers/
 * @author Srinivas Maroju
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *
 */
public class AddTwoLinkedLists {
	public static void main(String[] args) {

		ListNode list1 = ListNode.createLists(new Integer[] {2,4,3});
		ListNode list2 = ListNode.createLists(new Integer[] {5,6,4});
		AddTwoLinkedLists atll = new AddTwoLinkedLists();
		ListNode l3 = atll.addTwoNumbers(list1, list2);
		System.out.println(ListNode.printList(l3));
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode head = null;
		ListNode prev = null;
		int sum = 0;
		int carry = 0;
		int prev_carry = 0;
		while(l1!=null || l2!=null) {
			int v1 = l1==null ? 0 : l1.val;
			int v2 = l2==null ? 0 : l2.val;
			
			
			sum = (v1 + v2 + prev_carry) % 10;
			carry =  (v1 + v2 + prev_carry) / 10;

			
			prev_carry = carry;
			
			ListNode item = new ListNode(sum);
			
			if(head==null) {
				head = item;
				prev = head;
			} else {
				prev.next = item;
				prev = item;
			}

			l1 = (l1!=null) ? l1.next: l1;
			l2 = (l2!=null) ? l2.next: l2;
		}
		
		if(carry == 1) {
			ListNode item = new ListNode(1);
			prev.next = item;
		}
		
		return head;
	}

	
	
	// Definition for singly-linked list.
	static class ListNode {
		int val;
		ListNode next;
		public ListNode(int x) { val = x; }
		public String toString() {
			return ""+val;
		}

		private static ListNode createLists(Integer[] list) {
			ListNode prev = null;
			ListNode head = null;
			for(int elem:list) {
				ListNode item = new ListNode(elem);
				if(prev==null) {
					head = item;
					prev = item;
				} else {
					prev.next = item;
				}
				prev = item;
			}
			return head;
		}
		public static String printList(ListNode list) {
			StringBuffer sb = new StringBuffer("");

			while(list!=null) {
				sb.append(list);
				if(list.next!=null) {
					sb.append("->");
				}
				list = list.next;
			}
			sb.append(System.lineSeparator());

			return sb.toString();
		}
	}
	
}



