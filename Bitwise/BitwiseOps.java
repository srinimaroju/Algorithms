package Bitwise;

/**
 * To learn about bitwise ops
 * @author Srinivas Maroju
 * Chapter 5: cracking coding interview
 * 
 */
public class BitwiseOps {
	public static void main(String []args) {
		insertion(50,5,1,3);
	}
	/** 5.1
	 *  Insertion: You are given two 32-bit numbers, N and M, and two bit positions, i and
	 *  j. Write a method to insert M into N such that M starts at bit j and ends at bit i. 
	 *  You can assume that the bits j through i have enough space to fit all of M. That is, if
	 *  M = 10011, you can assume that there are at least 5 bits between j and i. You would not, for
	 *  example, have j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2. 
	 */
	public static void insertion(int n, int m, int i, int j) {
		System.out.println("================= Insertion Start==========");
		int allOnes = ~0;
		int left = allOnes << (j+1);
		int right =  (1 << i) - 1;
		int mask = left | right;
		int shift_n = mask & n;
		int shift_m = m << i;
		int result = shift_n | shift_m;
		System.out.println(Integer.toBinaryString(m) + " inserted into " + 
					Integer.toBinaryString(n) + 
					" between bits [" + j + ":" + i + "] " +
					"is " + Integer.toBinaryString(result));


		System.out.println("================= Insertion End ==========");
	}
}
