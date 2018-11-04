import java.util.Arrays;

/**
 * 
 * @author srinivasmaroju
 * https://leetcode.com/problems/first-missing-positive/description/
 * Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Note:

Your algorithm should run in O(n) time and uses constant extra space.


 *
 */
public class FirstMissingPostive {
	public static void main(String []args) {
		FirstMissingPostive f = new FirstMissingPostive();

		int[] list4={4,7,1};
		int[] list5={3,2,1};
		int[] list1={1,2,0};
		int[] list2={3,4,-1,1};
		int[] list3={7,8,9,11,12};

		System.out.println(f.firstMissingPositive(list4));
		System.out.println(f.firstMissingPositive(list5));
		System.out.println(f.firstMissingPositive(list1));
		System.out.println(f.firstMissingPositive(list2));
		System.out.println(f.firstMissingPositive(list3));
	}

	public int firstMissingPositive(int[] nums) {
		System.out.println(Arrays.toString(nums));
		if(nums.length==0) return 1;
		int[] hash=new int[nums.length];
		//hash[0] = nums[0];

		for(int i=0;i<nums.length;i++) {
			int num = nums[i];
			if(num<=nums.length && num>0) {
				hash[num-1]++; 
			}
		}
		for(int i=0;i<nums.length;i++) {
			if(hash[i]==0) {
				return i+1;
			}
		}
		return nums.length+1;

	}
}
