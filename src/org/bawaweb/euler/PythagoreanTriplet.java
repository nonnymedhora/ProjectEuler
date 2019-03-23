/**
 * 
 */
package org.bawaweb.euler;

/**
 * @author Navroz
 * https://projecteuler.net/problem=9
 * 
 * Special Pythagorean triplet
 * -----------------------------
 * Problem 9
 * A Pythagorean triplet is a set of three natural numbers, 
 * a < b < c, for which,
 * a^2 + b^2 = c^2
 * 
 * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
 * 
 * There exists exactly one Pythagorean triplet 
 * for which a + b + c = 1000.
 * 
 * Find the product abc.
 * 
 * Output
 * ------
 * x is 200
 * y is 375
 * z is 425
 * 200 + 375 + 425 = 1000
 * x^2 + y^2 is -->	40000 + 140625 = 180625
 * z^2 is --> 180625
 * Product (x * y * z) is  --> 31875000
 *
 */
public class PythagoreanTriplet {
	final static int target = 1000;

	public static void main(String[] args) {
		int a = 1;
		int b = 2;
		int c = 3;
		
		processPythagorean(a,b,c);

	}

	private static void processPythagorean(int a, int b, int c) {
		for(int x = a; x < 500; x++) {
			for(int y = b; y < 500; y++) {
				for(int z = c; z < 500; z++) {
					if(x+y+z == target) {
						if (x<y && y<z) {
							final int xSq = (int) Math.pow(x, 2);
							final int ySq = (int) Math.pow(y, 2);
							final int zSq = (int) Math.pow(z, 2);
							if (xSq + ySq == zSq) {
								System.out.println("x is " + x);
								System.out.println("y is " + y);
								System.out.println("z is " + z);
								System.out.println(x + " + " + y + " + " + z + " = " + (x + y + z));
								System.out.println("x^2 + y^2 is -->	" + xSq + " + " + ySq + " = "+ (xSq+ySq));
								System.out.println("z^2 is --> " + zSq);
								System.out.println("Product (x * y * z) is  --> " + (x * y * z));

								break;
							} 
						}
					}
				}
			}
		}
		
	}

}
