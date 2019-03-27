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
 * 
 *  https://projecteuler.net/problem=95
 * 
 *  Amicable Chains 
 *  ---------------
 * 
 *  Problem 95
 * 
 *  The proper divisors of a number are all the divisors excluding the
 *  number itself. For example, the proper divisors of 28 are 1, 2, 4, 7,
 *  and 14. As the sum of these divisors is equal to 28, we call it a
 *  perfect number.
 * 
 *  Interestingly the sum of the proper divisors of 220 is 284 and the
 *  sum of the proper divisors of 284 is 220, forming a chain of two
 *  numbers.
 * 
 *  For this reason, 220 and 284 are called an amicable pair.
 * 
 *  Perhaps less well known are longer chains. For example, starting with
 *  12496, we form a chain of five numbers: 12496 → 14288 → 15472 → 14536
 *  → 14264 (→ 12496 → ...) Since this chain returns to its starting
 *  point, it is called an amicable chain.
 * 
 *  Find the smallest member of the longest amicable chain with no
 *  element exceeding one million.
 *
 */
public class AmicableChains {
	private static final int MAX_VALUE = 1000000;		//	1 Million

	private static List<Integer> getDivisors(int num) {
		List<Integer> theList = new ArrayList<Integer>();
		for(int i = 1; i <= num/2; i++) {
			if(num%i==0){
				theList.add(i);
			}
		}
		return theList;
	}
	
	private static int getSmallestValue(List<Integer> aList) {
		int smallestValue = Integer.MAX_VALUE;
		Iterator<Integer> iter = aList.iterator();
		while( iter.hasNext() ) {
			int listVal = iter.next();
			if(listVal < smallestValue) {
				smallestValue = listVal;
			}
		}
		return smallestValue;
	}

	private static int getSumOfList(List<Integer> divList) {
		int sum = 0;
		Iterator<Integer> it = divList.iterator();
		while(it.hasNext()){
			sum += it.next();
		}
		return sum;
	}
	
	private static void printList(List<Integer> aList) {
		String s = "";
		for(int i = 0; i < aList.size(); i++){
			if(i==aList.size()-1){
				s+=aList.get(i);
			}else{
				s+=aList.get(i)+" → ";//→
			}
		}
		System.out.println(s);
	}
	

	private static List<Integer> getAmicableChain(int num) {
		List<Integer> aList = new ArrayList<Integer>();
		boolean broken = false;
		int start = num;
		aList.add(num);
		while( !broken ){
			List<Integer> divList = getDivisors(start);
			int sumOfDivsList = getSumOfList(divList);
			if ( sumOfDivsList < MAX_VALUE && sumOfDivsList != 0) {
				if ( !aList.contains(sumOfDivsList) ) {
					aList.add(sumOfDivsList);
					start = sumOfDivsList;
				} else {
					int index = aList.indexOf(sumOfDivsList);
					aList = aList.subList(index,aList.size()-1);
					broken = true;
					break;
				} 
			} else {
				return null;
			}
		}
		if(!broken){
			return null;
		}
		return aList;
	}
	
	private static boolean isPrime(final long number) {
		if (number < 0) {
			System.out.println("Invalid-Number	" + number);
			return false;
		}

		if (number == 1 || number == 2 || number == 3)
			return true;
		
		if(number%2==0){
			return false;
		}
		if(number%3==0){
			return false;
		}
		if(number%5==0){
			return false;
		}
		if(number%7==0){
			return false;
		}
		if(number%11==0){
			return false;
		}
		if(number%13==0){
			return false;
		}
		if(number%17==0){
			return false;
		}
		if(number%19==0){
			return false;
		}
		if(number%23==0){
			return false;
		}
		
		boolean isPrime = true;
		for (long i = 2; i < ((long) Math.sqrt(number) + 1); i++) {
			if (number % i == 0)
				return false;
		}

		return isPrime;
	}
	
	
	public static void main(String[] args) {
		int longestChainSize = -1;
		List<Integer> longestAmicableChain = new ArrayList<Integer>();
		
		int aNum = 1;

		while ( aNum<20000 ) {
			if ( !isPrime(aNum) ) {
				List<Integer> amicableChain = getAmicableChain(aNum);
				if (amicableChain != null) {
					int chainSize = amicableChain.size();
					if (chainSize > longestChainSize) {
						longestChainSize = chainSize;
						longestAmicableChain = amicableChain;
					}
				} 
			}
			aNum++;
		}
		
		System.out.println("Longest Chain has length --> "+longestAmicableChain.size());
		printList(longestAmicableChain);
		if (longestAmicableChain.size()>0) {
			System.out.println("Smallest Value is in the chain is --> " + getSmallestValue(longestAmicableChain));
		}

	}

}
