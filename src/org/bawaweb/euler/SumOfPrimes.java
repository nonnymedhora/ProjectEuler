/**
 * 
 */
package org.bawaweb.euler;

/**
 * @author Navroz
 * https://projecteuler.net/problem=10
 * 
 * Summation of primes
 * -----------------------
 * Problem 10
 * 
 * The sum of the primes below 10 
 * is 2 + 3 + 5 + 7 = 17.
 * 
 * Find the sum of all the primes below two million.
 * 
 * Output
 * ------
 * Sum of Primes less than 2000000 is 1179908154
 */
public class SumOfPrimes {

	final static int target = 2000000;
	
	private static boolean isPrime(final long number) {
		boolean isPrime = true;

		if (number < 0)
			System.out.println("Invalid-Number	" + number);

		if (number == 1 || number == 2 || number == 3)
			return true;

		for (long i = 2; i < ((long) Math.sqrt(number) + 1); i++) {
			if (number % i == 0)
				return false;
		}

		return isPrime;
	}
	
	public static void main(String[] args) {
		int sum = 0;
		
		for(int i = 2; i < target; i++) {
			if(isPrime(i)){
				sum += i;
			}
		}
		
		System.out.println("Sum of Primes less than "+ target + " is "+sum);

	}

}
