
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
