package DynamicProgramming;


/**
 * https://www.hackerrank.com/challenges/candies/problem
 * @author Srinivas Maroju
 *
 */import java.io.*;
 import java.util.*;
 public class Candies {

	 // Complete the candies function below.
	 static long candies(int n, int[] arr) {
		 long candies = 0;
		 int lastScore = 0;
		 int lastCandies = 0;
		 int peak = 0;
		 int peakCandies =0;
		 int[] candies_arr = new int[n];

		 for(int i=0;i<n;i++) {
			 int score = arr[i];
			 if(score>lastScore) {
				 candies += lastCandies+1;
				 lastCandies = lastCandies+1;
				 peak = i;
				 peakCandies = lastCandies;
			 } else if(score == lastScore) {
				 //assign min candies
				 candies += 1;
				 lastCandies = 1;


				 if(arr[peak] == score) { 
					 System.out.println("moving peak"); 
					 peak = i;
					 peakCandies = lastCandies; 
				 }


			 } else {
				 candies += 1;
				 lastCandies = 1;
				 //candies_arr[i] = lastCandies;
				 //Back Track
				 for(int j=(i-1); j>peak; j--) {
					 if( (candies_arr[j] <= candies_arr[j+1])
							 && (arr[j] > arr[j+1])
							 ) {
						 candies_arr[j]++;
					  } 
				 }
				 int distance = (i-1-peak);
				 int j = peak;
				 if( 
						 (candies_arr[j] <= candies_arr[j+1])
							  &&
							  (arr[j] > arr[j+1])
							 // &&
							 // (peakCandies<distance+1)
							 ) {
				
						 candies_arr[j]++;
						 peakCandies = candies_arr[j];
					 } 
				 
				 candies += distance;
				 if(peakCandies <= distance+1 ) {
					 candies++;
					// peakCandies++;
				 }


			 }
			 candies_arr[i] = lastCandies;
			 lastScore = score;
		 }
		 long sum =0;
		 for(int i=0;i<n;i++) {
			 sum+= candies_arr[i];
		 }
		 System.out.println(Arrays.toString(candies_arr));
		 System.out.println("Sum, "+ sum);
		 return candies;
	 }

	 private static Scanner scanner;

	 public static void main(String[] args) throws IOException {
		 System.out.println(System.getProperty("user.dir"));
		 scanner = new Scanner(new File(System.getProperty("user.dir") + "/resources/TestCandies.txt"));
		 BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

		 int n = scanner.nextInt();
		 scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		 int[] arr = new int[n];

		 for (int i = 0; i < n; i++) {
			 int arrItem = scanner.nextInt();
			 scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			 arr[i] = arrItem;
		 }

		 long result = candies(n, arr);

		 bufferedWriter.write(String.valueOf(result));
		 bufferedWriter.newLine();

		 bufferedWriter.close();

		 scanner.close();
	 }
 }

