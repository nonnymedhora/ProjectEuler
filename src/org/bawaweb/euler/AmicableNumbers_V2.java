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
 * Amicable numbers
 * ---------------
 * Problem 21
 * 
 * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
 * If d(a) = b and d(b) = a, where a ≠ b, 
 * then a and b are an amicable pair 
 * and each of a and b are called amicable numbers.
 * 
 * For example, 
 * the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; 
 * therefore d(220) = 284. 
 * The proper divisors of 284 are 1, 2, 4, 71 and 142; 
 * so d(284) = 220.
 * 
 * Evaluate the sum of all the amicable numbers under 10000.
 * 
 * OUTPUT
 * =========================================================
 * The Amicable Numbers till 10000 are:
 * 220  284  1184  1210  2620  2924  5020  5564  6232  6368
 * 
 * -----------------------------------
 * Sum of ALL Amicable Numbers till 10000
 * is 31626
 * ========================================================= *
 */
public class AmicableNumbers_V2 {
	
	public AmicableNumbers_V2() {
	}
	
	private List<Integer> getDivisors(int num) {
		List<Integer> theList = new ArrayList<Integer>();
		for(int i = 1; i <= num/2; i++) {
			if(num%i==0){
				theList.add(i);
			}
		}
		return theList;
	}

	private int getSumOfList(List<Integer> divList) {
		int sum = 0;
		Iterator<Integer> it = divList.iterator();
		while(it.hasNext()){
			sum += it.next();
		}
		return sum;
	}
	
	private void printList(List<Integer> aList) {
		String s = "";
		for(int i = 0; i < aList.size(); i++){
			if(i==aList.size()-1){
				s+=aList.get(i);
			}else{
				s+=aList.get(i)+"  ";//→
			}
		}
		System.out.println(s);
	}

	public List<Integer> getAmicableList(int limit) {
		List<Integer> amicableList = new ArrayList<Integer>();		
		for(int i = 1; i < limit; i++) {
			if (!amicableList.contains(i)) {
				List<Integer> divList = getDivisors(i);
				int divSum = getSumOfList(divList);
				if (i == getSumOfList(getDivisors(divSum))) {
					if (i != divSum) {
						amicableList.add(i); //	Amicables always comes in pairs
						amicableList.add(divSum);
					}
				} 
			}
		}
		return amicableList;
	}

	public List<Integer> getAmicableList(int start, int limit) {
		List<Integer> amicableList = new ArrayList<Integer>();		
		for(int i = start; i < limit; i++) {
			if (!amicableList.contains(i)) {
				List<Integer> divList = getDivisors(i);
				int divSum = getSumOfList(divList);
				if (i == getSumOfList(getDivisors(divSum))) {
					if (i != divSum) {
						amicableList.add(i); //	Amicables always comes in pairs
						amicableList.add(divSum);
					}
				} 
			}
		}
		return amicableList;
	}

	// for Problem 95 -- Amicable Chains
	public List<Integer> getAmicableList(int start, int limit, int maxValue) {
		List<Integer> amicableList = new ArrayList<Integer>();	
		
		if (start != -1 && limit != -1) {
			for (int i = start; i < limit; i++) {
				if (!amicableList.contains(i)) {
					List<Integer> divList = getDivisors(i);
					int divSum = getSumOfList(divList);
					if (divSum <= maxValue) {
						if (i == getSumOfList(getDivisors(divSum))) {
							if (i != divSum) {
								amicableList.add(i); //	Amicables always comes in pairs
								amicableList.add(divSum);
							}
						}
					} else {
						return null;
					}
				}
			} 
		} else {
			// find an amicable list whose values cannot exceed maxValue
			
		}
		return amicableList;
	}

	public static void main(String[] args) {
		int sumAmicables = 0;
		final int max = 10000;
		AmicableNumbers_V2 an = new AmicableNumbers_V2();
		List<Integer> amicableList = an.getAmicableList(1, max);
		System.out.println("The Amicable Numbers till "+max+" are:");
		an.printList(amicableList);
		sumAmicables = an.getSumOfList(amicableList);
		System.out.println("\n-----------------------------------\n"+
							"Sum of ALL Amicable Numbers till "+max+
							"\n is "+sumAmicables);
	}

}
