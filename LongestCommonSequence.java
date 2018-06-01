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
		LongestCommonSequence.printPretty("abcabcbb");
		LongestCommonSequence.printPretty("bbbb");
		LongestCommonSequence.printPretty("pwwkew");
		LongestCommonSequence.printPretty("adbcabacdeffabcz");
	}
	public String calculateLcs(String input) {
		HashMap <Character, Integer> bufferMap = new HashMap<>();
		int current_lcs = 0; //Stores current LCS 
		int max_lcs = 0;
		int index_start_max_lcs_sb = 0;
		int index_end_max_lcs_sb = 0;
		int index_start_current_lcs_sb = 0;
		
		for(int i=0,length=input.length(); i<length; i++){
			Character c = (Character)input.charAt(i);
			if(bufferMap.containsKey(c)) {
				//Repeat..Restart to check for new sequence
				current_lcs = 1;
				index_start_current_lcs_sb = i;
				bufferMap.clear();
				bufferMap.put(c,1);
			} else {
				bufferMap.put(c,1);
				current_lcs++;
				if (current_lcs > max_lcs) {
					 max_lcs = current_lcs;
					 index_start_max_lcs_sb = index_start_current_lcs_sb;
					 index_end_max_lcs_sb = i;
				}
					
			}
		}
		
		return input.substring(index_start_max_lcs_sb, index_end_max_lcs_sb+1);
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
}
