package Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author Srinivas Maroju
 *
 */

public class SortStack {
	public static void main(String []args) {
		Stack<Integer> s = new Stack<Integer>();
		int[] input = new int[]{20,40,32,88,3,12,9};
		for(int i:input) {
			s.push((Integer)i);
		}
		System.out.println("====== Before Sort ==============");
		System.out.println(s);
		new SortStack().sort(s);
		System.out.println("====== After Sort ==============");

		System.out.println(s);

	}
	public void sort(Stack<Integer> s) {
		Stack<Integer> s2 = new Stack<Integer>();
		int i =0;
		while(!s.empty()) {
			int tmp = s.pop();
			while(!s2.empty() && s2.peek() > tmp) {
				s.push(s2.pop());
			}
			s2.push(tmp);
		}
		while(!s2.empty()) {
			s.push(s2.pop());
		}
	}
}
