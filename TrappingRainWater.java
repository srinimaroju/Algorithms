import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 * @author Srinivas Maroju
 *
 */
public class TrappingRainWater {
	public static void main(String []args) {
		TrappingRainWater trw = new TrappingRainWater();
		System.out.println("Result is " + trw.trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
		System.out.println("Result is " + trw.trap(new int[]{0,2,0}));
		System.out.println("Result is " + trw.trap(new int[]{2,0,2}));
		System.out.println("Result is " + trw.trap(new int[]{}));
		System.out.println("Result is " + trw.trap(new int[]{9,1,0}));

		System.out.println("Result is " + trw.trap(new int[]{4,2,0,3,5}));

		System.out.println("Result is " + trw.trap(new int[]{4,3,3,9,3,0,9,2,8,3}));
		System.out.println("Result is " + trw.trap(new int[]{9,6,8,8,5,6,3}));
		System.out.println("Result is " + trw.trap(new int[]{5,2,1,2,1,5}));

	}
	public int trap(int []input) {
		int result = 0;
		int size = input.length;
		if(size<2) {
			return 0;
		}
		int[] left_max = new int[size];
		int[] right_max = new int[size];
		left_max[0] = input[0];
		for(int i=1;i<size;i++) {
			left_max[i] = Math.max(left_max[i-1], input[i]);
		}
		//System.out.println(Arrays.toString(left_max));
		right_max[size-1] = input[size-1];
		for(int i=size-2;i>0;i--) {
			right_max[i] = Math.max(right_max[i+1], input[i]);
		}	
		//System.out.println(Arrays.toString(right_max));

		for(int i=0;i<size;i++) {
			int diff = Math.min(left_max[i],right_max[i]);
			if(diff>input[i]) 
				result += diff - input[i];
		}
		return result;
	}
	
}


