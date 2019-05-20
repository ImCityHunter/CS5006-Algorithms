package edu.northeastern.cs_5006;

import java.util.Stack;

public class HanoiStack {
	
	//professor recursion function
	public void hanoi(int ndisks, int src, int dest) {
		if(ndisks > 0) {
			int other = 3 - src - dest;
			hanoi(ndisks-1,src,other);
			System.out.printf ("Move disk from pole %d to pole %d\n", src, dest);
			hanoi (ndisks-1, other, dest);
		}
	}
	
	public void hanoiIterative(int ndisks, int src, int dest) {
		
		
		
		Stack <Integer>[] poles= initialize(ndisks);
		
		
		//steps needed to complete the hanoi
		/*
		int moves = (int) Math.pow(2,ndisks)-1;
		if(ndisks%2!=0) {
			//odd # of disks
			for(int i = 1; i <= moves;i++) {
				if(i%3==1) moveDisk(poles,0,2);
				else if(i%3==2) moveDisk(poles,0,1);
				else if(i%3==0) moveDisk(poles,1,2);
			}
		}
		else {
			//even # of disks
			for(int i = 1; i <= moves;i++) {
				if(i%3==1) moveDisk(poles,0,1);
				else if(i%3==2) moveDisk(poles,0,2);
				else if(i%3==0) moveDisk(poles,1,2);
			}
		}
		 */
		
		//without calculating the steps
		int other = 3 - src - dest;
		if(ndisks%2!=0) { 
			//odd # of disks
			moveDisk(poles, src, dest);
			while(poles[dest].size() < ndisks) {
				moveDisk(poles, src, other);
				moveDisk(poles, other, dest);
				moveDisk(poles, src, dest);
	
			}
		}
		else { 
			//even # of disks
			while(poles[dest].size() < ndisks) {
				moveDisk(poles, src, other);
				moveDisk(poles, src, dest);
				moveDisk(poles, other, dest);
			}
		}

		
	}

	
	
	/**
	 * Initialize the array of stacks needed
	 * @param ndisks
	 * @return Array of Stacks
	 */
	public Stack<Integer> [] initialize(int ndisks) {
		@SuppressWarnings("unchecked")
		Stack <Integer>[] poles = new Stack [3];
		poles[0] = new Stack<Integer>();
		poles[1] = new Stack<Integer>();
		poles[2] = new Stack<Integer>();
		for(int i=1;i<= ndisks;i++) {
			poles[0].push((Integer) i);
		}
		return poles;
	}
	/**
	 * Find the legal move & 
	 * Move disk between poles 
	 * @param poles
	 * @param src
	 * @param dest
	 * 
	 */
	public void moveDisk(Stack <Integer>poles [],int src, int dest) {
		
		if(poles[src].isEmpty()) {
			poles[src].push(poles[dest].pop());
			int temp = src;
			src = dest;
			dest = temp;
		}
		else if(poles[dest].isEmpty()) {
			poles[dest].push(poles[src].pop());
		}
		else if(poles[src].peek() > poles[dest].peek()) {
			poles[dest].push(poles[src].pop());
		}
		else {
			poles[src].push(poles[dest].pop());
			int temp = src;
			src = dest;
			dest = temp;
		}
		System.out.printf ("Move disk from pole %d to pole %d\n", src, dest);
	}
	
	
	public static void main(String[] args) {
		HanoiStack stack = new HanoiStack();
		// Move one ring from pole 1 to pole 3.
		System.out.printf("\nHanoi for one ring:\n");
		System.out.println("recurisve: ");
		stack.hanoi (1,0,1);
		System.out.println("iterative: ");
		stack.hanoiIterative(1,0,1);

		// Move two rings from pole 1 to pole 3.
		System.out.printf("\nHanoi for two rings:\n");
		System.out.println("recurisve: ");
		stack.hanoi (2,0,1);
		System.out.println("iterative: ");
		stack.hanoiIterative(2,0,1);

		// Move three rings from pole 1 to pole 3.
		System.out.printf("\nHanoi for three rings:\n");
		System.out.println("recurisve: ");
		stack.hanoi (3,0,2);
		System.out.println("iterative: ");
		//stack.hanoiIterative(3);

		// Move four rings from pole 1 to pole 3.
		System.out.printf("\nHanoi for four rings:\n");
		System.out.println("recurisve: ");
		stack.hanoi (4,0,2);
		System.out.println("iterative: ");
		//stack.hanoiIterative(4);

	}
	
	
	

}


