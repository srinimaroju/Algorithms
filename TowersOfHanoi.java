import java.util.ArrayList;
import java.util.Arrays;


public class TowersOfHanoi {
	public Stack s1;
	public Stack s2;
	public Stack s3;
	public int numberofmoves;
	int[] disks;
	int numberofdisks;

	public TowersOfHanoi(int n) {
		s1 = new Stack();
		s2 = new Stack();
		s3 = new Stack();
		this.numberofdisks=n;
		this.numberofmoves=0;
		this.disks = new int[n];
		System.out.println("Initalizing disks");
		for(int i=0; i<n; i++) {
			this.disks[i]=i+1;
		}
		for(int i=0; i<n; i++) {
			s1.push(this.disks[n-1-i]);
		}

	}

	public void play(){
		this.playGame(this.s1.size(), this.s1, this.s2,this.s3);
	}

	public void playGame(int count, Stack source, Stack Buffer, Stack Destination) {
		if(!source.isEmpty() && count>0){
			this.playGame(count-1, source, Destination, Buffer);
			this.numberofmoves++;
			this.move(source, Destination);
			System.out.print("After Move " + this.numberofmoves +" Current State is " + this);
			if(!Buffer.isEmpty()) this.playGame(count-1, Buffer, source, Destination);
		}
	}
	public String getState(Stack s1, Stack s2, Stack s3) {
		return "s1:" + s1 + " || s2: " + s2 + " || s3:" + s3 + "\n"; 
	}

	public void move(Stack s1, Stack s2) {
		s2.push(s1.pop());
	}

	public static void main(String []args) throws Exception {
		System.out.println("Test");

		TowersOfHanoi th = new TowersOfHanoi(20);
		System.out.println("Initial State is " + th);

		th.play();

		System.out.println("Final State is " + th);

	}
	public String toString() {
		return "s1:" + s1 + " || s2: " + s2 + " || s3:" + s3 + "\n"; 
	}
}

class Stack {
	private ArrayList<Integer> stack;
	public Stack(){
		this.stack= new ArrayList<Integer>();
	}
	public int pop() {
		return stack.remove(stack.size()-1);
	}
	public void push(int item) {
		if(!this.stack.isEmpty() && (item > this.stack.get((this.stack.size()-1)))) {
			//System.out.println("Exception here");
			throw new IllegalArgumentException("Disk cannot be fit");
		}
		stack.add(item);
	}
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	public String toString() {
		return Arrays.toString(this.stack.toArray());
	}
	public int size() {
		return this.stack.size();
	}
	public int peek() {
		if(!this.stack.isEmpty()) {
			return this.stack.get((this.stack.size()-1));
		}
		else return -1;
	}
}

