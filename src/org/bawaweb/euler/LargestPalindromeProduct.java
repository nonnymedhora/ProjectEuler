/**
 * 
 */
package org.bawaweb.euler;

/**
 * @author Navroz
 * https://projecteuler.net/problem=4
 * 
 * Largest palindrome product
 * --------------------------
 * 
 * Problem 4
 * A palindromic number reads the same both ways. 
 * The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * 
 * Find the largest palindrome made from the product of two 3-digit numbers.
 * 
 * OUTPUT
 * -----------
 * Largest palindrome made from the product of two 3-digit numbers.
is 906609 = 913 * 993
 *
 */
public class LargestPalindromeProduct {
	

	public static void main(String[] args) {
		int product = 0;
		final int LIMIT = 999;
		final int BASE = 100;
		int top = (LIMIT*LIMIT);
		int floor = (BASE*BASE);
		
		int i = LIMIT;
		int j = LIMIT;
		boolean found = false;
		int max = 0;
		int num1 = 0;
		int num2 = 0;
		
		for( i = LIMIT; i >= BASE; i--) {
			for(j = LIMIT; j >= BASE; j--) {
				product = (i * j);
				String str = String.valueOf(product);

				final String reverse = new StringBuilder(str).reverse().toString();
				if( str.equals(reverse)) {
					if(product > max){
						max = product;
						num1 = j;
						num2 = i;
						found = true;
					}
				}
			}
		}
		if (found) {
			System.out.println("Largest palindrome made from the product of two 3-digit numbers.");
			System.out.println("is " + max + " = " + num1 + " * " + num2);
		} else
			System.out.println("No palindrome between "+floor+" and "+top);

	}

}
