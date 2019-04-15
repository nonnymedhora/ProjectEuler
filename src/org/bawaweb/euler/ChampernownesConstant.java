/**
 * 
 */
package org.bawaweb.euler;

/**
 * @author Navroz
 * 
 * Champernowne's constant
 * =============================
	Problem 40
	Ans. 210
	
	An irrational decimal fraction is created by concatenating the positive integers:
	
	0.123456789101112131415161718192021...
	
	It can be seen that the 12th digit of the fractional part is 1.
	
	If d(n) represents the nth digit of the fractional part, 
	find the value of the following expression.
	
	d(1) × d(10) × d(100) × d(1000) × d(10000) × d(100000) × d(1000000)

 * Output
 * ==================================
 * 	d1 = 1
	d2 = 1
	d3 = 5
	d4 = 3
	d5 = 7
	d6 = 2
	d7 = 1
	(d1*d2*d3*d4*d5*d6*d7) = 210

 *
 */
public class ChampernownesConstant {
	
	final static int 		target 	= 1000000;
	final static String 	start 	= "0.";

	public static void main(String[] args) {
		String theString = createIrrationalNum();

		int base = start.length();

		int d1 = Character.getNumericValue( theString.charAt( base) );
		int d2 = Character.getNumericValue( theString.charAt( (base + 10) - 1) );
		int d3 = Character.getNumericValue( theString.charAt( (base + 100) - 1) );
		int d4 = Character.getNumericValue( theString.charAt( (base + 1000) - 1) );
		int d5 = Character.getNumericValue( theString.charAt( (base + 10000) - 1) );
		int d6 = Character.getNumericValue( theString.charAt( (base + 100000) - 1) );
		int d7 = Character.getNumericValue( theString.charAt( (base + target) - 1) );

		System.out.println("d1 = " + d1);
		System.out.println("d2 = " + d2);
		System.out.println("d3 = " + d3);
		System.out.println("d4 = " + d4);
		System.out.println("d5 = " + d5);
		System.out.println("d6 = " + d6);
		System.out.println("d7 = " + d7);

		System.out.println("(d1*d2*d3*d4*d5*d6*d7) = " + (d1 * d2 * d3 * d4 * d5 * d6 * d7));

	}

	private static String createIrrationalNum() {
		StringBuilder s = new StringBuilder(start);
		int i = 1;
		final int limit = target + start.length();
		while ( s.length() <= limit ) {
			s.append(String.valueOf(i));
			i	+= 1;
		}
		
		return s.toString();
	}

}
