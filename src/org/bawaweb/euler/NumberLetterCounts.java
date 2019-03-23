/**
 * 
 */
package org.bawaweb.euler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Navroz
 * https://projecteuler.net/problem=17
 * 
 * Number letter counts
 * ------------------
 * Problem 17
 * 
 * If the numbers 1 to 5 are written out in words: 
 * one, two, three, four, five, 
 * then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
 * 
 * If all the numbers from 1 to 1000 (one thousand) inclusive 
 * were written out in words, how many letters would be used?
 * 
 * NOTE: Do not count spaces or hyphens. 
 * For example, 342 (three hundred and forty-two) contains 23 letters 
 * and 115 (one hundred and fifteen) contains 20 letters. 
 * 
 * The use of "and" when writing out numbers is in compliance with British usage.
 * 
 * OUTPUT
 * -------------
 * Writing 1 to 1000 in words
 * has a total of 21124 letters

 *
 */
public class NumberLetterCounts {
	
	
	private static final int LIMIT = 1000;
	private static final String BLANK = "";
	private static final String SPACE = " ";
	
	private static final Map<Integer,String> unitMap = new HashMap<Integer,String>();
	static {
		unitMap.put(1,"one");
		unitMap.put(2,"two");
		unitMap.put(3,"three");
		unitMap.put(4,"four");
		unitMap.put(5,"five");
		unitMap.put(6,"six");
		unitMap.put(7,"seven");
		unitMap.put(8,"eight");
		unitMap.put(9,"nine");
		unitMap.put(10,"ten");
	}
	
	private static final Map<Integer,String> teenMap = new HashMap<Integer,String>();
	static {
		teenMap.put(11,"eleven");
		teenMap.put(12,"twelve");
		teenMap.put(13,"thirteen");
		teenMap.put(14,"fourteen");
		teenMap.put(15,"fifteen");
		teenMap.put(16,"sixteen");
		teenMap.put(17,"seventeen");
		teenMap.put(18,"eighteen");
		teenMap.put(19,"nineteen");
	}
	
	private static final Map<Integer,String> tensMap = new HashMap<Integer,String>();
	static {
		tensMap.put(2,"twenty");
		tensMap.put(3,"thirty");
		tensMap.put(4,"forty");
		tensMap.put(5,"fifty");
		tensMap.put(6,"sixty");
		tensMap.put(7,"seventy");
		tensMap.put(8,"eighty");
		tensMap.put(9,"ninety");
	}
	

	private static final Map<Integer,String> hundredsMap = new HashMap<Integer,String>();
	static {
		hundredsMap.put(1,"one");
		hundredsMap.put(2,"two");
		hundredsMap.put(3,"three");
		hundredsMap.put(4,"four");
		hundredsMap.put(5,"five");
		hundredsMap.put(6,"six");
		hundredsMap.put(7,"seven");
		hundredsMap.put(8,"eight");
		hundredsMap.put(9,"nine");
	}
	
	public static int getNumLetters(final int num) {
		int h;		//	hundreds
		int t;		//	tens
		int u;		//	units

		String str = String.valueOf(num);
		String numString = "";
		
		if( num <= 10 ) {
			numString = unitMap.get(num);
		} else if( num > 10 && num < 20) {
			numString = teenMap.get(num);
		} else if( num >= 20 && num < 100) {
			t	=	Character.getNumericValue(str.charAt(0));
			u	=	Character.getNumericValue(str.charAt(1));
			
			if( u == 0) {
				numString = tensMap.get(t);
			} else {
				numString = tensMap.get(t) + " " + unitMap.get(u);
			}
			
		} else if( num >= 100 && num <= 999) {
			h	=	Character.getNumericValue(str.charAt(0));
			t	=	Character.getNumericValue(str.charAt(1));
			u	=	Character.getNumericValue(str.charAt(2));
			
			if( h!=0 && t!=0 && u!=0 ) {
				if ( t!=1) {
					numString = hundredsMap.get(h) + " hundred and " + tensMap.get(t) + " " + unitMap.get(u);
				} else {		//	t=1
					int lastTwo = 10+u;
					numString = hundredsMap.get(h) + " hundred and " + teenMap.get(lastTwo);
				}
			} else if( h!=0 && t==0 && u!=0 ) {
				numString = hundredsMap.get(h) + " hundred and " + unitMap.get(u);
			} else if( h!=0 && t!=0 && u==0 ) {
				if (t==1) {
					numString = hundredsMap.get(h) + " hundred and ten";
				} else {
					numString = hundredsMap.get(h) + " hundred and " + tensMap.get(t);
				}
			} else if( h!=0 && t==0 && u==0 ) {
				numString = hundredsMap.get(h) + " hundred";
			}
		} else if ( num == LIMIT) {
			numString = "one thousand";
		}
//		System.out.println(numString);
		numString = numString.replaceAll(SPACE,BLANK);
		return numString.length();
	}
	
	
	
	
	
	

	public static void main(String[] args) {
//		System.out.println(342+" has "+getNumLetters(342)+" letters");
		int totalNumLetters = 0;
		
		for(int i = 1; i <= LIMIT; i++ ) {
			totalNumLetters += getNumLetters(i);
		}
		
		System.out.println("Writing 1 to "+ LIMIT + " in words\nhas a total of "+totalNumLetters+" letters");
		
	}

}
