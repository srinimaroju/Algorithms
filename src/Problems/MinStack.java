/**
 * 
 * @author srinivasmaroju
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
https://leetcode.com/problems/min-stack/description/
 *
 */
import java.util.Stack;
class MinStack {
	Stack<Integer> values, minStack;

	
	public static void main (String []args) {
		 MinStack obj = new MinStack();
		 obj.push(0);
		 obj.push(1);
		 obj.push(0);
		 
		 System.out.println(obj.getMin());
		 obj.pop();
		 System.out.println(obj.getMin());
		/*
		 obj.pop();
		 System.out.println(obj.getMin());
		 obj.pop();
		 System.out.println(obj.getMin());
		 obj.pop();
		 System.out.println(obj.getMin());
		 obj.pop();
		 */
	}

	/** initialize your data structure here. */
	public MinStack() {
		values = new Stack<Integer>();
		minStack = new Stack<Integer>();
	}

	public void push(int x) {
		values.push(x);
		if(minStack.isEmpty()) {
			minStack.push(x);
		}
		else if(x <= ((int)minStack.peek())) {
			minStack.push(x);
		}
	}

	public void pop() {
		int x = values.pop();
		if(x==minStack.peek()) {
			minStack.pop();
		}
	}

	public int top() {
		return values.peek();
	}

	public int getMin() {
		return minStack.peek();
	}
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */