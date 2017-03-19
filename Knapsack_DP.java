//This is the java program to implement the knapsack problem using Dynamic Programming
import java.util.Arrays;
 
public class Knapsack_DP 
{
    static int max(int a, int b) 
    { 
        return (a > b)? a : b; 
    }
    static int knapSack(int W, int wt[], int val[], int n)
    {
        int i, w;
        int [][]K = new int[n+1][W+1];
 
	   // Build table K[][] in bottom up manner
        for (i = 0; i <= n; i++)
        {
            for (w = 0; w <= W; w++)
            {
                if (i==0 || w==0)
                    K[i][w] = 0;
                else if (wt[i-1] <= w)
                    K[i][w] = max(val[i-1] + K[i-1][w-wt[i-1]],  K[i-1][w]);
                else
                    K[i][w] = K[i-1][w];
            }
        }
        System.out.print(Arrays.deepToString(K));
        return K[n][W];
    }
 
    public static void main(String args[])
    {

        int []wt = new int[]{2,1,4,6,8,10};
 
        int []val = wt;
        
        int W = 10;
        int result = knapSack(W, wt, val, wt.length);
        
        System.out.println("The maximum value that can be put in a knapsack of capacity W is: " + result);
        
    }
}