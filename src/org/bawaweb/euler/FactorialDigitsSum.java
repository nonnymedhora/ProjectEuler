/**
 * 
 */
package org.bawaweb.euler;

import java.math.BigInteger;

/**
 * @author Navroz
 * https://projecteuler.net/problem=20
 * 
 * Factorial digit sum
 * ------------------
 * Problem 20
 * 
 * n! means n × (n − 1) × ... × 3 × 2 × 1
 * 
 * For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
 * and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
 * 
 * Find the sum of the digits in the number 100!
 * 
 * Note---
 * using java.math.BigInteger; since int and double go into over flow after ~30-40!
 * 
 * OUTPUT
 * --------
 * For 100!,
 * the sum of its digits is 648
 *
 */
public class FactorialDigitsSum {
	
	private static final int LIMIT = 100;

	private static int getSumOfDigits(BigInteger num) {
		String s = num.toString();
		int sum = 0;
		for(int i = 0; i < s.length(); i++) {
			int digit = Character.getNumericValue(s.charAt(i));
			sum += digit;
		}
		return sum;
	}

	public static void main(String[] args) {
		double sumDigits = 0;
		BigInteger factValue = new BigInteger("1");
		
		for(int i = 1; i <= LIMIT; i++) {
			factValue = factValue.multiply(new BigInteger(i+""));
		}
		
		
		System.out.println("For "+LIMIT+"!,\n the sum of its digits is "+getSumOfDigits(factValue));
	}

}
