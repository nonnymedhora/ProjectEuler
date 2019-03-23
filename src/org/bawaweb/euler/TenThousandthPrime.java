/**
 * 
 */
package org.bawaweb.euler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Navroz
 * https://projecteuler.net/problem=7
 * 
 * 10001st prime
 * 
 * Problem 7
 * 
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, 
 * we can see that the 6th prime is 13.
 * 
 * What is the 10 001st prime number?
 * 
 *
 */
public class TenThousandthPrime {
	
	private static boolean isPrimeNumber(final long number) {
		boolean isPrime = true;

		if (number < 0)
			System.out.println("Invalid-Number	" + number);

		if (number == 1 || number == 2 || number == 3)
			return true;

		for (long i = 2l; i < ((long) Math.sqrt(number) + 1); i++) {
			if (number % i == 0)
				return false;
		}

		return isPrime;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Long> aList = new ArrayList<Long>();
		long num = 0l;
		int target = 10001;
		while(aList.size()<=target) {
			num+=1;
			if(isPrimeNumber(num)){
				aList.add(num);
			}
		}
		System.out.println("The "+target+"th Prime Number is "+aList.get(aList.size()-1));

	}

}
