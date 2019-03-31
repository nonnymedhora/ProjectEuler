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
	
	Find the first four consecutive integers to have four distinct prime factors each. 
	What is the first of these numbers?

 *
 */
public class DistinctPrimeFactors {

	
	public static void main(String[] args) {
		DistinctPrimeFactors dpf = new DistinctPrimeFactors();
//		List<Long> distinctPrimeFactorList = new ArrayList<Long>();
		
		boolean found = false;
		
		
		long i = 644l;//(134043-4);//1;//
		long j = 645l;//(134043-3);//2;//
		long k = 646l;//(134043-2);//3;//
		long l = 647l;//(134043-1);//4;//
		
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
							boolean check = dpf.checkUniqueness( iList,jList );				//i-j
							if(!check) {
								continue;
							} else {
								check = dpf.checkUniqueness( iList,kList );					//i-k
								if(!check) {
									continue;									
								} else {
									check = dpf.checkUniqueness( iList,lList );				//i-l
									if(!check) {
										continue;
									} else {
										check = dpf.checkUniqueness(jList,kList);			//j-k
										if(!check) {
											continue;
										} else {
											check = dpf.checkUniqueness(jList,lList);		//j-l
											if(!check) {
												continue;
											} else {
												check = dpf.checkUniqueness(kList,lList);	//k-l
												if(!check) {
													continue;
												} else {
													found = true;
													break;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		System.out.println("Smallest consecutive numbers with "+size+" distinct prime factors");
		System.out.println(i +", "+j+", "+k+", "+l);
		
	}
	
	private boolean checkUniqueness(List<Long> aList, List<Long> bList) {
		final int aSize = aList.size();
		for(int i = 0; i < aSize; i++){
			if( bList.contains(aList.get(i)) ) {
				return false;
			}
		}
		return true;
	}

	private List<Long> getPrimeFactors(final long num) {
		List<Long> list = new ArrayList<Long>();
		
		List<Long> pList = getPrimesTill(num);
		Iterator<Long> iter = pList.iterator();
		while( iter.hasNext() ){
			final Long next = iter.next();
			if( (next!=1) && (num % next == 0) ){
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

}
