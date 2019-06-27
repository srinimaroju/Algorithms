/**
 * @author Srinivas Maroju
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * 
 * Input: "abcabcbb"
 * Output: 3 
 * Explanation: The answer is "abc", with the length of 3.
 * 
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3. 
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */

package String;

import java.util.HashMap;

public class LongestSubstringWithoutRepeat {
	public static void main(String []args) {
		LongestSubstringWithoutRepeat lcs = new LongestSubstringWithoutRepeat();
		System.out.println(lcs.lengthOfLongestSubstring("abcabcbb"));
		System.out.println(lcs.lengthOfLongestSubstring("bbbbb"));
		System.out.println(lcs.lengthOfLongestSubstring("pwwkew"));
	}
	public int lengthOfLongestSubstring(String s) {
		//System.out.println(input);
		HashMap <Character, Integer> bufferMap = new HashMap<>();
		int current_lcs = 0; //Stores current LCS 
		int max_lcs = 0;
		int index_start_max_lcs = 0;
		int index_end_max_lcs = 0;
		int index_start_current_lcs = 0;
		int index_end_current_lcs = 0;
		if(s.length()==0) {
			return 0;
		}

		for(int i=0,length=s.length(); i<length; i++){
			Character c = (Character)s.charAt(i);

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

		return s.substring(index_start_max_lcs, index_end_max_lcs+1).length();
	}
}
