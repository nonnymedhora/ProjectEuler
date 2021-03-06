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
 * 
 * Zeckendorf Representation
 * -----------------------
 * 
 * Problem 297
 * 
 * Each new term in the Fibonacci sequence is generated by adding the previous two terms.
 * Starting with 1 and 2, the first 10 terms will be: 1, 2, 3, 5, 8, 13, 21, 34, 55, 89.
 * 
 * Every positive integer can be uniquely written as a sum of nonconsecutive terms of the Fibonacci sequence. 
 * For example, 100 = 3 + 8 + 89.
 * Such a sum is called the Zeckendorf representation of the number.
 * 
 * For any integer n>0, let z(n) be the number of terms in the Zeckendorf representation of n.
 * Thus, z(5) = 1, z(14) = 2, z(100) = 3 etc.
 * 
 * Also, for 0<n<10^6, ∑ z(n) = 7894453.
 * 
 * Find ∑ z(n) for 0<n<10^17.
 *
 */
public class ZeckendorfRepresentation {
	
//	static int n1 = 0, n2 = 1, n3 = 0;
	static final double target = 100;//Math.pow(10,6);//106;

	int n1 = 0, n2 = 1, n3 = 0;
 	private void printFibonacci(int count) {
		if (count > 0) {
			n3 = n1 + n2;
			n1 = n2;
			n2 = n3;
			System.out.print(" " + n3);
			printFibonacci(count - 1);
		}
		//System.out.println("-----------------------");
	}
	
 	/**
 	 * Retrieves a list of Fibonacci numbers less than or equal to count
 	 * @param count		--	maximum possible value of the Fibonacci number
 	 * @return			--	list of Fibonacci numbers
 	 */
	private List<Integer> getFibonacciList(int count) {
		int n1 = 0, n2 = 1, n3 = 0;
		List<Integer> list = new ArrayList<Integer>();
		while (count >= n3) {
			n3 = n1 + n2;
			if (count >= n3) {
				n1 = n2;
				n2 = n3;
				list.add(n3);
//				System.out.print(" " + n3);
			}
		}
		return list;
	}
	

	public static void main(String[] args) {
		ZeckendorfRepresentation zf = new ZeckendorfRepresentation();
		/*zf.printList(zf.getFibonacciList(88));
		System.out.println("\n--------------");*/
		List<Integer> hList = zf.getFibonacciList((int) target);
		System.out.println("For number "+target);
		zf.printList(hList);
		System.out.println("\n--------------");
		int minZfH = zf.getMinZfTerms((int)target,hList);
		System.out.println("Z("+target+") == "+minZfH);
		
		
		/*
		List<Integer> fList = zf.getFibonacciList(106);		//	ooops its not 106 --- but 10^6
		if (fList.size()>0) {
			zf.printList(fList);
		}*/
		int sumZfTerms = 0;
		for(int i = 1; i < target; i++) {
			List<Integer> fibList = zf.getFibonacciList(i);
			/*if(i==5){
				zf.printList(fibList);
			}*/
			if(i==fibList.get(fibList.size()-1)) {System.out.println("Z("+i+") --> "+1);
				sumZfTerms += 1;
			} else {	int z=zf.getMinZfTerms(i,fibList);	System.out.println("Z("+i+") -->  "+z);	
				sumZfTerms += z;
			}
		}
		System.out.println("SumOfZfTerms till "+target+" is -- "+sumZfTerms);
		
	}
	
	
	//TODO BruteForceAttack----refactor-l8r (^o^)
	//logic-wise---Fine!-----but!!!
	//ok-read-it-yourself ^__^
	/**
	 * Retrieves the Zeckendorf Representation for a number
	 * from the list of Fibonacci numbers that are less than
	 * the number
	 * @param num		--	the number whose Zeckendorf Represenation we seek
	 * @param fibList	--	list of Fibonacci values less than the number
	 * @return the Zeckendorf Representation of the number Z(n)
	 */
	private int getMinZfTerms(int num, List<Integer> fibList) {
		final int size = fibList.size();
		if (size >= 2) {
			// 2
			for (int i = 0; i < size - 1; i++) {
				for (int j = 1; j < size; j++) {
					if (num == fibList.get(i) + fibList.get(j)) {
						return 2;
					}
				}
			}
		}
		if (size >= 3) {
			// 3
			for (int i = 0; i < size - 2; i++) {
				for (int j = 1; j < size - 1; j++) {
					for (int k = 2; k < size; k++) {
						if (num == fibList.get(i) + fibList.get(j) + fibList.get(k)) {
							return 3;
						}
					}
				}
			}
		}
		if (size >= 4) {
			// 4
			for (int i = 0; i < size - 3; i++) {
				for (int j = 1; j < size - 2; j++) {
					for (int k = 2; k < size - 1; k++) {
						for (int l = 0; l < size; l++) {
							if (num == fibList.get(i) + fibList.get(j) + fibList.get(k) + fibList.get(l)) {
								return 4;
							}
						}
					}
				}
			}
		}
		if (size >= 5) {
			// 5
			for (int i = 0; i < size - 4; i++) {
				for (int j = 1; j < size - 3; j++) {
					for (int k = 2; k < size - 2; k++) {
						for (int l = 0; l < size - 1; l++) {
							for (int m = 0; m < size; m++) {
								if (num == fibList.get(i) + fibList.get(j) + fibList.get(k) + fibList.get(l)
										+ fibList.get(m)) {
									return 5;
								}
							}
						}
					}
				}
			}
		}
		if (size >= 6) {
			// 6
			for (int i = 0; i < size - 5; i++) {
				for (int j = 1; j < size - 4; j++) {
					for (int k = 2; k < size - 3; k++) {
						for (int l = 0; l < size - 2; l++) {
							for (int m = 0; m < size - 1; m++) {
								for (int n = 0; n < size; n++) {
									if (num == fibList.get(i) + fibList.get(j) + fibList.get(k) + fibList.get(l)
											+ fibList.get(m) + fibList.get(n)) {
										return 6;
									}
								}
							}
						}
					}
				}
			}
		}
		if (size >= 7) {
			// 7
			for (int i = 0; i < size - 6; i++) {
				for (int j = 1; j < size - 5; j++) {
					for (int k = 2; k < size - 4; k++) {
						for (int l = 0; l < size - 3; l++) {
							for (int m = 0; m < size - 2; m++) {
								for (int n = 0; n < size - 1; n++) {
									for (int o = 0; o < size; o++) {
										if (num == fibList.get(i) + fibList.get(j) + fibList.get(k) + fibList.get(l)
												+ fibList.get(m) + fibList.get(n) + fibList.get(o)) {
											return 7;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if (size >= 8) {
			// 8
			for (int i = 0; i < size - 7; i++) {
				for (int j = 1; j < size - 6; j++) {
					for (int k = 2; k < size - 5; k++) {
						for (int l = 0; l < size - 4; l++) {
							for (int m = 0; m < size - 3; m++) {
								for (int n = 0; n < size - 2; n++) {
									for (int o = 0; o < size - 1; o++) {
										for (int p = 0; p < size; p++) {
											if (num == fibList.get(i) + fibList.get(j) + fibList.get(k) + fibList.get(l)
													+ fibList.get(m) + fibList.get(n) + fibList.get(o)
													+ fibList.get(p)) {
												return 8;
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
		if (size >= 9) {
			// 9
			for (int i = 0; i < size - 8; i++) {
				for (int j = 1; j < size - 7; j++) {
					for (int k = 2; k < size - 6; k++) {
						for (int l = 0; l < size - 5; l++) {
							for (int m = 0; m < size - 4; m++) {
								for (int n = 0; n < size - 3; n++) {
									for (int o = 0; o < size - 2; o++) {
										for (int p = 0; p < size - 1; p++) {
											for (int q = 0; q < size; q++) {
												if (num == fibList.get(i) + fibList.get(j) + fibList.get(k)
														+ fibList.get(l) + fibList.get(m) + fibList.get(n)
														+ fibList.get(o) + fibList.get(p) + fibList.get(q)) {
													return 9;
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
		if (size >= 10) {
			// 10
			for (int i = 0; i < size - 9; i++) {
				for (int j = 1; j < size - 8; j++) {
					for (int k = 2; k < size - 7; k++) {
						for (int l = 0; l < size - 6; l++) {
							for (int m = 0; m < size - 5; m++) {
								for (int n = 0; n < size - 4; n++) {
									for (int o = 0; o < size - 3; o++) {
										for (int p = 0; p < size - 2; p++) {
											for (int q = 0; q < size - 1; q++) {
												for (int r = 0; r < size; r++) {
													if (num == fibList.get(i) + fibList.get(j) + fibList.get(k)
															+ fibList.get(l) + fibList.get(m) + fibList.get(n)
															+ fibList.get(o) + fibList.get(p) + fibList.get(q)
															+ fibList.get(r)) {
														return 10;
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
		}

		System.out.println("For num [" + num + "] - fiblist size is " + fibList.size());
		return 0;
	}

	private void printList(List<Integer> list) {//System.out.println("Here "+System.currentTimeMillis());System.out.println(list.size());
		String listStr = "";
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()) {
			listStr += it.next() + " ";
		}
		System.out.println(listStr);
		System.out.println("===============================");
	}

}
