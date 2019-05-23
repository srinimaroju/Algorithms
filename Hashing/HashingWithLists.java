package Hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HashingWithLists {
	List<LinkedList<Item<Integer>>> data;
	static final int SIZE = 10;
	public static void main(String []args) {
		HashingWithLists hashmap = new HashingWithLists();
		hashmap.add(1);
		hashmap.add(11);
		hashmap.add(3);
		hashmap.add(13);
		hashmap.add(3);
		hashmap.add(12);
		System.out.println(hashmap);
	}
	public HashingWithLists() {
		data = new ArrayList<LinkedList<Item<Integer>>>(SIZE);
		for(int i=0;i<SIZE;i++) {
			data.add(i, null);
		}
	}
	public void add(int item) {
		int hash = hashcode(item);
		Item<Integer> newItem = new Item<Integer>(item, new Integer(item));
		if(data.get(hash) == null) {
			LinkedList<Item<Integer>> itemList = new LinkedList<Item<Integer>>();
			itemList.add(newItem);
			data.add(hash, itemList);
		} else {
			LinkedList<Item<Integer>> itemList = data.get(hash);
			if(itemList.contains(newItem)) {
				//Already exists
			} else {
				itemList.add(newItem);
			}
		}
		System.out.println("Added item" + item);
	}
	public static int hashcode(int item) {
		System.out.println("has for " + item  + " is " + item%SIZE);
		return item%SIZE;
	}
	
	@Override
	public String toString() {
		StringBuffer output = new StringBuffer("");
		for(int i=0;i<data.size();i++) {
			if(data.get(i)!=null) {
				output.append(i + ": " + Arrays.toString(data.get(i).toArray()) + "\n");
			}
		}
		
		return output.toString();
	}
	public int get(int item) {
		int hash = hashcode(item);
		if(data.get(hash)!=null) {
			LinkedList<Item<Integer>> itemList =  data.get(hash);
			for(Item<Integer> listitem:itemList) {
				if(listitem.key==item) {
					return item;
				}
			}
		}
		return 0;
	}
}


class Item<T> {
	public int key;
	public T value;
	public Item(int key, T value) {
		this.key = key;
		this.value = value;
	}
	public String toString() {
		return "[" + key + ":" + value + "]";
	}
}
