/**
 * 
 */
package org.bawaweb.euler;

/**
 * @author Navroz
 * 
 * How many reversible numbers are there below one-billion?
	Problem 145 Ans. 608720
	=================
	
	Some positive integers n have the property that the sum [ n + reverse(n) ] 
	consists entirely of odd (decimal) digits. 
	For instance, 36 + 63 = 99 and 409 + 904 = 1313. 
	
	We will call such numbers reversible; so 36, 63, 409, and 904 are reversible. 
	Leading zeroes are not allowed in either n or reverse(n).
	
	There are 120 reversible numbers below one-thousand.
	
	How many reversible numbers are there below one-billion (10^9)?
	
	Output
	=============================================
	There are 608720 Reversible Odd Numbers numbers below 1000000000


 *
 */
public class ReversibleOdds {
	
	private static final long target = 1000000000l;
	
	private static boolean isReversibleOdd(final long n){
		boolean isReversibleOdd = true;

		if (n % 10 == 0) {		// no leading / trailing zeroes
			return false;
		}

		long revNum = Long.parseLong(new StringBuilder(String.valueOf(n)).reverse().toString());

		long total = n + revNum;		//	sum[n + reverse(n)]

		String totalStr = String.valueOf(total);
		final int length = totalStr.length();
		for (int i = 0; i < length; i++) {		
			if (Character.getNumericValue(totalStr.charAt(i)) % 2 != 1) {		// odd digits test
				return false;
			}
		}

		return isReversibleOdd;
	}
	
	

	public static void main(String[] args) {
		int numRevOdds = 0;
		for(long nmbr = 11l; nmbr <= target; nmbr++){
			if( isReversibleOdd(nmbr) ){
				numRevOdds += 1;
			}
		}
		
		System.out.println("There are "+numRevOdds+" Reversible Odd Numbers numbers below "+target);
	}

}
