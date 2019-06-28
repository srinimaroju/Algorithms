import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/3sum/
 * @author Srinivas Maroju
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 * 
 */

public class ThreeSum {
	public static void main(String []args) {
		System.out.println(new ThreeSum().threeSum(new int[] {-1, 0, 1, 2, -1, 4}));
		System.out.println(new ThreeSum().threeSum(new int[] {0,0,0}));
		System.out.println(new ThreeSum().threeSum(new int[] {-2,0,1,1,2}));
	}

	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Arrays.sort(nums);
		
		for(int i=0;i<nums.length-2;i++) {
			int first = nums[i];
			int left = i+1;
			int right = nums.length-1;
			while(left < right) {
				//System.out.println("i:" + i + " left:" + left + " right:" + right);
				int sum = (first + nums[left] + nums[right]) ;
				if(sum == 0) {
					List<Integer> list = Arrays.asList(new Integer[]{first, nums[left], nums[right]});
					if(!result.contains(list)) {
						result.add(list);
					}
					left++; right--;
				} else if(sum > 0){
					right--;
				} else {
					left++;
				}
			}
		}
		return result;
	}
}
