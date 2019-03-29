/**
 * 
 */
package org.bawaweb.euler;

/**
 * @author Navroz
 * 
 * Lychrel numbers
 * ------------------
	Problem 55
	Answer	-	249
	
	If we take 47, reverse and add, 47 + 74 = 121, which is palindromic.
	
	Not all numbers produce palindromes so quickly. For example,	
	349 + 943 = 1292,
	1292 + 2921 = 4213
	4213 + 3124 = 7337
	
	That is, 349 took three iterations to arrive at a palindrome.
	
	Although no one has proved it yet, it is thought that some numbers, like 196, never produce a palindrome. 
	A number that never forms a palindrome through the reverse and add process is called a Lychrel number. 
	Due to the theoretical nature of these numbers, and for the purpose of this problem, we shall assume that 
	a number is Lychrel until proven otherwise. 
	
	In addition you are given that for every number below ten-thousand, 
	it will either (i) become a palindrome in less than fifty iterations, or, 
	(ii) no one, with all the computing power that exists, has managed so far to map it to a palindrome. 
	
	In fact, 10677 is the first number to be shown to require 
	over fifty iterations before producing a palindrome: 4668731596684224866951378664 (53 iterations, 28-digits).
	
	Surprisingly, there are palindromic numbers that are themselves Lychrel numbers; the first example is 4994.
	
	How many Lychrel numbers are there below ten-thousand?
	
	Output
	-----------------------
	Number of Lychrel Numbers below 10000 is 249

 *
 */

public class LychrelNumbers {
	
	final static int target = 10000;	
	
	private static long reverseAndAdd(final long num) {
		String numStr 		= String.valueOf(num);
		String revNumStr 	= new StringBuffer(numStr).reverse().toString();
		return num + (Long.parseLong(revNumStr));
	}
	
	private static boolean isPalindrome(long num) {
		String numStr 		= String.valueOf(num);
		String revNumStr 	= new StringBuffer(numStr).reverse().toString();
		if( numStr.equals(revNumStr) ) {
			return true;
		}
		return false;
	}
	
	private static boolean isLychrel(long num) {
		int tryCount = 0;
		while( tryCount < 30 ) {
			long revAdd = reverseAndAdd(num);
			if(isPalindrome(revAdd)){
				return false;			//	A Lychrel Number never forms a palindrome through the reverse-add process
			}
			num = revAdd;
			tryCount++;
		}
		return true;
	}

	public static void main(String[] args) {
		int numLychrel = 0;
		
		for(long i = 0l; i < target; i++) {
			if( isLychrel(i) ) {
				numLychrel += 1;
			}
		}
		System.out.println("Number of Lychrel Numbers below "+target+" is "+numLychrel);
	}

}
