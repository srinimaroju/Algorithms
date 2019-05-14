package Sorting;

import java.util.Arrays;

public class InsertionSort {
	int[] array;
	public static void main(String []args) {
		int[] unsorted = {22,1,34,38,45,129,2,13};
		InsertionSort is = new InsertionSort(unsorted);
	    is.sort();
	}
	public void sort() {
		int length = this.array.length;
		System.out.println("Before Sorting -- " + Arrays.toString(this.array));

		for(int i=0;i<length;i++) {
			for(int j=i;j>0;j--) {
				if(this.array[j-1] > this.array[j]) {
					//Swap
					int temp = this.array[j-1];
					this.array[j-1] = this.array[j];
					this.array[j] = temp;
				}
			}
		}
		System.out.println("After Sorting  -- " + Arrays.toString(this.array));
	}
	public InsertionSort(int[] array) {
		this.array = array;
	}
}
