/**
 * 
 */
package org.bawaweb.euler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Navroz
 * 
 * 
 * Goldbach's other conjecture
 * Ans---5777
 * ----------------------------
 * Problem 46
 * It was proposed by Christian Goldbach that 
 * 		every odd composite number 
 * 		can be written as the sum of 
 * 			a prime and twice a square.
 * 
 * 
 * 		 9 	=  7 + 2×1^2
 * 		15 	=  7 + 2×2^2
 * 		21 	=  3 + 2×3^2
 * 		25 	=  7 + 2×3^2
 * 		27 	= 19 + 2×2^2
 * 		33 	= 31 + 2×1^2
 * 
 * "It turns out that the conjecture was false."
 * 
 * What is the smallest odd composite 
 * that cannot be written 
 * as the sum of a prime and twice a square?
 * 
 * 
 * Note--
 * An odd number is any number not divisible by two. 
 * A composite number is a number with more than one prime factor. 
 * So an odd composite has two or more prime factors, none of which are two.
 * 
 * 
 * Output
 * -------
 * 	Smallest odd composite number which fails Goldbach's Conjecture -- 5777
 * 	is odd composite true
 * 	is Goldbach -- false
 *
 */
public class GoldbachsOddConjecture {

	private boolean isOdd(long num) {
		return (num%2!=0);
	}
	
	private boolean isOddComposite(long num) {
		if(isOdd(num)) {
			List<Long> primesList = getPrimesTill(num);
			for(int i = 0; i < primesList.size()-1; i++) {
				for(int j = 1; j < primesList.size(); j++) {					
					if (i!=j) {
						final Long prime1 = primesList.get(i);
						final Long prime2 = primesList.get(j);
						if ((prime1!=1&&prime2!=1) && prime1 * prime2 == num) {
							return true;
						} 
					}
				}
			}
		}
		return false;
	}
	
	private List<Long> getPrimesTill(final long number) {
		List<Long> list = new ArrayList<Long>();
		for(long i = 1l; i <= number; i++){
			if( isPrime(i) ){
				list.add(i);
			}
		}
		return list;		
	}
	
	private boolean processGoldbach(final long num) {
		List<Long> primesList = getPrimesTill(num);
		Collections.reverse(primesList);
		boolean found = false;
		if( isOddComposite(num) ) {
			
			Iterator<Long> iter = primesList.iterator();
			while( iter.hasNext() ) {
				long prime = iter.next();
				
				for(int i = 1; i < num; i++){
					long gbn = process(prime,i);
					if(num == gbn){
						return found = true;
					}
				}
				
			}//System.out.println("num-is---"+num);
		}
		return found;
	}
	
	private long process(final long prime, final int num) {		
		return (prime + 2 * (long)Math.pow(num,2));
	}

	private boolean isPrime(final long number) {
		boolean isPrime = true;
		if (number < 0)
			System.out.println("Invalid-Number	" + number);

		if (number == 1 || number == 2 || number == 3)
			return true;

		for (long i = 2; i < ((long) Math.sqrt(number) + 1); i++) {
			if (number % i == 0l)
				return false;
		}
		return isPrime;
	}
	
	public static void main(String[] args) {
		GoldbachsOddConjecture goc = new GoldbachsOddConjecture();
		boolean isFound = true;
		long num = 1;
		while( isFound ) {
			if ( goc.isOddComposite(num) ) {
				if ( !goc.processGoldbach(num) ) {
					isFound = false;
					break;
				} 
			}
			num++;
		}
		
		System.out.println("Smallest odd composite number which fails Goldbach's Odd Conjecture -- "+num);
//		System.out.println(goc.print2String(goc.getPrimesTill(num)));
		System.out.println("is odd composite "+goc.isOddComposite(num));
		System.out.println("is Goldbach -- "+goc.processGoldbach(num));

	}

	private String print2String(List<Long> list) {
		final int size = list.size();
		String s = "";
		for(int i = 0; i < size; i++){
			s += list.get(i) + " ";
		}
		return s;
	}

}



/**
 * The sum of any two prime numbers (greater than two) is always even
 * 
 * LogicalProof
 * 		O(n)	--	set of all odd numbers,			for all n>2 where n%2 != 0
 * 		P(n)	--	set of all prime numbers,		any prime number greater than 2 is odd  
 * 													P(n) subset of O(n)
 * 
 * 		Any odd number added to any other odd number is ALWAYS even
 * So -	Any prime number added to an other prime number is also even
 * 
 * 		A = (2 * x) + 1;
 * 		B = (2 * y) + 1;
 * 
 * 		A+B	=	((2 * x) + 1) + ((2 * y) + 1)		-->	2 * (x + y) + 2
 * 			=	2 * (x + y + 1)
 * 			EVEN
 * 
 * 
 * Also
 * ------
 * Conjecture -	Only one of two consecutive numbers can be prime
 * For any two consecutive numbers: one is even, the other is odd
 * Since
 * for	n,	n+1
 * 	if	n 	is even	--	then its not prime,	n+1 is odd
 * 	if 	n+1 is even	--	then its not prime,	n is odd
 */
