// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class KStates
{        
	public static void main(String[] args) {
		KStates ks = new KStates();
		int[] states1={1, 0, 1, 1};
		int[] states2={1,0,0,0,0,1,0,0};
		int[] states3={1,1,1,0,1,1,1,1};
		
		//System.out.println(Arrays.toString(ks.cellCompete(states1,2).toArray()));
		System.out.println(Arrays.deepToString(ks.cellCompete(states2,1).toArray()));
		System.out.println(Arrays.deepToString(ks.cellCompete(states3,2).toArray()));
		
	}
  // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public List<Integer> cellCompete(int[] states, int days)
    {
    	//System.out.println("Intial" +  Arrays.toString(states));
         int[] temp;
         for(int i=0;i<days;i++) {
             temp=states.clone();
             int next=0, prev=0;
             for(int j=0;j<states.length;j++) {
                 //check edgenodes
                 if(j==0) {
                	 next=states[j+1];
                	 prev=0;
                 }   
                 //Always 0
                 else if (j==states.length-1) {
                	 prev=states[j-2];
                	 next=0;
                 } else {
                	 prev = states[j-1];
                	 next = states[j+1];
                     
                 }
                 if(prev!=next) {
                     temp[j] = 1;  
                 } else {
                     temp[j] = 0;
                 }
             }
             for(int k=0;k<states.length;k++) {
                 states[k]=temp[k];
             }
            // System.out.println("After day " + (i+1) + Arrays.toString(states));
         }
         List<Integer> finalState = new ArrayList<Integer>();
         for(int k=0;k<states.length;k++) {
                 finalState.add(states[k]);
         }
         return finalState;
    }
  // METHOD SIGNATURE ENDS
}