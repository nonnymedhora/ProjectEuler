/**
 * 
 */
package org.bawaweb.euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Navroz
 * 
 * Prime triplets
 * -------------------------------------
	Problem 196
	Ans. 322303240771079935
	Diff. 65%
	
	Build a triangle from all positive integers in the following way:
													row		startVal				formula					#digits
	 1												1		1
	 2  3											2		2						1+(2-1)					2
	 4  5  6										3		4						2+(3-1)					3
	 7  8  9 10										4		7						3+(4-1)					4
	11 12 13 14 15									5		11						7+(5-1)					5
	16 17 18 19 20 21								6		16						11+(6-1)				6
	22 23 24 25 26 27 28							7		22						16+(7-1)				7
	29 30 31 32 33 34 35 36							8		29						22+(8-1)				8
	37 38 39 40 41 42 43 44 45
	46 47 48 49 50 51 52 53 54 55
	56 57 58 59 60 61 62 63 64 65 66
	. . .
	
	Each positive integer has up to eight neighbours in the triangle.
	
	A set of three primes is called a prime triplet 
	if one of the three primes has the other two as neighbours in the triangle.
	
	For example, in the second row, the prime numbers 2 and 3 are elements of some prime triplet.
	
	If row 8 is considered, it contains two primes which are elements of some prime triplet, i.e. 29 and 31.
	If row 9 is considered, it contains only one prime which is an element of some prime triplet: 37.
	
	Define S(n) as the sum of the primes in row n which are elements of any prime triplet.
	Then S(8)=60 and S(9)=37.
	
	You are given that S(10000)=950007619.
	
	Find  S(5678027) + S(7208785).


 *
 */
public class PrimeTriplets {

	private static Map<Integer, Long> 			rowSumPrimeTripletsMap 	= new HashMap<Integer, Long>();
	private static Map<Integer, List<Integer>> 	rowPrimesIndicesMap 	= new HashMap<Integer, List<Integer>>();

	private static long[] getRow(long start, int row) {
		long[] theRow = new long[row];
		for (int i = 0; i < row; i++) {
			theRow[i] = start;
			start += 1;
		}
		return theRow;
	}

	private static long getStart(int row) {
		if (row == 1 || row == 2) {
			return row;
		}
		return ((row - 1) + getStart(row - 1));
	}

	private static List<Integer> getPrimeTripletIndices(final int row) {
		int startRow 	= row - 2;
		int aboveRow 	= row - 1;
		int belowRow 	= row + 1;
		int endRow		= row + 2;

		long s1 	= getStart(startRow);
		long s2 	= getStart(aboveRow);
		long s3 	= getStart(row);
		long s4 	= getStart(belowRow);
		long s5 	= getStart(endRow);

		long[] topRow 	= getRow(s1, startRow);
		long[] above 	= getRow(s2, aboveRow);
		long[] theRow 	= getRow(s3, row);
		long[] below 	= getRow(s4, belowRow);
		long[] botRow 	= getRow(s5, endRow);

		List<Integer> startRowPrimeIndices = new ArrayList<Integer>();
		for (int i = 0; i < topRow.length; i++) {
			if (isPrime(topRow[i])) {
				startRowPrimeIndices.add(i);
			}
		}
		rowPrimesIndicesMap.put(startRow, startRowPrimeIndices);

		List<Integer> aboveRowPrimeIndices = new ArrayList<Integer>();
		for (int i = 0; i < above.length; i++) {
			if (isPrime(above[i])) {
				aboveRowPrimeIndices.add(i);
			}
		}
		rowPrimesIndicesMap.put(aboveRow, aboveRowPrimeIndices);

		List<Integer> rowPrimeIndices = new ArrayList<Integer>();
		for (int i = 0; i < theRow.length; i++) {
			if (isPrime(theRow[i])) {
				rowPrimeIndices.add(i);
			}
		}
		rowPrimesIndicesMap.put(row, rowPrimeIndices);

		List<Integer> belowRowPrimeIndices = new ArrayList<Integer>();
		for (int i = 0; i < below.length; i++) {
			if (isPrime(below[i])) {
				belowRowPrimeIndices.add(i);
			}
		}
		rowPrimesIndicesMap.put(belowRow, belowRowPrimeIndices);

		List<Integer> endRowPrimeIndices = new ArrayList<Integer>();
		for (int i = 0; i < botRow.length; i++) {
			if (isPrime(botRow[i])) {
				endRowPrimeIndices.add(i);
			}
		}
		rowPrimesIndicesMap.put(endRow, endRowPrimeIndices);

		long sumOfPrimeTriplets = 0l;
		List<Integer> primeTripletsIndicesList = new ArrayList<Integer>();

		for (int i = 0; i < rowPrimeIndices.size(); i++) {

			int primesFoundCount = 0;
			int position = rowPrimeIndices.get(i);

			if (position == 0) { // cant-see-left				

				if (aboveRowPrimeIndices.contains(position)) {// above0p
					if (startRowPrimeIndices.contains(position)) {
						primesFoundCount += 1;
					}
					if (startRowPrimeIndices.contains(position + 1)) {
						primesFoundCount += 1;
					}
					if (aboveRowPrimeIndices.contains(position + 1)) {
						primesFoundCount += 1;
					}
					if (rowPrimeIndices.contains(position + 1)) {
						primesFoundCount += 1;
					}
				} // check-above0p

				if (aboveRowPrimeIndices.contains(position + 1)) {// above0p+1
					if (startRowPrimeIndices.contains(position)) {
						primesFoundCount += 1;
					}
					if (startRowPrimeIndices.contains(position + 1)) {
						primesFoundCount += 1;
					}
					if (startRowPrimeIndices.contains(position + 2)) {
						primesFoundCount += 1;
					}
					if (aboveRowPrimeIndices.contains(position)) {
						primesFoundCount += 1;
					}
					if (aboveRowPrimeIndices.contains(position + 2)) {
						primesFoundCount += 1;
					}
					if (rowPrimeIndices.contains(position + 1)) {
						primesFoundCount += 1;
					}
					if (rowPrimeIndices.contains(position + 2)) {
						primesFoundCount += 1;
					}
				} // check=above0p+1

				if (belowRowPrimeIndices.contains(position)) {// below0p
					if (endRowPrimeIndices.contains(position)) {
						primesFoundCount += 1;
					}
					if (endRowPrimeIndices.contains(position + 1)) {
						primesFoundCount += 1;
					}
					if (belowRowPrimeIndices.contains(position + 1)) {
						primesFoundCount += 1;
					}
					if (rowPrimeIndices.contains(position + 1)) {
						primesFoundCount += 1;
					}
				} // check-below0p

				if (belowRowPrimeIndices.contains(position + 1)) {// below0p+1
					if (endRowPrimeIndices.contains(position)) {
						primesFoundCount += 1;
					}
					if (endRowPrimeIndices.contains(position + 1)) {
						primesFoundCount += 1;
					}
					if (endRowPrimeIndices.contains(position + 2)) {
						primesFoundCount += 1;
					}
					primesFoundCount += 1;//?
					if (belowRowPrimeIndices.contains(position)) {
						primesFoundCount += 1;
					}
					if (belowRowPrimeIndices.contains(position + 2)) {
						primesFoundCount += 1;
					}

					if (rowPrimeIndices.contains(position + 1)) {
						primesFoundCount += 1;
					}
					if (rowPrimeIndices.contains(position + 2)) {
						primesFoundCount += 1;
					}
				} // check=below0p+1

				if (rowPrimeIndices.contains(position + 1)) {
					primesFoundCount += 1;
				}

				if (primesFoundCount >= 2) {
					sumOfPrimeTriplets += theRow[position];
					primeTripletsIndicesList.add(position);
				}

			} else { // position-index--not-zero
				

				if (aboveRowPrimeIndices.contains(position - 1)) {// abovePp-1
					if (startRowPrimeIndices.contains(position - 2)) {
						primesFoundCount += 1;
					}
					if (startRowPrimeIndices.contains(position - 1)) {
						primesFoundCount += 1;
					}
					if (startRowPrimeIndices.contains(position)) {
						primesFoundCount += 1;
					}
					primesFoundCount += 1;//?
					if (aboveRowPrimeIndices.contains(position - 2)) {
						primesFoundCount += 1;
					}
					if (aboveRowPrimeIndices.contains(position)) {
						primesFoundCount += 1;
					}

					if (rowPrimeIndices.contains(position - 2)) {
						primesFoundCount += 1;
					}
					if (rowPrimeIndices.contains(position - 1)) {
						primesFoundCount += 1;
					}
				} // check-abovePp-1

				if (aboveRowPrimeIndices.contains(position)) {// abovePp
					if (startRowPrimeIndices.contains(position - 1)) {
						primesFoundCount += 1;
					}
					if (startRowPrimeIndices.contains(position)) {
						primesFoundCount += 1;
					}
					if (startRowPrimeIndices.contains(position + 1)) {
						primesFoundCount += 1;
					}
					if (aboveRowPrimeIndices.contains(position - 1)) {
						primesFoundCount += 1;
					}
					if (aboveRowPrimeIndices.contains(position + 1)) {
						primesFoundCount += 1;
					}
					if (rowPrimeIndices.contains(position - 1)) {
						primesFoundCount += 1;
					}
					if (rowPrimeIndices.contains(position + 1)) {
						primesFoundCount += 1;
					}
				} // check-abovePp

				if (aboveRowPrimeIndices.contains(position + 1)) {// abovePp+1
					if (startRowPrimeIndices.contains(position)) {
						primesFoundCount += 1;
					}
					if (startRowPrimeIndices.contains(position + 1)) {
						primesFoundCount += 1;
					}
					if (startRowPrimeIndices.contains(position + 2)) {
						primesFoundCount += 1;
					}
					if (aboveRowPrimeIndices.contains(position)) {
						primesFoundCount += 1;
					}
					if (aboveRowPrimeIndices.contains(position + 2)) {
						primesFoundCount += 1;
					}
					if (rowPrimeIndices.contains(position + 1)) {
						primesFoundCount += 1;
					}
					if (rowPrimeIndices.contains(position + 2)) {
						primesFoundCount += 1;
					}
				} // check=abovePp+1

				if (belowRowPrimeIndices.contains(position - 1)) {// belowPp-1
					if (endRowPrimeIndices.contains(position - 2)) {
						primesFoundCount += 1;
					}
					if (endRowPrimeIndices.contains(position - 1)) {
						primesFoundCount += 1;
					}
					if (endRowPrimeIndices.contains(position)) {
						primesFoundCount += 1;
					}
					if (belowRowPrimeIndices.contains(position)) {
						primesFoundCount += 1;
					}
					if (belowRowPrimeIndices.contains(position - 2)) {
						primesFoundCount += 1;
					}
					if (rowPrimeIndices.contains(position - 1)) {
						primesFoundCount += 1;
					}
					if (rowPrimeIndices.contains(position - 2)) {
						primesFoundCount += 1;
					}
				} // check-belowPp-1

				if (belowRowPrimeIndices.contains(position)) {// belowPp
					if (endRowPrimeIndices.contains(position - 1)) {
						primesFoundCount += 1;
					}
					if (endRowPrimeIndices.contains(position)) {
						primesFoundCount += 1;
					}
					if (endRowPrimeIndices.contains(position + 1)) {
						primesFoundCount += 1;
					}
					if (belowRowPrimeIndices.contains(position - 1)) {
						primesFoundCount += 1;
					}
					if (belowRowPrimeIndices.contains(position + 1)) {
						primesFoundCount += 1;
					}
					if (rowPrimeIndices.contains(position - 1)) {
						primesFoundCount += 1;
					}
					if (rowPrimeIndices.contains(position + 1)) {
						primesFoundCount += 1;
					}
				} // check-belowPp

				if (belowRowPrimeIndices.contains(position + 1)) {// belowPp+1
					if (endRowPrimeIndices.contains(position)) {
						primesFoundCount += 1;
					}
					if (endRowPrimeIndices.contains(position + 1)) {
						primesFoundCount += 1;
					}
					if (endRowPrimeIndices.contains(position + 2)) {
						primesFoundCount += 1;
					}
					if (belowRowPrimeIndices.contains(position)) {
						primesFoundCount += 1;
					}
					if (belowRowPrimeIndices.contains(position + 2)) {
						primesFoundCount += 1;
					}
					if (rowPrimeIndices.contains(position + 1)) {
						primesFoundCount += 1;
					}
					if (rowPrimeIndices.contains(position + 2)) {
						primesFoundCount += 1;
					}
				} // check-belowPp+1
				

				if (rowPrimeIndices.contains(position - 1)) {
					primesFoundCount += 1;
				}

				if (rowPrimeIndices.contains(position + 1)) {
					primesFoundCount += 1;
				}
				if (primesFoundCount >= 2) {
					sumOfPrimeTriplets += theRow[position];
					primeTripletsIndicesList.add(position);
				}
			}//endselse
		}

		rowSumPrimeTripletsMap.put(row, sumOfPrimeTriplets);

		return primeTripletsIndicesList;

	}

	private static long getSumOfPrimesIndices(int row) {

		return 0l;
	}

	public static void main(String[] args) {
//		int sampleRow = 8;
//
//		System.out.println("start of row " + sampleRow + " -- " + getStart(sampleRow));
//		System.out.println("Row " + sampleRow + " is " + printRow(getRow(getStart(sampleRow), sampleRow)));
//		System.out.println("PrimeTripletIndices (row " + sampleRow + ")are: " + printList(getPrimeTripletIndices(sampleRow)));
//		System.out.println("Sum from map is " + rowSumPrimeTripletsMap.get(sampleRow));
//		System.out.println("_________________________________________________");
		
		/*List<Integer> primeTripletIndices4Row = getPrimeTripletIndices(sampleRow);
		long start = getStart(sampleRow);
		long sum = 0l;
		Iterator<Integer> iter = primeTripletIndices4Row.iterator();
		while(iter.hasNext()){
			int index = iter.next();			
			sum += start+index;
		}
		System.out.println("Sum for row "+sampleRow+" is == "+sum);*/
		
		
		
//		sampleRow = 9;
//
//		System.out.println("start of row " + sampleRow + " -- " + getStart(sampleRow));
//		System.out.println("Row " + sampleRow + " is " + printRow(getRow(getStart(sampleRow), sampleRow)));
//		System.out.println("PrimeTripletIndices (row " + sampleRow + ")are: " + printList(getPrimeTripletIndices(sampleRow)));
//		System.out.println("Sum from map is " + rowSumPrimeTripletsMap.get(sampleRow));
//		System.out.println("_________________________________________________");

		int sampleRow = 10000;
		/*
		System.out.println("start of row " + sampleRow + " -- " + getStart(sampleRow));
		System.out.println("Row " + sampleRow + " is " + printRow(getRow(getStart(sampleRow), sampleRow)));
		getPrimeTripletIndices(sampleRow);
		//System.out.println( "PrimeTripletIndices (row " + sampleRow + ")are:" + printList(getPrimeTripletIndices(sampleRow)));
		long summmm = rowSumPrimeTripletsMap.get(sampleRow);
		System.out.println("Sum is " + summmm);
		System.out.println(" Equal to 950007619 " + (summmm == 950007619));*/
		
		List<Integer> primeTripletIndices4Row = getPrimeTripletIndices(sampleRow);
		long start = getStart(sampleRow);System.out.println("Start for row "+sampleRow+" is "+start);
		long sum = 0l;
		Iterator<Integer> iter = primeTripletIndices4Row.iterator();
		while(iter.hasNext()){
			int index = iter.next();			
			sum += start+index;
		}
		System.out.println("Sum for row "+sampleRow+" (using-primeTripletIndices4Row-list) is == "+sum);
		System.out.println("Sum from map is " + rowSumPrimeTripletsMap.get(sampleRow));

	}

	private static String printRow(long[] row) {
		String s = "";
		for (int i = 0; i < row.length; i++) {
			s += row[i] + " ";
		}
		return s;
	}

	private static String printList(List<Integer> list) {
		String s = "";
		Iterator<Integer> iter = list.iterator();
		while (iter.hasNext()) {
			s += iter.next() + " ";
		}
		return s;
	}

	private static boolean isPrime(final long number) {
		boolean isPrime = true;
		if (number < 0) {
			System.out.println("Invalid-Number	" + number);
			return false;
		}

		if (number == 1 || number == 2 || number == 3)
			return true;

		for (long i = 2; i < ((long) Math.sqrt(number) + 1); i++) {
			if (number % i == 0l)
				return false;
		}
		return isPrime;
	}

}
