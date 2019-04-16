/**
 * 
 */
package org.bawaweb.euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Navroz
 * 
 * Same differences
 * ===========================
	Problem 135
	Ans. 4989
	
	Given the positive integers, x, y, and z, 
	are consecutive terms of an arithmetic progression, 
	the least value of the positive integer, n, 
	for which the equation, x^2 − y^2 − z^2 = n, 
	
	has exactly two solutions is n = 27:
	
	34^2 − 27^2 − 20^2 = 12^2 − 9^2 − 6^2 = 27
	
	It turns out that n = 1155 is the least value which has exactly ten solutions.
	
	How many values of n less than one million have exactly ten distinct solutions?

 *
 */
public class SameDifferences {
	private static final int target = 1000000;
	private static final int numSols = 10;
	private static Map<Integer,List<int[]>> solutionMap = new HashMap<Integer,List<int[]>>();
	
	public static void main(String[] args) {
		int num10Solns = 0;
		List<Integer> solsList = new ArrayList<Integer>();
		generateAP(target);
		for (int key : solutionMap.keySet()) {
			List<int[]> vals = solutionMap.get(key);
			if (vals.size() == numSols) {
				num10Solns += 1;
				solsList.add(key);
			}
		}

		System.out.println("From 1 to " + target + 
							" there are " + num10Solns + 
							" values, solsList.size is " 
							+ solsList.size());
		System.out.println("where x^2 - y^2 - z^2 = n, and n has exactly " + 
							numSols + " solutions");
		printIntList(solsList);
		System.out.println("=========================================================");
		for (int sol : solsList) {
			System.out.println("-------" + sol + "--------");
			printList(solutionMap.get(sol));
			System.out.println("==========================");
		}

	}
	
	private static void generateAP(final int n) {

		for (int diff = 1; diff <= (n / 2); diff++) {
			for (int num = 1; num <= (n - diff); num++) {
				int[] prog = new int[3];
				if ((num + (2 * diff)) <= n) {
					prog[0] = (num + (2 * diff));
					prog[1] = (num + diff);
					prog[2] = num;

					int key = process(prog);
					if (key > 0 && key <= target) {
						add2SolutionMap(key, prog);
					}
				}
			}
		}
	}
	
	private static int process(final int[] arr){		
		int n = 0;
		return (int)Math.pow(arr[0],2) - (int)Math.pow(arr[1],2) - (int)Math.pow(arr[2],2);
		
	}
	
	private static void add2SolutionMap(int key, int[] val) {
		if (solutionMap.get(key) == null) {
			List<int[]> aList = new ArrayList<int[]>();
			aList.add(val);
			solutionMap.put(key, aList);
		} else {
			List<int[]> valList = solutionMap.get(key);
			if (!valList.contains(val)) {
				valList.add(val);
				solutionMap.put(key, valList);
			}
		}
	}
	
	private static void printIntList(List<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			int arr = list.get(i);
			System.out.print(arr + " ");
		}
	}

	
	private static void printList(List<int[]> list) {
		for (int i = 0; i < list.size(); i++) {
			int[] arr = list.get(i);
			printArray(arr);
		}
	}

	private static void printArray(int[] arr) {
		String s = "";
		for (int i = 0; i < arr.length; i++) {
			s += arr[i] + " ";
		}
		System.out.println(s);
	}

}
