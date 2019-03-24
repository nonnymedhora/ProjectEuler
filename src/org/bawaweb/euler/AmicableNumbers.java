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
public class AmicableNumbers {
	
	private static int LIMIT = 10000;
	
	private static List<Integer> getDivisors(int num) {
		List<Integer> theList = new ArrayList<Integer>();
		for(int i = 1; i <= num/2; i++) {
			if(num%i==0){
				theList.add(i);
			}
		}
		return theList;
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
				s+=aList.get(i)+"  ";//→
			}
		}
		System.out.println(s);
	}

	public static void main(String[] args) {
		int sumAmicables = 0;
		List<Integer> amicableList = new ArrayList<Integer>();
		
		for(int i = 1; i < LIMIT; i++) {
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
		System.out.println("The Amicable Numbers till "+LIMIT+" are:");
		printList(amicableList);
		System.out.println("\n-----------------------------------\n"+
							"Sum of ALL Amicable Numbers till "+LIMIT+
							"\n is "+getSumOfList(amicableList));
	}

}
