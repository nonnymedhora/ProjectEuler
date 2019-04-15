/**
 * 
 */
package org.bawaweb.euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Navroz
 * 
 * Pandigital prime
 * ====================
 * Ans. 7652413
	Problem 41
	
	We shall say that an n-digit number is pandigital 
	if it makes use of all the digits 1 to n exactly once. 
	
	For example, 2143 is a 4-digit pandigital and is also prime.
	
	What is the largest n-digit pandigital prime that exists?
	
	Output
	======================================
	For 4 digits - the largest Pandigital Prime is 4231
	For 7 digits - the largest Pandigital Prime is 7652413

 *
 */
public class PandigitalPrime {

	
	public static void main(String[] args) {
		
		Map<Integer, Integer> numPanPrimeMap = new HashMap<Integer, Integer>();
		
		for (int i = 2; i <= 9; i++) {
			List<String> choices = createChoices(i);

			for (String numStr : choices) {
				int num = Integer.parseInt(numStr);
				
				if ( isPrime(num) ) {
					add2NumPanPrimeMap(numPanPrimeMap, i, num);
				}
			}
		}

		for (int i = 2; i <= 9; i++) {
			if (numPanPrimeMap.get(i) != null) {
				System.out.println("For " + i + " digits - the largest Pandigital Prime is " + numPanPrimeMap.get(i));
			}
		}
	}

	protected static void add2NumPanPrimeMap(Map<Integer, Integer> map, int key, int value) {
		if (map.get(key) == null) {
			map.put(key, value);
		} else {
			int existing = map.get(key);
			if (value > existing) {
				map.put(key, value);
			}
		}
	}

	private static List<String> createChoices(final int num) {
		if (num <= 0) {
			return null;
		}
		List<String> ch = new ArrayList<String>();
		String numStr = "";
		for (int i = 1; i <= num; i++) {
			numStr += String.valueOf(i);
		}
		return permute(numStr, 0, num - 1, ch);
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

}
