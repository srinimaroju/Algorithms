import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * 
 * Input:
	[
	  1->4->5,
	  1->3->4,
	  2->6
	]
	Output: 1->1->2->3->4->4->5->6
 * 
 * 
 * @author Srinivas Maroju
 *
 */
public class MergeKLists {
	public static void main(String []args) {
		int[] list1 = {1,4,5};
		int[] list2 = {1,3,4};
		int[] list3 = {2,6};
		int[][] lists_array = {list1, list2, list3};

		ListNode[] lists = createLists(lists_array);
		System.out.println("--- Before Merge ---------");
		System.out.println(printLists(lists));

		MergeKLists mkl = new MergeKLists();
		ListNode result = mkl.mergeKLists(lists);

		System.out.println("--- After Merge ---------");

		ListNode[] resultList = new ListNode[1];
		resultList[0] = result;
		System.out.println(printLists(resultList));

	}
	private static ListNode[] createLists(int[][] lists_array) {
		ListNode[] lists = new ListNode[lists_array.length];
		int i=0;
		for(int[] list:lists_array) {
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
			lists[i++] = head;
		}
		return lists;
	}
	public ListNode mergeKLists(ListNode[] lists) {
		//Reverse Order
		PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(
				new Comparator<ListNode>() {
					public int compare(ListNode a, ListNode b)  {
						return new Integer(a.val).compareTo(new Integer(b.val));
					}
				});
		ListNode prev = null;
		ListNode head = null;
		
		for(int i=0;i<lists.length;i++) {
			pq.add(lists[i]);
		}
		
		while(!pq.isEmpty()) {
			ListNode item = pq.poll();
			if(head == null) {
				head = item;
			}
			if(prev == null) {
				prev = item;
			} else {
				prev.next = item;
			}
			prev = item;
			
			if(item.next!=null) {
				pq.add(item.next);
			}
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
	public static String printLists(ListNode[] lists) {
		StringBuffer sb = new StringBuffer("");
		for(ListNode list:lists) {
			while(list!=null) {
				sb.append(list);
				if(list.next!=null) {
					sb.append("->");
				}
				list = list.next;
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
}

/**
 * Definition for singly-linked list. 
 * */
class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
	public String toString() {
		return ""+val;
	}
}
