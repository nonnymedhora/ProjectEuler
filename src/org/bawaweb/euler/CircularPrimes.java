/**
 * 
 */
package org.bawaweb.euler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Navroz
 * 
 * Circular primes
 * =================
 * Ans. 55
 * 
	Problem 35
	
	The number, 197, is called a circular prime because all rotations of the digits: 
	197, 971, and 719, are themselves prime.
	
	There are thirteen such primes below 100: 
	2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
	
	How many circular primes are there below one million?

 * 
 * Output
 * ==================
 * There are 55 CircularPrimes till 1000000
	2 3 5 7 11 13 17 31 37 71 73 79 97 113 131 197 199 311 337 373 719 733 919 971 991 1193 1931 3119 3779 7793 7937 9311 9377 11939 19391 19937 37199 39119 71993 91193 93719 93911 99371 193939 199933 319993 331999 391939 393919 919393 933199 939193 939391 993319 999331 
	=================

 */
public class CircularPrimes {

	private final static int  target = 1000000;
	
	public static void main(String[] args) {

		int numCircularPrimes = 0;
		List<Integer> numCircularPrimesList = new ArrayList<Integer>();

		for (int nmbr = 2; nmbr <= target; nmbr++) {

			if ( isPrime(nmbr) ) {

				if ( allCircularPermsArePrime(nmbr)) {

					numCircularPrimes += 1;
					numCircularPrimesList.add(nmbr);
				}
			}
		}

		System.out.println("There are " + numCircularPrimes + " CircularPrimes till " + target);
		printList(numCircularPrimesList);
	}
	
	private static List<String> circularPermute(final String aString) {
		List<String> circularList = new ArrayList<String>();
		String dblStr = aString + aString;
		final int length = aString.length();
		for (int i = 0; i < length; i++) {
			circularList.add(dblStr.substring(i, length + i));
		}
		return circularList;
	}

	private static boolean allCircularPermsArePrime(final int num) {
		boolean allPermsArePrime = true;
		String numStr = String.valueOf(num);
		List<String> numPermsList = circularPermute(numStr);
		for (String nArr : numPermsList) {
			int n = Integer.parseInt(nArr);
			if (!isPrime(n))
				return false;
		}
		return allPermsArePrime;
	}
	
	/** from GfG
     * permutation function 
     * @param str string to calculate permutation for 
     * @param l starting index 
     * @param r end index 
	 * @return list of permutations of str
     */
	private static List<String> permute(String str, int l, int r, List<String> perms) {
		if (l == r) {
			perms.add(str);

		} else {
			for (int i = l; i <= r; i++) {
				str = swap(str, l, i);
				permute(str, l + 1, r, perms);
				str = swap(str, l, i);
			}
		}
		return perms;
	}
  
    /** from GfG
     * Swap Characters at position 
     * @param a string value 
     * @param i position 1 
     * @param j position 2 
     * @return swapped string 
     */
	public static String swap(String a, int i, int j) {
		char temp;
		char[] charArray = a.toCharArray();
		temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	}
	
	private static boolean isPrime(final int number) {
		boolean isPrime = true;
		if (number < 0)
			System.out.println("Invalid-Number	" + number);

		if (number == 1 || number == 2 || number == 3)
			return true;

		for (int i = 2; i < ((int) Math.sqrt(number) + 1); i++) {
			if (number % i == 0)
				return false;
		}
		return isPrime;
	}
	
	private static void printList(List<Integer> aList) {
		for (int i = 0; i < aList.size(); i++) {
			int val = aList.get(i);
			System.out.print(val + " ");
		}
		System.out.println("\n=================");
	}
}
