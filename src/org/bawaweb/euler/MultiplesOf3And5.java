/**
 * 
 */
package org.bawaweb.euler;

/**
 * @author Navroz
 * https://projecteuler.net/problem=1
 * 
 * Multiples of 3 and 5
 * ------------------------
 * Problem 1
 * 
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, 
 * we get 3, 5, 6 and 9. The sum of these multiples is 23.
 * 
 * Find the sum of all the multiples of 3 or 5 below 1000.
 *
 */
public class MultiplesOf3And5 {

	
	public static void main(String[] args) {
		int sum = 0;

		for(int i = 1; i < 10; i++) {
			if((i%3==0)||(i%5==0)) {
				sum += i;
			}
		}
		
		System.out.println("Sum of Multiples of 3 & 5 upto 10 is --> " + sum);
		
		sum = 0;

		for(int i = 1; i < 1000; i++) {
			if((i%3==0)||(i%5==0)) {
				sum += i;
			}
		}
		
		System.out.println("Sum of Multiples of 3 & 5 upto 1000 is --> " + sum);
		
		
		
	}

}
