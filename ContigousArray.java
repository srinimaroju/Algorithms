import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/contiguous-array/
 * @author Srinivas Maroju
 *
 */
public class ContigousArray {
	public static void main(String []args) {
		int[] input = {1,1,0,0,1,1,1,0,0,0};
		ContigousArray ca = new ContigousArray();
		System.out.println(ca.findMaxLength(input));
		System.out.println(ca.findMaxLength(new int[] {0,0,1,0,0,0,1,1}));

	}
	public int findMaxLength(int[] nums) {
		int[] diffs = new int[nums.length];
		int count = 0;
		int max = 0;
		for(int i=0;i<nums.length;i++) {
			if(nums[i]==0) {
				count--;
			} else {
				count++;
			}
			diffs[i] = count;
		}
	
		HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
		map.put(0,-1);
		for(int i=0;i<nums.length;i++) {
			if(map.containsKey(diffs[i])) {
				int index = map.get(diffs[i]);
				if(i-index > max) {
					max = i-index;
				}
			} else {
				map.put(diffs[i], i);
			}
			
		}
		return max;
	}
}
