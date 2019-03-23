/**
 * 
 */
package org.bawaweb.euler;

/**
 * @author Navroz
 * https://projecteuler.net/problem=6
 * 
 * Sum square difference
 * 
 * Problem 6
 * 
 * The sum of the squares of the first ten natural numbers is,
 * 12 + 22 + ... + 102 = 385.
 * 
 * The square of the sum of the first ten natural numbers is,
 * (1 + 2 + ... + 10)2 = 552 = 3025
 * 
 * Hence the difference between 
 * the sum of the squares of the first ten natural numbers 
 * and the square of the sum is 
 * 3025 − 385 = 2640.
 * 
 * Find the difference between 
 * the sum of the squares of the first one hundred natural numbers 
 * and the square of the sum.
 * 
 * OUTPUT
 * ----------
 * Difference between sum-of-squares
 * and sum-squared for first 100 numbers
 * is 25164150
 *
 */
public class SumSquareDistance {

	public static void main(String[] args) {
		int n = 100;
		
		int sumSquares = (n*(n+1)*((2*n)+1))/6;
		int sum = (n*(n+1))/2;
		double sumSq = Math.pow(sum,2);
		
		System.out.println("Difference between sum-of-squares\n and sum-squared for first "+n+" numbers\n is "+(int)(sumSq-sumSquares));

	}

}
