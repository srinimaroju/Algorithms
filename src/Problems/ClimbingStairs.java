/**
 * 
 * @author srinivasmaroju
 * https://leetcode.com/problems/climbing-stairs/description/
 * You are climbing a stair case. It takes n steps to reach to the top.

	Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
	
	Note: Given n will be a positive integer.
	
	Example 1:
	
	Input: 2
	Output: 2
	Explanation: There are two ways to climb to the top.
	1. 1 step + 1 step
	2. 2 steps
	Example 2:
	
	Input: 3
	Output: 3
	Explanation: There are three ways to climb to the top.
	1. 1 step + 1 step + 1 step
	2. 1 step + 2 steps
	3. 2 steps + 1 step
 *
 */
public class ClimbingStairs {
	public static void main(String []args) {
		ClimbingStairs cs = new ClimbingStairs();
		for(int i=9;i<10;i++) {
			System.out.println("Stairs for n: " + i +" is:  " + cs.climbStairs(i));
		}
		
		
	}
	public int climbStairs(int n) {
		if(n==1 || n==2 || n==3) { return n; }
		int []memory = new int[n];
		for(int i=0;i<n;i++) {
			memory[i] = -1;
		}
		memory[0]=1; memory[1]=2; memory[2]=3;
		return recursiveStairs(n, memory);
	}
	public int recursiveStairs(int n, int[] memory) {
		if(memory[n-1]!=-1) {
			System.out.println("Alread in memory  "+ (n-1));
			return memory[n-1];
		}
		memory[n-1] =  recursiveStairs(n-1, memory) + recursiveStairs(n-2, memory);
		return memory[n-1];
	}
}
