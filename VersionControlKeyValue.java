import java.util.*;

/*
 */


//  set(a,b)  set(x,y)   set(e,f)   set(a,c)    delete(a) delete(e)  set(a,d)
//    10        20          30        40          50        60          70
//  Operations are happening after time 70

// get(a) -> d, get(e) -> null, get(x) -> y, size() -> 2
// get(a, 0) -> null, get(a, 10) -> b, get(a, 25) -> b, get(a, 45) -> c, get(a, 55) -> null, get(a, 100) -> d
// get(x, 10) -> null, get(x, 50) -> y, get(e, 25) -> null, get(e, 55) -> f, get(e, 100) -> null

// System.nanoTime() -> Long (to grab current time)

class VersionControlKeyValue<K, V> implements KeyValueStoreInterface<K, V> {
	public static void main(String[] args) {
		VersionControlKeyValue<String, String> kvs = new VersionControlKeyValue<String, String>();
		kvs.set("key1","value1");
		kvs.set("key2","value2");
		System.out.println("Size is: " + kvs.size()); // 2

		System.out.println(kvs.get("key1"));  //Should print value1
		System.out.println(kvs.delete("key1")); //Should print value1 and delete

		System.out.println("Size is: " + kvs.size()); // Should return 1

		boolean nullValue = (kvs.get("invalidkey") == null);
		System.out.println("Value is null " + nullValue); // true

		kvs.set("key3","value3");

		//Delete
		kvs.delete("invalidkey");
		System.out.println("Size is: " + kvs.size()); // Should return 2

		//Overwrite
		kvs.set("key2","value2-updated");
		System.out.println(kvs.get("key2")); //Should return value1-updated
		System.out.println("Size is: " + kvs.size()); // Should return 2
		System.out.println(kvs);
	}
	
	HashMap<K, HashMapItem<K,V>> data;
	int deletedItems;
	
	public VersionControlKeyValue() {
		data = new HashMap<K, HashMapItem<K,V>>();
		deletedItems = 0;
	}

	// Return null if doesn't exist
	public V get(K key) {
		HashMapItem<K, V> existingItem = data.get(key);
		if(existingItem==null) {
			return null;
		} else {
			while(existingItem.next!=null){
				existingItem = existingItem.next;
			} 
			return existingItem.value;
		}
	}

	public HashMapItem<K, V> getValue(K key) {
		HashMapItem<K, V> existingItem = data.get(key);
		if(existingItem==null) {
			return null;
		} else {
			while(existingItem.next!=null){
				existingItem = existingItem.next;
			} 
			return existingItem;
		}
	}
	public V get(K key, Long time) {
		HashMapItem<K, V> existingItem = data.get(key);
		if(existingItem==null) {
			return null;
		} else {
			while(existingItem.next!=null){
				if(existingItem.timestamp > time) {
					if(existingItem.prev==null) {
						return null;
					}
					return (V) existingItem.prev.value;
				} else if(existingItem.timestamp == time) {
					return existingItem.value;
				} 
			}
		}
		return existingItem.value;
	}

	// If the key already exists, overwrite previous key
	public void set(K key, V value) {
		//not yet set
		HashMapItem<K, V> existingItem = this.getValue(key);
		if(existingItem==null) {
			HashMapItem<K,V> item = new HashMapItem<K,V>(key, value);
			data.put(key, item);
		} else {
			HashMapItem<K,V> item = new HashMapItem<K,V>(key, value);
			while(existingItem.next!=null){
				existingItem = existingItem.next;
			} 
			if(existingItem.isDeleted) {
				deletedItems--;
			}
			existingItem.next = item;
			item.prev = existingItem;
		}
	}

	// Returns deleted key
	public V delete(K key) {
		HashMapItem<K, V> item = this.getValue(key);
		if (item == null || item.isDeleted) {
			return null;
		}
		deletedItems++;
		item.isDeleted = true;
		return item.value;
	}
	
	public int size() {
		return this.data.size() - deletedItems;
	}
	
	@Override
	public String toString() {
		StringBuffer output = new StringBuffer("");
		data.keySet();
		for(K key: data.keySet()) {
			HashMapItem<K,V> item = data.get(key);
			if(item!=null) {
				output.append(item.toString() + " --> ");
				while(item.next!=null) {
					output.append(item.toString() + " --> ");
					item = item.next;
				}
			}
			output.append("\n");
		}
		return output.toString();
	}
}

class HashMapItem<K,V> {
	K key;
	V value;
	Long timestamp;
	public HashMapItem<K, V> prev;
	public HashMapItem<K, V> next;
	public boolean isDeleted;
	public HashMapItem(K key, V value) {
		this.key = key;
		this.value = value;
		this.timestamp = System.nanoTime();
		isDeleted = false;
	}
	public String toString() {
		return "[Key:" + this.key + ",Value:" + this.value + ",Time:" + this.timestamp+"]";
	}
}

interface KeyValueStoreInterface<K,V> {
	public V get(K key);
	public void set(K Key, V Value);
	public V delete(K key);
	public int size();
	//public V search(K key)
}

class KeyValueStore<K, V> implements KeyValueStoreInterface<K, V> {
	private HashMap<K,V> data;
	public KeyValueStore() {
		data = new HashMap<K,V>();
	}

	// Return null if doesn't exist
	public V get(K key) {
		return data.get(key);
	}

	// If the key already exists, overwrite previous key
	public void set(K key, V value) {
		data.put(key, value);
	}

	// Returns deleted key
	public V delete(K key) {
		return data.remove(key);
	}
	public int size() {
		return data.size();
	}
}
