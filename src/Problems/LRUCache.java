import java.util.Collections;
import java.util.HashMap;


/***
 * @author srinivasmaroju
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 // capacity );


cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
*/

class LRUCache {
	public static void main(String []args) {
		int capacity = 2;
		LRUCache cache = new LRUCache(capacity);
		/*
		cache.put(1, 1);
		cache.put(2, 2);
		cache.get(1);       // returns 1
		cache.put(3, 3);    // evicts key 2
		cache.get(2);       // returns -1 (not found)
		cache.put(4, 4);    // evicts key 1
		cache.get(1);       // returns -1 (not found)
		cache.get(3);       // returns 3
		cache.get(4);       // returns 4
		*/
		/*
		
		cache.put(2, 1);
		cache.put(2, 2);
		cache.get(2);
		cache.put(1,1);
		cache.put(4,1);
		cache.get(2);
		*/
		LRUCache cache1  = new LRUCache(10);
		cache1.put(10,13);
		cache1.put(3,17);
		cache1.put(6,11);
		cache1.put(10,5);
		cache1.put(9,10);
		cache1.get(13);
		cache1.put(2,19);
		cache1.get(2);
		cache1.get(3);
		cache1.put(5,25);
		cache1.get(8);
		cache1.put(9,22);
		cache1.put(5,5);
		cache1.put(1,30);
		cache1.get(11);
		cache1.put(9,12);
		cache1.get(7);
		cache1.get(5);
		cache1.get(8);
		cache1.get(9);
		cache1.put(4,30);
		cache1.put(9,3);
		cache1.get(9);
		cache1.get(10);
		cache1.get(10);
		cache1.put(6,14);
		cache1.put(3,1);
		cache1.get(3);
		cache1.put(10,11);
		cache1.get(8);
		cache1.put(2,14);
		cache1.get(1);
		cache1.get(5);
		cache1.get(4);
		cache1.put(11,4);
		cache1.put(12,24);
		cache1.put(5,18);
		cache1.get(13);
		cache1.put(7,23);
		cache1.get(8);
		cache1.get(12);
		cache1.put(3,27);
		cache1.put(2,12);
		cache1.get(5);
		cache1.put(2,9);
		cache1.put(13,4);
		cache1.put(8,18);
		cache1.put(1,7);
		cache1.get(6);
		cache1.put(9,29);
		cache1.put(8,21);
		cache1.get(5);
		cache1.put(6,30);
		cache1.put(1,12);
		cache1.get(10);
		cache1.put(4,15);
		cache1.put(7,22);
		cache1.put(11,26);
		cache1.put(8,17);
		cache1.put(9,29);
		cache1.get(5);
		cache1.put(3,4);
		cache1.put(11,30);
		cache1.get(12);
		cache1.put(4,29);
		cache1.get(3);
		cache1.get(9);
		cache1.get(6);
		cache1.put(3,4);
		cache1.get(1);
		cache1.get(10);
		cache1.put(3,29);
		cache1.put(10,28);
		cache1.put(1,20);
		cache1.put(11,13);
		cache1.get(3);
		cache1.put(3,12);
		cache1.put(3,8);
		cache1.put(10,9);
		cache1.put(3,26);
		cache1.get(8);
		cache1.get(7);
		cache1.get(5);
		cache1.put(13,17);
		cache1.put(2,27);
		cache1.put(11,15);
		cache1.get(12);
		cache1.put(9,19);
		cache1.put(2,15);
		cache1.put(3,16);
		cache1.get(1);
		cache1.put(12,17);
		cache1.put(9,1);
		cache1.put(6,19);
		cache1.get(4);
		cache1.get(5);
		cache1.get(5);
		cache1.put(8,1);
		cache1.put(11,7);
		cache1.put(5,2);
		cache1.put(9,28);
		cache1.get(1);
		cache1.put(2,2);
		cache1.put(7,4);
		cache1.put(4,22);
		cache1.put(7,24);
		cache1.put(9,26);
		cache1.put(13,28);
		cache1.put(11,26);
		
	}
	int capacity;
	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> lru_map = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> lru_count_map = new HashMap<Integer, Integer>();
	int lru=0;
	int current_capacity, mru;
	
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.lru = 1;
        this.mru = 0;
        this.current_capacity = 0;
    }
    
    public int get(int key) {
    	System.out.println("Getting key: " + key  + " with capacity:" + current_capacity + " lru:" + lru + " mru:" + mru);
    	int value = -1;
    	if(map.containsKey(key)) {
    		if(lru_count_map.get(key) != mru) {
    			value =  ((int)map.get(key));
        		mru++;
        		if(lru_count_map.containsKey(key)) {
        			lru_map.remove(lru_count_map.get(key), key);
        			lru_map.put(mru, key);
        		}
        		lru_count_map.put(key, mru);
        		lru++;
    		} else {
    			//mru do nothing
    		}
    		
    	} else {
    		//value =  ((int)map.get(key));
    		//lru_map.put(++mru, key);
    	}
    	System.out.println(Collections.singletonList(map));
    	System.out.println(Collections.singletonList(lru_map));
    	System.out.println(Collections.singletonList(lru_count_map));
    	
    	return value;
    }
    
    public void put(int key, int value) {
    	System.out.println("Setting key:" + key + " value: " + value + " current capacity: " + current_capacity + " lru:" + lru + " mru:" + mru);
    	 if((current_capacity == capacity) && current_capacity!=0 && !map.containsKey(key)) {
        	//Full capacity remove lru element
        	System.out.println("Evicts lru " +lru);
        	int lru_key = lru_map.remove(lru);
        	System.out.println("Evicts key " +lru_key);
        	map.remove(lru_key);
        	lru_count_map.remove(lru_key);
        	lru++;
        	lru_map.put(++mru, key);
        } 
    	else if(map.containsKey(key)) {
    		if(lru_count_map.get(key) == mru) {
    			//Do nothing, already MRU
    		} else {
    			if( lru_map.get(lru) != key) {
        			int lru_key = lru_map.remove(lru);
            		lru++;
        			lru_map.put(lru, lru_key);
            		lru_count_map.put(lru_key, lru);
        		} else
        		{
        			lru++;
        		}
        		lru_map.remove(lru_count_map.get(key));
        		lru_map.put(++mru, key);
    		}
    		
    	}
    	else {
    		lru_map.put(++mru, key);
    		current_capacity++;
    	}

	    lru_count_map.put(key, mru);
    	map.put(key, value);
        System.out.println(Collections.singletonList(map));
    	System.out.println(Collections.singletonList(lru_map));
    	System.out.println(Collections.singletonList(lru_count_map));
       
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */