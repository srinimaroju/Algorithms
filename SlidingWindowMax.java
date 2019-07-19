import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 * @author Srinivas Maroju
 *
 */
public class SlidingWindowMax {
	public static void main(String []args) {
		SlidingWindowMax sm = new SlidingWindowMax();
		System.out.println(sm.maxSlidingWindow2(new int[]{1,3,-1,-3,5,3,6,7}, 3));
		//System.out.println(sm.maxSlidingWindow2(new int[]{7,2,4}, 2));
	}
	public int[] maxSlidingWindow2(int[] nums, int k) {
        if(nums.length <= 1){
            return nums;
        }
        Deque<Integer> q = new LinkedList<Integer>();
        int n = nums.length;
        int[] res = new int[n-k+1];
        for(int i = 0; i < k; i++){
            while(!q.isEmpty() && nums[i] > nums[(Integer)q.peekLast()]){
                Integer x = q.removeLast();
                System.out.println("removing: " + x);
            }
            q.addLast(i);
        }
        System.out.println(q);
        System.out.println("---");

        int idx = 0;
        for(int j = k; j < n; j++){
            res[idx++] = nums[(Integer)q.peek()];
            while(!q.isEmpty() && (Integer)q.peek() <= j-k){
                q.removeFirst();
            }
            while(!q.isEmpty() && nums[j] > nums[(Integer)q.peekLast()]){
                q.removeLast();
            }
            q.addLast(j);
            System.out.println(q);

        }
        res[idx] = nums[(Integer)q.peek()];
		System.out.println(Arrays.toString(res));
        return res;
    }
	public int[] maxSlidingWindow(int[] nums, int k) {
		int max= Integer.MIN_VALUE;
		
		if(nums.length==0) {
			return new int[0];
		}
		if(nums.length < k || k==1) {
			return nums;
		}
		
		
		int[] result = new int[nums.length-k+1];
		
		LinkedList<Integer> values = new LinkedList<Integer>();

		for(int i=0;i<nums.length;i++) {
			values.add(nums[i]);
			max = Math.max(nums[i], max);
			if(i>=k) {
				 System.out.println("removing: " + nums[i-k]);
				 
			}
			
			while(!values.isEmpty() && nums[i]>values.peek()) {
				 values.remove();
			}
			
			System.out.println(values);
			if((i+1)>=k) {
				result[i+1-k] = max;
				values.removeLast();
			}  
		}
		System.out.println(Arrays.toString(result));
		return result;
	}
}
