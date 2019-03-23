/**
 * 
 */
package org.bawaweb.euler;

/**
 * @author Navroz
 * https://projecteuler.net/problem=16
 * 
 * Power digit sum
 * -------------------
 * Problem 16
 * 
 * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
 * 
 * What is the sum of the digits of the number 2^1000?
 *
 */
public class PowerDigitSum {
	static int 		x = Integer.MAX_VALUE;				//	2^31-1
	static long 	y = Long.MAX_VALUE;					//	2^63-1
	static float 	f = Float.MAX_VALUE;				//	(2-(2^-23)) * (2^127)
	static double 	d = Double.MAX_VALUE;				//	(2-(2^-52)) * (2^1023)
	
	public static void main(String[] args) {
		System.out.println((int)Math.pow(2,15));

		System.out.println(d/*>Math.pow(2,1000)*/);//1.0715086071862673E301
		
		
	}

}
