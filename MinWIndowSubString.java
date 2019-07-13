import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 * @author Srinivas Maroju
 *
 */
public class MinWIndowSubString {
	public static void main(String []args) {
		MinWIndowSubString ms = new MinWIndowSubString();
		System.out.println(ms.minWindow("ADOBECODEBBANC", "ABC"));
		
	}
	public String minWindow(String s, String t) {
		
		String result = "";
		
		if(s.length()==0 || t.length()==0) {
			return result;
		}
		
		HashMap<Character, Integer> query  =  new HashMap<Character, Integer>();
		HashMap<Character, Integer> window  = new HashMap<Character, Integer>();

		int left =0 , right =0;
		int required = t.length();
		int found = 0;

		int minIndex = -1;
		int minRange = Integer.MAX_VALUE;

		query = this.calculateQueryHash(t);
		required = query.size();

		//System.out.println("Length of string is: " + s.length());
		do {
			/*
			System.out.println("left: " + left 
					+ " right: " + right
					+ " Required: " + required + 
					 " found: " + found +
					 " query: " + query +
					 " window: " + window);
			*/
			if(required == found) {				
				Character l = s.charAt(left);
				if(right-left < minRange) {
					minRange = right-left;
					minIndex = left;
				}
				left++;

				if(query.containsKey(l)) {
					window.put(l,  window.getOrDefault(l, 0) - 1);
					if(query.get(l) > window.get(l)) {
						found--;
					}
				}
			}
			else {
				
				if(right>=s.length()) {
					break;
				}
			
				Character c = s.charAt(right);
				if(query.containsKey(c)) {
					window.put(c,  window.getOrDefault(c, 0) + 1);
					if((int)query.get(c) == (int)window.get(c)) {
						found++;
					}
				}
				right++;
			}
		} while(right <= (s.length())) ;
		//System.out.println("Minindex: " + minIndex + " MinRange: " + minRange); 
		if(minIndex!=-1) {
			result = s.substring(minIndex, minRange+minIndex);
		}
		return result;
	}
	public HashMap<Character, Integer> calculateQueryHash(String s) {
		HashMap<Character, Integer> query  =  new HashMap<Character, Integer>();
		for(int i=0;i<s.length();i++) {
			query.put(s.charAt(i),  query.getOrDefault(s.charAt(i), 0) + 1);
		}
		//System.out.println("Query Hash is: " + query);
		return query;
	}
}
