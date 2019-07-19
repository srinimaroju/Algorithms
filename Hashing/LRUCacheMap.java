package Hashing;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheMap {
	public static void main(String []args) {
		KeyValueCache<Integer, Integer> cache = new KeyValueCache<Integer, Integer>();
		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));       // returns 1
		cache.put(3, 3);    // evicts key 2
		System.out.println(cache.get(2));       // returns -1 (not found)
		cache.put(4, 4);    // evicts key 1
		System.out.println(cache.get(1));       // returns -1 (not found)
		System.out.println(cache.get(3));       // returns 3
		System.out.println(cache.get(4));       // returns 4
	}
}

class KeyValueCache<K, V> extends LinkedHashMap<K, V> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int MAX_ENTRIES;
	public KeyValueCache() {
		super(10, 0.75f, true);
		MAX_ENTRIES = 2;
	}
	public V get(Object k) {
		return super.get(k);
	}
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
	        return size() > MAX_ENTRIES;
	}
	 
}