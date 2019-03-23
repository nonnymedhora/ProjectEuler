/**
 * 
 */
package org.bawaweb.euler;

/**
 * @author Navroz https://projecteuler.net/problem=5
 * 
 * 
 * Smallest multiple
 * --------------------- 
 * Problem 5
 * 
 * 2520 is the smallest number that can be divided by each of the
 * numbers from 1 to 10 without any remainder.
 * 
 * What is the smallest positive number that is evenly divisible by all
 * of the numbers from 1 to 20?
 * 
 * 1-N 		Smallest 
 * ---- 	-------- 
 * 1-2 		2 
 * 1-3 		6 
 * 1-4 		12 
 * 1-5 		60 
 * 1-6 		60 
 * 1-7
 *
 */
public class SmallestMultiple {

	public static void main(String[] args) {
		
		long[] arr = new long[]{1,2,3,4,5,6,7,8,9,10,
								11,12,13,14,15,16,17,18,19,20};
		System.out.println("Smallest Multiple for first " + arr.length + " numbers is -->  " + lcm(arr));

	}

	//https://stackoverflow.com/questions/4201860/how-to-find-gcd-lcm-on-a-set-of-numbers
	private static long gcd(long a, long b) {
		while (b > 0) {
			long temp = b;
			b = a % b; // % is remainder
			a = temp;
		}
		return a;
	}
	
	private static long lcm(long a, long b) {
		return a * (b / gcd(a, b));
	}

	private static long lcm(long[] input) {
		long result = input[0];
		for (int i = 1; i < input.length; i++)
			result = lcm(result, input[i]);
		return result;
	}

}
