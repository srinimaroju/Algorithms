import java.util.HashMap;

/*
 * https://leetcode.com/problems/copy-list-with-random-pointer/
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/
public class CopyListRandomPointer {
	public static void main(String []args) {
		CopyListRandomPointer c = new CopyListRandomPointer();
		Node second = new Node(2, null, null);
		second.random = second;
		Node first = new Node(1,second, second);
		c.copyRandomList(first);
	}
    public Node copyRandomList(Node head) {
    	HashMap<Node, Node> map = new HashMap<Node, Node>();
    	
        Node temp = head;
        while(head!=null) {
        	Node newNode = new Node(head.val, head.next,null);
        	System.out.println("Copied node" +  newNode);
        	if(!map.containsKey(head)) {
        		map.put(head, newNode);
        	}
        	head=head.next;
        }
        head = temp;
        while(head!=null) {
        	Node newNode=map.get(head);
        	newNode.random=map.get(head.random);
        	newNode.next = map.get(head.next);
        	head=head.next;
        }
        return map.get(temp);
        
    }
}

class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
    public String toString() {
    	return "val: " + val;
    }
};
