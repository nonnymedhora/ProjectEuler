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
 */
public class LargestPalindromeProduct {
	

	public static void main(String[] args) {
		int product = 0;
		int top = (999*999);
		int floor = (100*100);
		
		int i = 999;
		int j = 999;
		boolean found = false;
		for( i = 999; i >= 100; i--) {//System.out.println("i is "+i);
			for(j = 999; j >= 100; j--) {
				product = (i * j);//System.out.println("prod is "+product);
				String str = String.valueOf(product);//System.out.println("star is "+str);
				//System.out.println("For "+str+" "+j+" * "+i);
				final StringBuilder reverse = new StringBuilder(str).reverse();
				if( str.equals(reverse)) {
					System.out.println("star is "+str+" i is "+i+" j is "+j);
					found = true;
					break;
				}
			}
		}
		if (found) {
			System.out.println("Largest palindrome made from the product of two 3-digit numbers.");
			System.out.println("is " + product + " = " + j + " * " + i);
		} else
			System.out.println("No palindrome between "+floor+" and "+top);

	}

}
