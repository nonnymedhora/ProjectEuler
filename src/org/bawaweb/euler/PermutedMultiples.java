/**
 * 
 */
package org.bawaweb.euler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Navroz
 * 
 * Permuted multiples
 * --------------------
	Problem 52
	Ans. 142857				[(2) 285715,(3) 428571,(4) 571428,(5) 714285,(6) 857142]
	
	It can be seen that the number, 125874, and its double, 251748, 
	contain exactly the same digits, but in a different order.
	
	Find the smallest positive integer, x, 
	such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.

 *
 * Output
 * ==========================
 * Permuted multiples of 142857 contains all digits of 142857
	1	142857
	2	285714
	3	428571
	4	571428
	5	714285
	6	857142

 */
public class PermutedMultiples {

	public static void main(String[] args) {
		boolean found = false;
		int num = 125784;
		while (!found) {
			if (doPermMultCheck(num)) {
				found = true;
				break;
			}
			num += 1;
		}

		System.out.println("Permuted multiples of " + num + " contains all digits of " + num);
		for (int i = 1; i <= 6; i++) {
			System.out.println(i + "\t" + (i * num));
		}
	}

	private static boolean doPermMultCheck(final int num) {
		List<Integer> digList = getDigitList(num);
		int twiceNum = 2 * num;
		List<Integer> twiceList = getDigitList(twiceNum);
		if (doDigitListCompareCheck(digList, twiceList)) {
			int thriceNum = 3 * num;
			List<Integer> thriceList = getDigitList(thriceNum);
			if (doDigitListCompareCheck(digList, thriceList)) {
				int quadNum = 4 * num;
				List<Integer> quadList = getDigitList(quadNum);
				if (doDigitListCompareCheck(digList, quadList)) {
					int pentaNum = 5 * num;
					List<Integer> pentaList = getDigitList(pentaNum);
					if (doDigitListCompareCheck(digList, pentaList)) {
						int hexaNum = 6 * num;
						List<Integer> hexaList = getDigitList(hexaNum);
						if (doDigitListCompareCheck(digList, hexaList)) {
							return true;
						}
					}

				}
			}
		}
		return false;
	}

	private static boolean doDigitListCompareCheck(final List<Integer> aList, final List<Integer> bList) {
		boolean allOk = true;
		if (aList.size() != bList.size()) {
			return false;
		}
		for (int aNum : aList) {
			if (!bList.contains(aNum)) {
				return false;
			}
		}
		return allOk;
	}

	private static List<Integer> getDigitList(final int n) {
		String numString = String.valueOf(n);
		List<Integer> digitList = new ArrayList<Integer>();
		for(int i = 0; i < numString.length(); i++) {
			digitList.add( Character.getNumericValue( numString.charAt(i))	);
		}
		return digitList;
	}

}
