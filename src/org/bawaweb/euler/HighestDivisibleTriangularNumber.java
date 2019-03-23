/**
 * 
 */
package org.bawaweb.euler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Navroz
 * https://projecteuler.net/problem=12
 * 
 * Highly divisible triangular number
 * ---------------------------------------
 * Problem 12
 * 
 * The sequence of triangle numbers is generated 
 * by adding the natural numbers. 
 * So the 7th triangle number would be 
 * 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28. 
 * 
 * The first ten terms would be:
 * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
 * 
 * Let us list the factors of the first seven triangle numbers:

     1: 1
     3: 1,3
     6: 1,2,3,6
    10: 1,2,5,10
    15: 1,3,5,15
    21: 1,3,7,21
    28: 1,2,4,7,14,28
    
 * We can see that 28 is the first triangle number 
 * to have over five divisors.
 * 
 * What is the value of the first triangle number 
 * to have over five hundred divisors?
 *
 */
public class HighestDivisibleTriangularNumber {
	
	static final int target = 5;//500;
	
	static List<Integer> getDivisors(int x) {
		List<Integer> theList = new ArrayList<Integer>();
		for(int i = 1; i <= x; i++) {
			if(x%i == 0){
				theList.add(i);
			}
		}
		return theList;		
	}



	public static void main(String[] args) {
		int natNum = 1;
		int term = 1;
		List<Integer> aList = getDivisors(term);
		while(aList.size()<=target){
			natNum += 1;
			term += natNum;
//			if(term==Integer.MAX_VALUE){System.out.println("MAX REACHED");}
			aList = getDivisors(term);
		}
		System.out.println(term+ " is a Triangular Number and has "+target+" divisors");
		System.out.println("They are:");
		printList(aList);

	}



	private static void printList(List<Integer> aList) {
		String s = "";
		for(int i = 0; i < aList.size(); i++){
			if(i==aList.size()-1){
				s+=aList.get(i);
			}else{
				s+=aList.get(i)+", ";
			}
		}
		System.out.println(s);
	}

}
