/**
 * 
 */
package org.bawaweb.euler;

/**
 * @author Navroz
 * https://projecteuler.net/problem=11
 * 
 * Largest product in a grid
 * ----------------------------
 * Problem 11
 * 
 * In the 20×20 grid below, 
 * four numbers along a diagonal line have been marked in red.

				08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08
				49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00
				81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65
				52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91
				22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80
				24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50
				32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70
				67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21
				24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72
				21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95
				78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92
				16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57
				86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58
				19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40
				04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66
				88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69
				04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36
				20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16
				20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54
				01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48
				
 * The product of these numbers is 26 × 63 × 78 × 14 = 1788696.
 * What is the greatest product of four adjacent numbers in the same direction 
 * (up, down, left, right, or diagonally) in the 20×20 grid?
 * 
 * 
 * 
 * OUTPUT
 * -------------------
 * Largest Product of 4 adjacent digits
 * in the (20x20)Grid are
 * is 70600674
 * Direction is  --> Diagonal - Moving Left and Down
 * From start[r,c] (13,7) to end[r,c] (17,3)
 * The numbers are:
 * 	89, 94, 97, 87
 * Maximum Product is --> 70600674

 *
 */
public class MinMaxGridProduct {
	static final int dimension = 20;
	private final static int[][] theGrid;
	static {
		theGrid = new int[dimension][];
		theGrid[0] = new int[]{	 8,  2, 22, 97, 38, 15,  0, 40,  0, 75,  4,  5,  7, 78, 52, 12, 50, 77, 91,  8	};
		theGrid[1] = new int[]{	49, 49, 99, 40, 17, 81, 18, 57, 60, 87, 17, 40, 98, 43, 69, 48,  4, 56, 62,  0	};
		theGrid[2] = new int[]{	81, 49, 31, 73, 55, 79, 14, 29, 93, 71, 40, 67, 53, 88, 30,  3, 49, 13, 36, 65	};
		theGrid[3] = new int[]{	52, 70, 95, 23,  4, 60, 11, 42, 69, 24, 68, 56,  1, 32, 56, 71, 37,  2, 36, 91	};
		theGrid[4] = new int[]{	22, 31, 16, 71, 51, 67, 63, 89, 41, 92, 36, 54, 22, 40, 40, 28, 66, 33, 13, 80	};
		theGrid[5] = new int[]{	24, 47, 32, 60, 99,  3, 45,  2, 44, 75, 33, 53, 78, 36, 84, 20, 35, 17, 12, 50	};
		theGrid[6] = new int[]{	32, 98, 81, 28, 64, 23, 67, 10, 26, 38, 40, 67, 59, 54, 70, 66, 18, 38, 64, 70	};
		theGrid[7] = new int[]{	67, 26, 20, 68,  2, 62, 12, 20, 95, 63, 94, 39, 63,  8, 40, 91, 66, 49, 94, 21	};
		theGrid[8] = new int[]{	24, 55, 58,  5, 66, 73, 99, 26, 97, 17, 78, 78, 96, 83, 14, 88, 34, 89, 63, 72	};
		theGrid[9] = new int[]{	21, 36, 23,  9, 75,  0, 76, 44, 20, 45, 35, 14,  0, 61, 33, 97, 34, 31, 33, 95	};
		theGrid[10]= new int[]{	78, 17, 53, 28, 22, 75, 31, 67, 15, 94,  3, 80,  4, 62, 16, 14,  9, 53, 56, 92	};
		theGrid[11]= new int[]{	16, 39,  5, 42, 96, 35, 31, 47, 55, 58, 88, 24,  0, 17, 54, 24, 36, 29, 85, 57	};
		theGrid[12]= new int[]{	86, 56,  0, 48, 35, 71, 89,  7,  5, 44, 44, 37, 44, 60, 21, 58, 51, 54, 17, 58	};
		theGrid[13]= new int[]{	19, 80, 81, 68,  5, 94, 47, 69, 28, 73, 92, 13, 86, 52, 17, 77,  4, 89, 55, 40	};
		theGrid[14]= new int[]{	04, 52,  8, 83, 97, 35, 99, 16,  7, 97, 57, 32, 16, 26, 26, 79, 33, 27, 98, 66	};
		theGrid[15]= new int[]{	88, 36, 68, 87, 57, 62, 20, 72,  3, 46, 33, 67, 46, 55, 12, 32, 63, 93, 53, 69	};
		theGrid[16]= new int[]{	04, 42, 16, 73, 38, 25, 39, 11, 24, 94, 72, 18,  8, 46, 29, 32, 40, 62, 76, 36	};
		theGrid[17]= new int[]{	20, 69, 36, 41, 72, 30, 23, 88, 34, 62, 99, 69, 82, 67, 59, 85, 74,  4, 36, 16	};
		theGrid[18]= new int[]{	20, 73, 35, 29, 78, 31, 90,  1, 74, 31, 49, 71, 48, 86, 81, 16, 23, 57,  5, 54	};
		theGrid[19]= new int[]{	01, 70, 54, 71, 83, 51, 54, 69, 16, 92, 33, 48, 61, 43, 52,  1, 89, 19, 67, 48	};		
	}
	
	static final int windowSize	=	4;
	
	
	static boolean canGoRight(int row, int col) {
		return (col+1)+windowSize<=dimension;		
	}
	
	static int getGoRightProduct(int row, int col) {		
		int product = 1;
		for(int c = col; c <col+windowSize; c++){
			product *= theGrid[row][c];
			if(product==0)	return product;
		}
		return product;		
	}	
	
	static int[] getGoRightCells(int row, int col) {
		int[] cells = new int[windowSize];
		for(int i = 0; i < windowSize; i++){ 
			cells[i]=theGrid[row][col+i];
			
		}
		return cells;
		
	}

	static boolean canGoDown(int row, int col) {
		return (row+1)+windowSize<=dimension;		
	}
	
	static int getGoDownProduct(int row, int col) {		
		int product = 1;
		for(int r = row; r <row+windowSize; r++){
			product *= theGrid[r][col];
			if(product==0)	return product;
		}
		return product;		
	}
	
	static int[] getGoDownCells(int row, int col) {
		int[] cells = new int[windowSize];
		for(int i = 0; i < windowSize; i++){ 
			cells[i]=theGrid[row+i][col];
			
		}
		return cells;
		
	}

	static boolean canGoLeft(int row, int col) {
		return (col+1)>=windowSize;		
	}	

	static boolean canGoUp(int row, int col) {
		return (row+1)>=windowSize;		
	}	

	static boolean canGoRightDown(int row, int col) {
		return canGoRight(row,col) && canGoDown(row,col);		
	}	

	static int getRightDownProduct(int row, int col) {	
		int product = 1;
		for(int i = 0; i <windowSize; i++){
			product *= theGrid[row+i][col+i];
			if(product==0)	return product;
		}
		return product;		
	}
	
	static int[] getGoRightDownCells(int row, int col) {
		int[] cells = new int[windowSize];
		for(int i = 0; i < windowSize; i++){ 
			cells[i]=theGrid[row+i][col+i];
			
		}
		return cells;
		
	}

	static boolean canGoLeftDown(int row, int col) {
		return canGoLeft(row,col) && canGoDown(row,col);		
	}
	

	static int getLeftDownProduct(int row, int col) {	
		int product = 1;
		for(int i = 0; i <windowSize; i++){
			product *= theGrid[row+i][col-i];
			if(product==0)	return product;
		}
		return product;		
	}
	
	static int[] getGoLeftDownCells(int row, int col) {
		int[] cells = new int[windowSize];
		for(int i = 0; i < windowSize; i++){ 
			cells[i]=theGrid[row+i][col-i];
			
		}
		return cells;
		
	}

	static void printAray(int[] arr) {
		String s = "";
		for(int i = 0; i < arr.length; i++) {
			if(i==arr.length-1){
				s+=arr[i];
			}else{
				s+=arr[i]+", ";
			}
		}
		System.out.println(s);
	}
	
	public static void main(String[] args) {
		
		int maxProduct = 0;
		int[] maxProductNos = new int[windowSize];
		String direction = "";
		int startRow=0;
		int startCol = 0;
		int endRow = 0;
		int endCol = 0;
		
		int minProduct = 0;
		int[] minProductNos = new int[windowSize];
		String minDirection = "";
		int minStartRow=0;
		int minStartCol = 0;
		int minEndRow = 0;
		int minEndCol = 0;
		
		
		
		for(int row = 0; row < (dimension-windowSize); row++) {
			for(int col = 0; col < (dimension-windowSize); col++) {
				
				if( canGoRight(row,col) ) {
					int product = getGoRightProduct(row,col);
					if (product > maxProduct) {
						maxProduct = product;
						maxProductNos = getGoRightCells(row,col);
						direction = "Moving Right";
						startRow = row+1;
						startCol = col+1;
						endRow = row+1;
						endCol = col+1+windowSize;
					} else if (product <= minProduct) {
						minProduct = product;
						minProductNos = getGoRightCells(row,col);
						minDirection = "Moving Right";
						minStartRow = row+1;
						minStartCol = col+1;
						minEndRow = row+1;
						minEndCol = col+1+windowSize;
					}
					
				}
				
				if( canGoDown(row,col) ) {
					int product = getGoDownProduct(row,col);
					if (product > maxProduct) {
						maxProduct = product;
						maxProductNos = getGoDownCells(row,col);
						direction = "Moving Down";
						startRow = row+1;
						startCol = col+1;
						endRow = row+1+windowSize;
						endCol = col+1;
					} else if (product <= minProduct) {
						minProduct = product;
						minProductNos = getGoDownCells(row,col);
						minDirection = "Moving Down";
						minStartRow = row+1;
						minStartCol = col+1;
						minEndRow = row+1+windowSize;
						minEndCol = col+1;
					}
				}
				
				if( canGoRightDown(row,col) ) {
					int product = getRightDownProduct(row,col);
					if (product > maxProduct) {
						maxProduct = product;
						maxProductNos = getGoRightDownCells(row,col);
						direction = "Diagonal - Moving Right and Down";
						startRow = row+1;
						startCol = col+1;
						endRow = row+1+windowSize;
						endCol = col+1+windowSize;
					} else if (product <= minProduct) {
						minProduct = product;
						minProductNos = getGoRightDownCells(row,col);
						minDirection = "Diagonal - Moving Right and Down";
						minStartRow = row+1;
						minStartCol = col+1;
						minEndRow = row+1+windowSize;
						minEndCol = col+1+windowSize;
					}
				}
				
				if( canGoLeftDown(row,col) ) {
					int product = getLeftDownProduct(row,col);
					if (product > maxProduct) {
						maxProduct = product;
						maxProductNos = getGoLeftDownCells(row,col);
						direction = "Diagonal - Moving Left and Down";
						startRow = row+1;
						startCol = col+1;
						endRow = row+1+windowSize;
						endCol = col+1-windowSize;
					} else if (product <= minProduct) {
						minProduct = product;
						minProductNos = getGoLeftDownCells(row,col);
						minDirection = "Diagonal - Moving Left and Down";
						minStartRow = row+1;
						minStartCol = col+1;
						minEndRow = row+1+windowSize;
						minEndCol = col+1-windowSize;
					}
				}
			}
		}
		
		System.out.println("Largest Product of "+windowSize+" adjacent digits\n in the ("+dimension+"x"+dimension+") Grid are");
		System.out.println("is "+maxProduct);
		System.out.println("Direction is  --> "+direction);
		System.out.println("From start[r,c] ("+startRow+","+startCol+") to end[r,c] ("+endRow+","+endCol+")");
		System.out.println("The numbers are:");
		printAray(maxProductNos);
		System.out.println("Maximum Product is --> "+maxProduct);
		System.out.println("\n-------------------------------------------------\n");
		
		System.out.println("Smallest Product of "+windowSize+" adjacent digits\n in the ("+dimension+"x"+dimension+") Grid are");
		System.out.println("is "+minProduct);
		System.out.println("Direction is  --> "+minDirection);
		System.out.println("From start[r,c] ("+minStartRow+","+minStartCol+") to end[r,c] ("+minEndRow+","+minEndCol+")");
		System.out.println("The numbers are:");
		printAray(minProductNos);
		System.out.println("Minimum Product is --> "+minProduct);
		System.out.println("\n-------------------------------------------------\n");
		

	}

}
