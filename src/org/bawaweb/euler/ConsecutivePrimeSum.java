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
 * Consecutive prime sum
 * Ans. 997651
 * ------------------------
	Problem 50
	
	The prime 41, can be written as the sum of six consecutive primes:
	41 = 2 + 3 + 5 + 7 + 11 + 13
	
	This is the longest sum of consecutive primes that adds to a prime below one-hundred.	
	The longest sum of consecutive primes below one-thousand that adds to a prime, 
	contains 21 terms, and is equal to 953.
	
	Which prime, below one-million, can be written as the sum of the most consecutive primes?

 *
 */
public class ConsecutivePrimeSum {
	
	private static final int target = 1000000;	// 1 million

	public static void main(String[] args) {
		ConsecutivePrimeSum cps = new ConsecutivePrimeSum();
		int term = 0;
		List<Integer> maxList = new ArrayList<Integer>();
		List<Integer> primesTillN = new ArrayList<Integer>(); 
		for (int i = 1; i <= target; i++) {
			if (cps.isPrime(i)) {
				primesTillN.add(i);
				List<Integer> primeTerms = cps.getPrimeChainToSumN(i, primesTillN);
				if (primeTerms != null && primeTerms.size() > 0 && !primeTerms.contains(1)) {

					if (primeTerms.size() > maxList.size()) {
						maxList = primeTerms;
						term = i;
					}
				}
			}
		}

		System.out.println(term+" has the longest chain of consecutive primes - size: "+maxList.size());
		cps.printList(maxList);
	}
	
	
	private List<Integer> getPrimeChainToSumN(int n, List<Integer> primes) {
		List<Integer> primeChain = new ArrayList<Integer>();
		int primesChainSum = 0;
		int start = 0;
		while (primesChainSum <= n) {
			primesChainSum = 0;
			for (int i = start; i < primes.size(); i++) {
				primesChainSum += primes.get(i);
				primeChain.add(primes.get(i));
				if (primesChainSum >= n)
					break;

			}
			if (primesChainSum == n) {
				return primeChain;
			} else {
				primesChainSum = 0;
				primeChain = new ArrayList<Integer>();
				start += 1;
			}
		}
		return null;
	}
	
	private boolean isPrime(final int number) {
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
	
	private void printList(List<Integer> list) {
		String listStr = "";
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()) {
			listStr += it.next() + " ";
		}
		System.out.println(listStr);
		System.out.println("===============================");
	}

}
