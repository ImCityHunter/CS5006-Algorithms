package exam;

/**
 * Problem4.java
 *
 * Problem 4
 * CS 5006 Final Exam
 * 
 * Given an even power-of-2 sized array of elements in  
 * the following format: 
 *   [ a1, a2, a3, a4, ……, an, b1, b2, b3, b4, …., bn ]
 * 
 * The task is shuffle the upper and lower sub-arrays 
 * without using extra space. 
 *   [ a1, b1, a2, b2, a3, b3, ……, an, bn ]
 * 
 * The algorithm uses a divide and conquer technique. 
 * Logically divide the given array into half and swap 
 * the second half elements of the lower sub-array with 
 * first half element of upper sub-array. Recursively do 
 * this for the lower and upper sub-arrays.
 *
 * Here is the process with the help of an example with
 * an 8-element array.
 *   [ a1, a2, a3, a4, b1, b2, b3, b4 ]
 *   
 * Split the array into two sub-arrays: 
 *   [ a1, a2, a3, a4 : b1, b2, b3, b4 ]
 *
 * Exchange top-half elements of lower array with bottom-
 * half element of upper array: 
 *   [ a1, a2, b1, b2 : a3, a4, b3, b4 ]
 *             '----'   '----'
 *             
 * Recursively split the lower sub-array [ a1, a2, b1, b2 ] 
 * into sub-arrays:
 *   [ a1, a2 :  b1, b2 ]
 *   
 * Again, exchange top-half elements of lower array with
 * bottom-half elements of upper array:
 *   [ a1, b1 :  a2, b2 ]
 *         --    --
 *         
 * Recursively split [ a1, b1 ] into sub-arrays: 
 *   [ a1 : b1 ]
 *   
 * This array is size 2, so it is already merged.
 * 
 * Recursively repeat the above steps for the upper-arrays. 
 *   
 */
import java.util.Arrays;

public class Problem4 {
	/**
	 * Shuffle together the lower and upper halves of the array
	 * The number of elements must be an even power of 2.
	 * 
	 * @param a the array
	 * @param first the index of the first element
	 * @param last the index of the last element
	 */
    static void shuffleArray(int a[], int first, int last) {
    	if((last-first)>2) {	
    		int half = (first+last)/2; //index of median between current last & first
    		int len = (last-half)/2;
    		int bottomUpper = first + len; //upper side of bottom half (index)
    		int topLower = half+1; //lower side of top half (index)
    		for(int i = 0;i< len;i++) {
    			int temp = a[bottomUpper+i];
    			a[bottomUpper+i] = a[topLower+i];
    			a[topLower+i] = temp;
    		}
    		shuffleArray(a,first,half); //split first half
    		shuffleArray(a,half+1,last); //split second half  		
    	}

    }
     
    /**
     * Test function for the array
     * 
     * @param args unused
     */
    public static void main(String[] args)
    {
        System.out.println("Start problem 4\n");

        // a: size 2
        int a[] = { 1, 2 };
        System.out.println("\na) original:");
        System.out.println(Arrays.toString(a));
      
        shuffleArray(a, 0, a.length - 1);
        System.out.println("\na) actual:");
        System.out.println(Arrays.toString(a));
        
        System.out.println("\na) expected:");
        System.out.println("[1, 2]");
        
        // b: size 4
        int b[] = { 1, 3, 2, 4 };
        System.out.println("\nb) original:");
        System.out.println(Arrays.toString(b));
      
        shuffleArray(b, 0, b.length - 1);
        System.out.println("\nb) actual:");
        System.out.println(Arrays.toString(b));
        
        System.out.println("\nb) expected:");
        System.out.println("[1, 2, 3, 4]");
        
        // c: size 8
        int c[] = { 1, 3, 5, 7, 2, 4, 6, 8 };
        System.out.println("\nc) original:");
        System.out.println(Arrays.toString(c));
      
        shuffleArray(c, 0, c.length - 1);
        System.out.println("\nb) actual:");
        System.out.println(Arrays.toString(c));
        
        System.out.println("\nc) expected:");
        System.out.println("[1, 2, 3, 4, 5, 6, 7, 8]");
        
        System.out.println("\nEnd problem 4");
    }
}