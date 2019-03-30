/**
 * 
 */
package org.bawaweb.euler;

/**
 * @author Navroz
 * 
 * Investigating the primality of numbers of the form 2(n^2)-1
 * 
 * Problem 216
 * Ans216. 5437849
 * Diff. 45%
 * ---------------
 * Consider numbers t(n) of the form t(n) = 2(n^2)-1 with n > 1.
 * The first such numbers are 
 * 7, 17, 31, 49, 71, 97, 127 and 161.
 * It turns out that only 49 = 7*7 and 161 = 7*23 are not prime.
 * 
 * For n ≤ 10000 there are 2202 numbers t(n) that are prime.
 * 
 * How many numbers t(n) are prime for n ≤ 50,000,000 ?
 *
 */
public class Primality {
	
	static int target = 10000;//50000000;//
	
	static long getTn(int n) {
		return (long)(2*(Math.pow(n,2))-1);
	}
	
	private static boolean isPrime(final long number) {
		boolean isPrime = true;
		if (number < 0) {
			System.out.println("Invalid-Number	" + number);
			return false;
		}

		if (number == 1 || number == 2 || number == 3)
			return true;

		for (long i = 2; i < ((long) Math.sqrt(number) + 1); i++) {
			if (number % i == 0l)
				return false;
		}
		return isPrime;
	}


	public static void main(String[] args) {
		int numPrimes = 0;
		System.out.println(getTn(50000000));
		
		
		for(int i = 1; i <= target; i++) {
			if(isPrime(getTn(i))){
				numPrimes += 1;
			}
		}
		
		System.out.println(numPrimes);
	}

}
