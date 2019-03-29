/**
 * 
 */
package org.bawaweb.euler;

/**
 * @author Navroz
 * 
 *         How many reversible numbers are there below one-billion? 
 *         ----------------------------------------------------------
 *         Problem 145
 *         Ans. 608720
 * 
 *         Some positive integers n have the property that 
 *         the sum [ n + reverse(n) ] consists entirely of odd (decimal) digits. 
 *         For instance, 36 + 63 = 99 and 409 + 904 = 1313. 
 *         We will call such numbers reversible; 
 *         so 36, 63, 409, and 904 are reversible. 
 *         
 *         Leading zeroes are not allowed in either n or reverse(n).
 * 
 *         There are 120 reversible numbers below one-thousand. How many
 *         reversible numbers are there below one-billion (10^9)?
 *         
 *         
 *         Output
 *         ------------------------------------------------------------
 *         There are 608720 fully odd reversible numbers below 1000000000
 *
 * 
 */
public class ReversibleOddNumbers {

	final static int target =	1000000000;//1000;// 		//	billion

	private static long reverseAndAdd(final long num) {
		String numStr = String.valueOf(num);
		String revNumStr = new StringBuffer(numStr).reverse().toString();
		return num + (Long.parseLong(revNumStr));
	}
	
	private static boolean allOddDigits(long num) {
		String numStr = String.valueOf(num);
		int length = numStr.length();
		for( int i = 0; i < length; i++) {
			int digit = Character.getNumericValue( numStr.charAt(i) );
			if(0==i&&digit==0){return false;}
				if( digit%2 == 0) {	//	even digit
					return false;
				}
		}
		return true;
	}

	public static void main(String[] args) {
		int count = 0;
		for(int i = 10; i < target; i++) {
			if ( i % 10 != 0) {
				long proc = reverseAndAdd(i);
				if (allOddDigits(proc)) {
					count += 1;
				} 
			}
		}
		
		System.out.println("There are "+count+" fully odd reversible numbers below "+target);
	}

}
