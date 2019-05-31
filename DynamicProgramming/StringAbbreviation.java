package DynamicProgramming;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;


/**
 * https://www.hackerrank.com/challenges/abbr/problem
 * @author Srinivas Maroju
 *
 */
public class StringAbbreviation {
	public static HashMap<String, String> cache;


	// Complete the abbreviation function below.
	static String abbreviation(String a, String b) {
		String key = a+"+"+b;
		String result = null;
		if(cache==null) {
			cache = new HashMap<String, String>();
		}
		else {
			if(cache.containsKey(key)) {
				return cache.get(key);
			}
		}

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
			if(a.charAt(a.length()-1) == b.charAt(b.length()-1)) {
				result = abbreviation(a.substring(0,a.length()-1), b.substring(0,b.length()-1));
			}
			//Recursion 2 - Not equal and Not a lower case character
			else if(!Character.isLowerCase(a.charAt(a.length()-1))) {
				result = "NO";
			}
			// Recursion 3 - Lower case character
			else {
				if(Character.toUpperCase(a.charAt(a.length()-1)) == b.charAt(b.length()-1)) {
					if( abbreviation(a.substring(0,a.length()-1), b) == "YES"  ||
							abbreviation(a.substring(0,a.length()-1), b.substring(0,b.length()-1)) == "YES"
							) {
						result = "YES";
					} else {
						result = "NO";
					}
				} else {
					result = abbreviation(a.substring(0,a.length()-1), b);
				}
			}
		}

		cache.put(key, result);
		return result;

	}

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
		final Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "/resources/TextAbbreviationTest.txt"));

		int q = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		long Totalstart = System.nanoTime();
		for (int qItr = 0; qItr < q; qItr++) {
			String a = scanner.nextLine();

			String b = scanner.nextLine();
			long start = System.nanoTime();
			//System.out.println("Lenght of --" + a + "-- is:" + a.length() + a.charAt(a.length()-1));

			String result = abbreviation(a, b);
			
			//System.out.println("TIme taken: " + (System.nanoTime()-start) + "ns");
			//System.out.println("Case " +  (qItr+1) + "------------");
			cache = null;
			bufferedWriter.write(result);
			bufferedWriter.newLine();
		}
		System.out.println("Time taken: " + ((System.nanoTime()-Totalstart)/1000000.0) + "ms");

		bufferedWriter.close();

		scanner.close();
	}
}
