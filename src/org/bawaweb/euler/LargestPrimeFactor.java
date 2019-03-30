/**
 * 
 */
package org.bawaweb.euler;

/**
 * @author Navroz
 * https://projecteuler.net/problem=3
 * 
 * Largest prime factor
 * -----------------------
 * 
 * Problem 3
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 * 
 * ANS--6857
 *
 */
	/*	OUTPUT
	 * Target is 600851475143    [and half is] 300425737571]
	 * Largest Prime Factor for 600851475143 is 300425737531
	 **/
public class LargestPrimeFactor {
	static long target = 600851475143l;//13195;//
	
	private static boolean isPrime(final long number) {
		boolean isPrime = true;
		if (number < 0)
			System.out.println("Invalid-Number	" + number);

		if (number == 1 || number == 2 || number == 3)
			return true;

		for (long i = 2; i < ((long) Math.sqrt(number) + 1); i++) {
			if (number % i == 0l)
				return false;
		}
		return isPrime;
	}
	
	
	public static void main(String[] args) {
		long largestPrime = 0l;
		long half = ((target/2));//System.out.println("t/29 is "+(target/29)+" and (t%29) is "+(target%29));
		System.out.println("Target is "+ target+"    [and half is] ("+half+")");
		
		
		for( long x = half; x > 5000; x-- ) {//System.out.println(x);
			
			if ( target%x == 0 ) {//System.out.println("IsFactor "+x);
				
				if (x % 2 == 0) {
					continue;
				}
				if (x % 3 == 0) {
					continue;
				}
				if (x % 5 == 0) {
					continue;
				}
				if (x % 7 == 0) {
					continue;
				}
				if (x % 11 == 0) {
					continue;
				}
				if (x % 13 == 0) {
					continue;
				}
				if (x % 17 == 0) {
					continue;
				}
				if (x % 19 == 0) {
					continue;
				}
				if (x % 23 == 0) {
					continue;
				}
				
				if ( isPrime(x) ) {//System.out.println("Testing Prime for "+x);
					largestPrime = x;
					break;
				} 
			}
		}
		
		
		System.out.println("Largest Prime Factor for "+target+" is "+largestPrime);
	}

}
