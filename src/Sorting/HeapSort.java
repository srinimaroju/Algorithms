package Sorting;

import java.util.Arrays;

class HeapSort {
  int[] array;
  public static void main(String []args) {
    int[] unsorted = {22,1,34,38,45,129,2,13};
    HeapSort hs = new HeapSort(unsorted);
    hs.sort();
  }
  public HeapSort(int[] array) {
	  this.array = array;
  }
  public void sort() {
	  for(int i=this.array.length/2; i>=0;i--) {
		  this.heapify(i, this.array.length);
	  }
	  System.out.println("Before Sorting -- " + Arrays.toString(this.array));
	  for(int i=0; i<this.array.length;i++) {
		  int max = this.array[0];
		  this.array[0] = this.array[this.array.length-i-1];
		  this.array[this.array.length-i-1] = max;
		  this.heapify(0, this.array.length-i-1);
	  }
	  System.out.println("After Sorting  -- " + Arrays.toString(this.array));
  }
  public void heapify(int index, int length) {
	  if(index>=length) return;
	  
	  int largest = index;

	  int child1 = index*2 + 1;
	  int child2 = index*2 + 2;
	  
	  if(child1 < length && array[index] < array[child1]) {
		  largest = child1;
	  }
	  
	  if(child2 < length && array[child1] < array[child2]) {
		  largest = child2;
	  }
	  
	  //Swap
	  if(index != largest) {
		  int temp = array[index];
		  array[index] = array[largest];
		  array[largest] = temp;
		  //this.printTree();
		  this.heapify(largest, length);
	  } 
  }
  public void printTree() {
	  this.printTree(0, 0);
	  System.out.println("--------------------------");
  }
  private void printTree(int index, int level) {
	  if(index>=this.array.length) {
		  return;
	  }
	  for(int i=0;i<level;i++) {
		  System.out.print("   ");
	  }
	  System.out.println(this.array[index]);
	  this.printTree(index*2+1, level+1);
	  this.printTree(index*2+2, level+1);
  }
}
