import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author srinivasmaroju
 * https://leetcode.com/problems/top-k-frequent-words/
 * 
 * Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.
Follow up:
Try to solve it in O(n log k) time and O(n) extra space.
 */
public class KMostFrequentWords {

	public static void main(String []args) {
		String[] words = {"alpha", "alphabet","alpine","ali","alpha"};
		Trie trie = new Trie();
		for(String word:words) {
			trie.addWord(word);
		}
		System.out.println(trie);
		trie.deleteWord("ali");
		System.out.println(trie);
		String search = "alpine";
		System.out.println("Searching for word: " + search + " Found: "+ trie.search(search));
	}
}

class TrieNode {
	Map<Character, TrieNode> children;
	boolean endOfWord;
	int frequency;
	public TrieNode() {
		this.endOfWord = false;
		children = new HashMap<Character, TrieNode>();
		frequency = 0;
	}
	public String toString(int level) {
		boolean isTail = children.isEmpty();
		String padding = "";
		String output = "";
		if(isTail) return output;

		for(int i=0;i<level;i++) {
			padding += " ";
		}

		for(Object child:children.keySet().toArray()) {
			output += padding + "└─" + child.toString();
			if(children.get(child).endOfWord) {
				output += "[E]"+"{"+children.get(child).frequency+"}";
			}
			output += "\n";
			output +=  children.get(child).toString(level+1);
		}
        return output;
	}
}

class Trie {
	
	private final TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	private boolean deleteWord(TrieNode current, String word, int index) {
		if(index==word.length()){
			if(current.endOfWord!=true) {
				return false;
			}
			current.endOfWord = false;
			return current.children.size() == 0;
		}
		char c = word.charAt(index);
		TrieNode node = current.children.get(c);
		if(node==null) {
			return false;
		}
		if(deleteWord(node, word, index+1)) {
			current.children.remove(c);
			return current.children.size() == 0;
		}
		return false;
	}
	private boolean searchRecursive(TrieNode node, String word, int index) {
		if(node==null) {
			return false;
		}
		if(index==word.length()) {
			return node.endOfWord;
		}
		char c = word.charAt(index);
		TrieNode child = node.children.get(c);
		return searchRecursive(child, word, index+1);
	}
	public boolean search(String word) {
		return searchRecursive(root, word, 0);
	}
	
	public void deleteWord(String word) {
		deleteWord(root, word, 0);
	}
	
	
	public void addWord(String word) {
		//System.out.println("Adding word: " + word);
		TrieNode current = root;
		for(char c: word.toCharArray()) {
			//System.out.println("Adding char: " + c);
			TrieNode node = current.children.get(c);
			if(node==null) {
				//System.out.println("Adding new child: " + c);
				node = new TrieNode();
				current.children.put(c, node);
			}
			current = node;
		}
		current.frequency++;
		current.endOfWord = true;
	}
	public String toString() {
        return root.toString(1); // assumption: the root is not null;
    }
}



