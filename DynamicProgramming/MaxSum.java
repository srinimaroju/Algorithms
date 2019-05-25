package DynamicProgramming;

/**
 * https://www.hackerrank.com/challenges/max-array-sum/problem
 * @author Srinivas Maroju
 *
 */

public class MaxSum {
	static int maxSubsetSum(int[] arr) {
		int[] maxSubSetSum = new int[arr.length+1];
		for(int i=arr.length-1 ;i>=0; i--) {
			maxSubSetSum[i] = maxSum(arr, maxSubSetSum, i);
		}
		return  maxSubSetSum[0];
	}

	static int maxSum(int[] arr, int[] maxSubSetSum, int start) {
		int result = 0;

		int length = arr.length-start;
		if(length==1) {
			result = arr[start];
		}
		else if(length==2) {
			result = Math.max(arr[start], arr[start+1]);
		}
		else {
			result = Math.max(
					Math.max(arr[start], (arr[start] + maxSubSetSum[start+2])), 
					maxSubSetSum[start+1]
					);
		}
		maxSubSetSum[start] = result;
		return result;
	}

	public static void main(String[] args) {
		int[] arr = {13,2,-15};
		int res = maxSubsetSum(arr);
		System.out.println(String.valueOf(res));
	}
}

