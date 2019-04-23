import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class KMostFrequentHeap {

	public static void main(String []args) {
		
		String []input  = {"hi", "the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
		KMostFrequentHeap kmf = new KMostFrequentHeap();
		System.out.println(kmf.kMostFrequentWords(input, 4));
	}
	
	public String[] kMostFrequentWords(String[] input, int k) {
		HashMap<String, Integer> map = new HashMap<String,Integer>(k);
		for(String word: input) {
			int count = 1;
			if(map.containsKey(word)) {
				count = map.get(word) + 1;
			}
			map.put(word, count);
		}
		PriorityQueue<String> pq = new PriorityQueue<String>(k,
				new Comparator<String>() {
					public int compare(String w1, String w2) {
						int count1 = map.get(w1);
						int count2 = map.get(w2);
						if(map.get(w1) == map.get(w2)) {
							return w1.compareTo(w2);
						} else {
							return new Integer(count1).compareTo(new Integer(count2));
						}
					}
		});
		for(String word:map.keySet()) {
			if(pq.size()==k) {
				pq.poll();
			}
			pq.add(word);
		}
		//System.out.println(pq);
		ArrayList<String> output = new ArrayList<String>(k);
		while(!pq.isEmpty()) {
			output.add(pq.poll());
		}
		Collections.reverse(output);
		//System.out.println(output);
		String[] result = new String[k];
		for(int i=0;i<output.size();i++) {
			result[i] = output.get(i);
		}
		//System.out.println(Arrays.toString(result));
		return result;
	}
}
