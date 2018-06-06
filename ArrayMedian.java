/**
 * @author srinivasmaroju
 * 
 * https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 * 
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * 
 * Example 1:
 * 		nums1 = [1, 3]
 * 		nums2 = [2]
 * 		The median is 2.0
 * 
 * Example 2:
 * 		nums1 = [1, 2]
 * 		nums2 = [3, 4]
 * 	
 * 		The median is (2 + 3)/2 = 2.5
 *
 */

public class ArrayMedian {
	int debugBreak;
	public ArrayMedian() {
		this.debugBreak=0;
	}
	public static void main(String []args) {
		//int[] array1 = {1,7,13,16,17,24};
		//int[] array2 = {2,3,4,7,19,21,22,39,45, 61,65};
		
		int[] array1 = {20,22,23};
		int[] array2 = {7,19,21,24,26,28};
		
		ArrayMedian am = new ArrayMedian();
		float result = am.calculateMedian(array1, array2);
		System.out.println("Median is " + result);
	}
	public void printDebug(int[] list1, int[] list2,int offset1, int length1,int offset2, int length2) {
		System.out.println("Calculating median for--");
		System.out.print("start: "+ offset1 + " end:" + length1 + " [");
		for(int i=offset1;i<=length1;i++) {
			System.out.print(list1[i]);
			if(i<=length1-1) {
				System.out.print(",");
			}
		}
		System.out.print("] -- ");
		System.out.print("start: "+ offset2 + " end:" + length2 + " [");
		for(int i=offset2;i<=length2;i++) {
			System.out.print(list2[i]);
			if(i<=length2-1) {
				System.out.print(",");
			}
		}
		System.out.print("]");
		System.out.println();
	}
	public float calculateMedian(int[] list1, int[] list2) {
		int length1=list1.length;
		int length2=list2.length;
		
		if(length1==0 && length2==0) {
			return 0;
		} 
		else if(length1==0) {
			return this.calcMedian(list2, 0, length2-1);
		} else if(length2 == 0) {
			return this.calcMedian(list1, 0, length1-1);
		}
		
		return this.calculateMedianRecursive(list1, list2, 0, list1.length-1, 0, list2.length-1);
	}
	public float calculateMedianRecursive(int[] list1, int[] list2,int start1, int end1,int start2, int end2) {
		while((this.debugBreak++) <20) {
			int length1=end1-start1+1;
			int length2=end2-start2+1;
		
			if(length1==0 && length2==0) {
				return 0;
			} 
			//First array is empty
			else if(length1==0) {
				return this.calcMedian(list2, start2, end2);
			} else if(length2 == 0) {
				return this.calcMedian(list1, start1, end1);
			}
			
			this.printDebug(list1, list2,start1, end1, start2, end2);
			//1-100 1000-2000
			if((list1[end1] < list2[start2]) && (length1 < length2)) {
				System.out.println("List1 < List 2, length(list1) < length(list2)");
				//Median definitely in second array, offset by length1
				int middle = start2+length1;
				return this.calcMedian(list2, middle, end2);
			} 
			//1-1000 1100-1200
			else if((list1[end1] < list2[start2]) && (length1 > length2)) {
				//Median in first array, offset by length2
				int middle = start1+length2;
				System.out.println("List1 < List 2, length(list1) > length(list2)");
				return this.calcMedian(list1, middle, end1);
			}
			// 1000-2000 1-100
			else if((list1[start1] > list2[end2]) && (length1 > length2)) {
				System.out.println("List1 > List 2, length(list1) > length(list2)");
				//Median definitely in first array, offset by length2
				int middle = start1+length2;
				return this.calcMedian(list1, middle, end1);	
			} 
			//1000-1100 1-999
			else if((list1[start1] > list2[end2]) && (length1 < length2)) {
				System.out.println("List1 > List 2, length(list1) < length(list2)");
				//Median in second arr, offset by length1
				int middle = start2+length1;
				return this.calcMedian(list2, middle, end2-1);
			}
			
			int center1 = (start1+end1)/2;
			int center2 = (start2+end2)/2;
			
			//Only one element in each array - break recursion
			if(length1==1 && length2==1) {
				return ((float)list1[start1]+(float)list2[start2])/2;
			} 
			//First array has one element, second has 2
			// {16}, {7,19} 
			else if(length1==1 && length2==2) {
				return list1[start1];
			}
			// {16,19}, {17}
			else if(length1==2 && length2==1) {
				return list2[start2];
			}
			//Second array has one element, first has 2
			
			//Equal centers case
			if(list1[center1] == list2[center2]) {
				if((length1+length2)/2 == 0) {
					if(list1[center1+1]<list2[center2+1]){
						return (list1[center1] + list1[center1]+1)/2;
					} else {
						return (list2[center2] + list2[center2]+1)/2;
					}
					
				} else {
					return list1[center1];
				}
			} else if(list1[center1] > list2[center2]) {
				if( length1 < length2 ) {
					//move list1 to left
					//or
					//move list2 to right
					System.out.println("Moving list1 to left start:" + start1 + " end:" + center1 + ", list 2 to right, l1<l2");
					float result= this.calculateMedianRecursive(list1, list2, start1, center1+1, center2+1,end2);
					System.out.println("Returning result "+ result);
					return result;
				} else if( length1 > length2 )  {
					//move list1 to left
					//or 
					//move list2 to right
					System.out.println("Moving list1 to left, list 2 to right, l1>l2");
					float result =  this.calculateMedianRecursive(list1, list2, start1, center1, center2,end2);
					System.out.println("Returning result "+ result);
					return result;
				} else {
					//lengths are equal
					if(length1%2!=0) {
						//odd
						return (list1[center1] + list2[center2])/2;
					} else {
						int min = (list1[center1+1] > list2[center2+1]) ? list2[center2+1]:list1[center1+1];
						return ((float)list1[center1] + min)/2;
					}
				}
			} else if(list2[center2] > list1[center1]) {
				if(length1 > length2) {
					//move list1 to right
					//or
					//move list2 to left
					System.out.println("Moving list1 to right, list 2 to left, l1>l2");
					return this.calculateMedianRecursive(list1, list2, center1+1, end1, start2, center2);
				} else if(length2 > length1) {
					//move list1 to right
					//or 
					//move list2 to left
					System.out.println("Moving list1 to right, list 2 to left, l1<l2");
					return this.calculateMedianRecursive(list1, list2, center1, end1, start2, center2);
				} else {
					//lengths are equal
					if(length1%2!=0) {
						//odd
						return ((float)list1[center1] + list2[center2])/2;
					} else {
						int min = (list1[center1+1] > list2[center2+1]) ? list2[center2+1]:list1[center1+1];
						return ((float)list2[center2] + min)/2;
					}
				}
			}
		}
		return 0;
	}
	/**
	 * Helper function
	 * @param list
	 * @param start
	 * @param end
	 * @returns Median for a single Array
	 */
	public float calcMedian(int[]list, int start, int end) {
		System.out.println("Calculating median for start:" + start + "-- end:" + end + "--");
		
		System.out.print("[");
		float result;
		for(int i=start;i<=end;i++) {
			System.out.print(list[i]);
			if(i<end) {
				System.out.print(",");
			}
		}
		
		System.out.println("]");
		
		if(end==0) {
			result = 0;
		}
		if(start==end) {
			return list[start];
		} else if(start==end-1){
			//only 1 element
			result = (list[start]+list[end])/2;
		} else {
			if((end-start)%2!=0) {
				//even
				result = ((float)
							(
								list[(end-start/2)]+list[(end-start/2)+1]
							)
						)/2;
			} else {
				//odd
				result = list[(end-start)/2];
			}
		}
		System.out.println("Returning result " + result);
		return result;
	}
}
