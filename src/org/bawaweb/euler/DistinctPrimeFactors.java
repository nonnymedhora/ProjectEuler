/**
 * 
 */
package org.bawaweb.euler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Navroz
 * 
 * Distinct primes factors
 * -----------------------------
 * Ans. 134043;
	Problem 47

	The first two consecutive numbers to have two distinct prime factors are:
	
	14 = 2 × 7
	15 = 3 × 5
	
	The first three consecutive numbers to have three distinct prime factors are:
	
	644 = 2² × 7 × 23
	645 = 3 × 5 × 43
	646 = 2 × 17 × 19.		
			[Q?n	how is it distinct -- 2 is prime factor for both 646 and 644]
			[appears to seek distinct primes only for the number ---
			not as a distinct prime factor for the three consecutive numbers]
	
	Find the first four consecutive integers to have four distinct prime factors each. 
	What is the first of these numbers?
	
	Output
	---------------------------------------------------------------
	Smallest consecutive numbers with 4 distinct prime factors
	134043, 134044, 134045, 134046
	---------------------------------------------------------------
	Time ~= 1hour	(starting from 644)

 *
 */
public class DistinctPrimeFactors {

	
	public static void main(String[] args) {
		DistinctPrimeFactors dpf = new DistinctPrimeFactors();
		
		boolean found = false;
		
		
		long i = 644l;
		long j = 645l;
		long k = 646l;
		long l = 647l;
		
		final int size = 4;
		
		while( !found ) {	
			i++;
			j++;
			k++;
			l++;													
			
			List<Long> iList = dpf.getPrimeFactors(i);				
			if( iList.size() == size ) {
				List<Long> jList = dpf.getPrimeFactors(j);			
				if( jList.size() == size ) {
					List<Long> kList = dpf.getPrimeFactors(k);		
					if( kList.size() == size ) {
						List<Long> lList = dpf.getPrimeFactors(l);	
						if( lList.size() == size ) {
							found = true;
							break;
							
//							REMOVING Uniqueness Constraint Checks below
//							============================================
//							boolean check = dpf.checkUniqueness( iList,jList );				//i-j
//							if(!check) {
//								continue;
//							} else {
//								check = dpf.checkUniqueness( iList,kList );					//i-k
//								if(!check) {
//									continue;									
//								} else {
//									check = dpf.checkUniqueness( iList,lList );				//i-l
//									if(!check) {
//										continue;
//									} else {
//										check = dpf.checkUniqueness(jList,kList);			//j-k
//										if(!check) {
//											continue;
//										} else {
//											check = dpf.checkUniqueness(jList,lList);		//j-l
//											if(!check) {
//												continue;
//											} else {
//												check = dpf.checkUniqueness(kList,lList);	//k-l
//												if(!check) {
//													continue;
//												} else {
//													System.out.println("FOUND i is "+i);
//													found = true;
//													break;
//												}
//											}
//										}
//									}
//								}
//							}
//							ENDS	REMOVING U
							
						}
					}
				}
			}
		}
		
		System.out.println("Smallest consecutive numbers with "+size+" distinct prime factors");
		System.out.println(i +", "+j+", "+k+", "+l);
		
	}
	
	//from problem statement  doesn't appear that uniqueness/distinctness occurs
	//644 = 2² × 7 × 23			&&
	//646 = 2 × 17 × 19.			
	//2 appears for both 644 and 646 as a prime factor
	//so not calling this method
	//	Note---	Having ALL distinct prime factors for consecutive numbers
	//		is IMPOSSIBLE
	//		2,3,5	??
	private boolean checkUniqueness(List<Long> aList, List<Long> bList) {
		//amending-for-2
		final int aSize = aList.size();
		for(int i = 0; i < aSize; i++){
			if( aList.get(i) != 2 ) {
				if( bList.contains( aList.get(i) ) )  {
					return false;
				}
			}
		}
		return true;
	}

	//TODO	rename method - getDistinctPrimeFactors
	private List<Long> getPrimeFactors(final long num) {
		List<Long> list = new ArrayList<Long>();
		
		List<Long> pList = getPrimesTill(num/2);			//	factor(n) !> n/2,
		Iterator<Long> iter = pList.iterator();
		while( iter.hasNext() ){
			final Long next = iter.next();
			if( ( next != 1 ) && ( num % next == 0 ) ){
				list.add(next);
			}
		}
		return list;
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
	
	private void printList(List<Long> list) {
		String listStr = "";
		Iterator<Long> it = list.iterator();
		while(it.hasNext()) {
			listStr += it.next() + " ";
		}
		System.out.println(listStr);
		System.out.println("===============================");
	}

}
