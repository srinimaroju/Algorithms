package Problems;

/**
 * Key Value Cache
 * Uses a double linked list with an LRU Policy
 */
import java.util.HashMap;

public class KeyValueCacheLRU<T> {
	HashMap<String, ListItem<T>> data = new HashMap<String, ListItem<T>>();
	private int MAX_SIZE = 10;
	private int size;
	ListItem<T> head;
	ListItem<T> tail;

	public KeyValueCacheLRU(int max_size) {
		head = tail = null;
		this.MAX_SIZE = max_size;
		size = 0;
	}
	public ListItem<T> get(String key) {
		ListItem<T> item =  data.get(key);
		if(item==null) {
			return null;
		}
		if(head==tail) {
			//Only one element
			return item;
		}
		if(item==head) { //Already at the top
			return item;
		}
		
		if(item!=tail) { // Not the item
			ListItem<T> prevItem = item.prev;
			item.next.prev = prevItem;
			prevItem.next = item.next;
		} else {
			item.next.prev = null;
			tail = item.next;
		}
		
		head.next = item;
		item.prev = head;
		head = item;
		return item;
	}
	public void set(String key, T value) {
		if(data.containsKey(key)) { // Already set
			return;
		}
		ListItem<T> item = new ListItem<T>(key, value);
		if(size==MAX_SIZE) {
			String keytoEvict = tail.key;
			data.remove(tail.key);
			tail = tail.next;
			tail.prev.next = null;
			tail.prev = null;
			System.out.println("Max size met:" +  size + ", Evicting key: " + keytoEvict);

			
			size--;
		}
		if(head==null) {
			head = item;
			tail = item;
		} else {
			head.next = item;
			item.prev = head;
			head = item;
		}
		data.put(key, item);
		size++;
	}
	@Override
	public String toString() {
		return "size:" + this.MAX_SIZE + " data: " + data.toString() +
				" head: " + this.head.key + " tail:" + this.tail.key;
	}
	
	public static void main(String []args) {
		KeyValueCacheLRU<Integer> cache = new KeyValueCacheLRU<Integer>(2);
		cache.set("1", new Integer(1));
		System.out.println(cache);
		cache.set("2", new Integer(2));
		System.out.println(cache);
		
		System.out.println(cache.get("1"));       // returns 1
		System.out.println(cache);
		cache.set("3", new Integer(3));    // evicts key 2
		System.out.println(cache);
		System.out.println(cache.get("2"));       // returns null
		cache.set("4", new Integer(4));    // evicts key 1
		System.out.println(cache.get("1"));       // returns null
		System.out.println(cache.get("3"));       // returns 3
		System.out.println(cache.get("4"));       // returns 4
		
	}
}


class ListItem<T>{
	public String key;
	public T value;
	public ListItem<T> prev;
	public ListItem<T> next;
	public ListItem(String key, T value) {
		prev = next = null;
		this.key = key;
		this.value = value;
	}
	@Override
	public String toString() {
		return "data: " + this.value.toString();
	}
 }
