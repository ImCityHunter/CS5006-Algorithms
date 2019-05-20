package exam;

/**
 * Problem3.java
 * 
 * Problem 3
 * CS 5006 Final Exam
 * 
 * The Counterfeit Coin problem is a reduce and conquer problem. 
 * Given (N-1) genuine coins and 1 counterfeit coin that looks the 
 * same but weights less, determine how to detect the counterfeit 
 * coin using only a balance scale to compare equal numbers of 
 * coins in the smallest number of weighings.
 * 
 * If the number of coins is even, compare the two halves and 
 * the lighter half contains the counterfeit coin. If odd, set one 
 * of the coins aside and compare the even number. If the two 
 * halves are equal, the coin set aside is counterfeit. Otherwise 
 * the counterfeit coin is in the lighter half. If only two coins 
 * remain, the lighter one is counterfeit.
 * 
 * The problem size decreases by a factor of 2, so the algorithm 
 * performs O(log2 n) comparisons, assuming that dividing and 
 * weighing the coins are O(1) operations.
 * 
 * This program represents N coins as a string that contains (N-1)
 * REGULAR_COINs and 1 COUNTERFEIT_COIN. Substring comparison works 
 * because COUNTERFEIT_COIN is lexically less than REGULAR_COIN.
 * It is efficient because a substring does not copy characters: 
 * it just refers to the original string.
 * 
 * The problem is to implement the function findCounterfeit() that 
 * takes a string representing the coins, and finds the index of 
 * the counterfeit using only string length, substring, and string 
 * comparison to divide the coins into piles and compare the piles 
 * with the balance scale. Do use COUNTERFET_COIN or REGULAR_COIN
 * in this function.
 * 
 * Implement the solution either iteratively or recursively. If 
 * recursively, you may implement a supporting function if needed.
 */

import java.util.Arrays;
import java.util.Random;

public class Problem3 {
	static final char REGULAR_COIN = '1';
	static final char COUNTERFEIT_COIN = '0';
	
	/**
	 * Crate collection of (n-1) regular coins plus one light 
	 * counterfeit at the specified position in the string.
	 * 
	 * @param n number of coins
	 * @param counterfeit the index of the counterfeit coin
	 * @return collection with one light counterfeit coin.
	 */
	static String makeCoins(int n, int counterfeit) {
		char c[] = new char[n];
		Arrays.fill(c, REGULAR_COIN);
		c[counterfeit] = COUNTERFEIT_COIN; 
		return new String(c);
	}
	
	/**
	 * Crate collection of (n-1) regular coins plus one light 
	 * counterfeit randomly placed in the string.
	 * 
	 * @param n number of coins
	 * @param counterfeit the index of the counterfeit coin
	 * @return collection with one light counterfeit coin.
	 */
	static String makeCoins(int n) {
		return makeCoins(n, new Random().nextInt(n));
	}
		
	/**
	 * Given a collection of coins, returns the index of the
	 * counterfeit coin using only string length, substring, 
	 * and string comparison to divide the coins into piles
	 * and compare the piles with the balance scale.
	 * 
	 * @param coins the coins with one light counterfeit
	 * @return the index of the counterfeit in the string
	 */
	static int findCounterfeit(String coins) {
		int left = 0; //default left side
		int right = coins.length(); //default right side
		int ans = -1; //default = -1;
		
		while(ans == -1 && right >= left) {
			int i = (right+left)/2;
			String leftStr = coins.substring(left,i);
			String rightStr = null;
			String sub = coins.substring(left,right);
			
			//if substring of coin has a length of 1, then answer is found;
			if(sub.length()==1) return i;
			
			//else Binary Search the remaining String
			
			//if remaining string has even length
			if(sub.length()%2==0) {
				rightStr = coins.substring(i,right);
				if(leftStr.compareTo(rightStr) < 0) right = i;
				else left = i;
			}
			else { //remaining string has odd length
				rightStr = coins.substring(i+1,right);
				if(leftStr.compareTo(rightStr)==0) ans = i;
				else if(leftStr.compareTo(rightStr) < 0) right=i;
				else left=i+1;
			}
		}
		return ans;
	}
	
	/**
	 * Main function exercises findCounterfeit n repetitions 
	 * for n=1..7 coins.
	 * 
	 * @param args unused
	 */
	public static void main(String[] args) {
		// try from 1 to 7 coins
		for (int ncoins = 1; ncoins <= 7; ncoins++) {
			// repeat once for each counterfeit position 
			for (int cpos = 0; cpos < ncoins; cpos++) {
				// create the collection of coins and counterfeit
				String coins = makeCoins(ncoins, cpos);
				System.out.printf("\ncoins: %s\n", coins);
				
				// find the counterfeit position in the string
				int found = findCounterfeit(coins);
				System.out.printf("counterfeit at: %d expected: %d\n", found, cpos);
			}
		}
	}
}