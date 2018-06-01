/*
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 * 
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3. 
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * 
 * Attempted Solution 1
*/
import java.util.HashMap;

public class LongestCommonSequence {
	public static void main(String[] args) {
		LongestCommonSequence.printPretty("abcabcdbb");
		LongestCommonSequence.printPretty("bbbb");
		LongestCommonSequence.printPretty("pwwkew");
		LongestCommonSequence.printPretty("dvdf");
		LongestCommonSequence.printPretty("adbcabacdeffabcz");
		//LongestCommonSequence.printPretty("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ abcdefg..");
	}
	public String calculateLcs(String input) {
		//System.out.println(input);
		HashMap <Character, Integer> bufferMap = new HashMap<>();
		int current_lcs = 0; //Stores current LCS 
		int max_lcs = 0;
		int index_start_max_lcs = 0;
		int index_end_max_lcs = 0;
		int index_start_current_lcs = 0;
		int index_end_current_lcs = 0;
		if(input.length()==0) {
			return "";
		}
		for(int i=0,length=input.length(); i<length; i++){
			Character c = (Character)input.charAt(i);

			if(bufferMap.containsKey(c) && ( bufferMap.get(c) >= index_start_current_lcs )) {
			    int cur_index = bufferMap.get(c);
				current_lcs =  i-cur_index;
				index_start_current_lcs =  cur_index + 1;
				index_end_current_lcs = index_start_current_lcs;
			} else {
				current_lcs++;
			}
			index_end_current_lcs = i;
			bufferMap.put(c,i);
			if (current_lcs > max_lcs) {
				 max_lcs = current_lcs;
				 index_start_max_lcs = index_start_current_lcs;
				 index_end_max_lcs = index_end_current_lcs;
			}	
		}
		
		return input.substring(index_start_max_lcs, index_end_max_lcs+1);
	}
	/*
	 * Helper function to print results
	 */
	public static void printPretty(String input) {
		LongestCommonSequence lcs = new LongestCommonSequence();
		String result = lcs.calculateLcs(input);
		System.out.println("Longest common substring for input --" 
							+ input + "-- is result -- " + result + "--");
	}
	public void printMap(HashMap<Character, Integer> input) {
		for (Object name: input.keySet()) {
            String key = name.toString();
            String value = input.get(name).toString();  
            System.out.println(key + " " + value);  
		} 
	}
}
