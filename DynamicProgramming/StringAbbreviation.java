package DynamicProgramming;

import java.io.*;
import java.util.Scanner;


/**
 * https://www.hackerrank.com/challenges/abbr/problem
 * @author Srinivas Maroju
 *
 */
public class StringAbbreviation {
	public static int[][] cache;

	// Complete the abbreviation function below.
	static String abbreviation(String a, String b) {
		//String key = a+"+"+b;
	
		if(cache[a.length()][b.length()]!=0) {
			return cache[a.length()][b.length()]==1 ?  "YES": "NO";
		}
		
		String result = null;
		//System.out.println("calc abb for " + a + ":" + b);

		//Base Case 1
		if(a.equalsIgnoreCase(b)) result = "YES";

		//Base Case 2
		if(a.equals("")) {
			result = "NO";
		}

		//Base Case 3
		else if(b.equals("")) {
			for(char ch: a.toCharArray()) {
				if(!Character.isLowerCase(ch)) {
					result = "NO"; 
					break;
				}
			}
			if(result==null) {
				result = "YES";
			}
		}

		if(result==null) {
			//Recursion 1 - If both characters are equal - Move forward
			if(a.charAt(0) == b.charAt(0)) {
				result = abbreviation(a.substring(1), b.substring(1));
			}
			//Recursion 2 - Not equal and Not a lower case character
			else if(!Character.isLowerCase(a.charAt(0))) {
				result = "NO";
			}
			// Recursion 3 - Not Equal, but a lower case character
			else {
				if(
					(Character.toUpperCase(a.charAt(0)) == b.charAt(0))
						&& 
					(abbreviation(a.substring(1), b.substring(1)) == "YES")) {
					result = "YES";
				} else {
					result = abbreviation(a.substring(1), b);
				}
			}
		}

		cache[a.length()][b.length()] = result=="YES"? 1:-1;
		return result;

	}

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
		final Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "/resources/TextAbbreviationTest.txt"));

		int q = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		//long Totalstart = System.nanoTime();
		for (int qItr = 0; qItr < q; qItr++) {
			String a = scanner.nextLine();

			String b = scanner.nextLine();
			//long start = System.nanoTime();
			//System.out.println("Lenght of --" + a + "-- is:" + a.length() + a.charAt(a.length()-1));
			cache = new int[a.length()+1][b.length()+1];
			String result = abbreviation(a, b);
			
			bufferedWriter.write(result);
			bufferedWriter.newLine();
		}
		//System.out.println("Time taken: " + ((System.nanoTime()-Totalstart)/1000000.0) + "ms");

		bufferedWriter.close();

		scanner.close();
	}
}
