/**
 * 
 */
package org.bawaweb.euler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Navroz
 * 
 * Path sum: two ways
Problem 81

In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom right, 
by only moving to the right and down, is indicated in bold red and is equal to 2427.
		131 673 234 103 18 
		201 96 	342 965 150 
		630 803 746 422 111 
		537 699 497 121 956 
		805 732 524 37 	331 

Find the minimal path sum, in matrix.txt (right click and "Save Link/Target As..."), 
a 31K text file containing a 80 by 80 matrix, 
from the top left to the bottom right by only moving right and down.
		
		
		Output	for	example
		-------------------
		Minimum-Sum path is == 2707
		Path is: 
		131 673 234 103 18 150 111 956 331 
 *
 */
public class MinPathSum81 {

	public static int[] getPaths(final int[][] aMatrix){
		return null;
		
	}
	
	
	
	public static int getMinPathSum(final int[][] aMatrix){
		
		int nRows = aMatrix.length;
		int nCols = aMatrix[0].length;
		
		int row = 0;
		int col = 0;
		int sum = aMatrix[row][col];System.out.println("START row,col ["+row+","+col+"] adding "+aMatrix[row][col]);
		
		while (/*row = 0;*/ row < aMatrix.length/*; row++*/) {
			while (/*col = 0;*/ col < aMatrix.length/*; col++*/) {

				int rt = 0;
				int dn = 0;

				if (canGoRight(aMatrix, col) && canGoDown(aMatrix, row)) {
					rt = getRightValue(aMatrix, row, col);
					dn = getDownValue(aMatrix, row, col);
					if (rt < dn) {
						col += 1;
					} else {
						row += 1;
					}
					System.out.println("A	For row,col [" + row + "," + col + "] adding " + aMatrix[row][col]);
					sum += aMatrix[row][col];
				} else if (!canGoRight(aMatrix, col) && canGoDown(aMatrix, row)) {
					dn = getDownValue(aMatrix, row, col);
					row += 1;
					System.out.println("B	For row,col [" + row + "," + col + "] adding " + aMatrix[row][col]);
					sum += aMatrix[row][col];

				} else if (canGoRight(aMatrix, col) && !canGoDown(aMatrix, row)) {
					rt = getRightValue(aMatrix, row, col);
					col += 1;
					System.out.println("C	For row,col [" + row + "," + col + "] adding " + aMatrix[row][col]);
					sum += aMatrix[row][col];
				} else if (!canGoRight(aMatrix, col) && !canGoDown(aMatrix, row)) {
					sum += aMatrix[row][col];
					System.out.println("D	For row,col [" + row + "," + col + "] adding " + aMatrix[row][col]);
					row+=1;
					col+=1;
					break;
				}

			}
		}
		
		return sum;
	}
	private static boolean canGoDown(int[][] matrix, int row) {		
		return row+1<matrix.length;
	}
	private static boolean canGoRight(int[][] matrix, int col) {
		int ncols=matrix[0].length;
		return col+1<ncols;
	}
	private static int getDownValue(int[][] matrix, int row, int col) {
		return matrix[row+1][col];
	}
	private static int getRightValue(int[][] matrix, int row, int col) {
		return matrix[row][col+1];
	}
	
	public static void main(String[] args) {
		int[][] matrix = new int[][]{
		new int[] { 131, 673, 234, 103, 18 },
		new int[] { 201, 96, 342, 965, 150 },
		new int[] { 630, 803, 746, 422, 111 },
		new int[] { 537, 699, 497, 121, 956 },
		new int[] { 805, 732, 524, 37, 331 }
		};
		
		int m = matrix.length;
		int n = matrix[0].length;
		
		int maxLengthOfPath = m + n - 1; 
		List<int[]> thePaths = new ArrayList<int[]>();
		thePaths=traverse(matrix, m, n, 0, 0, new int[maxLengthOfPath], 0, thePaths ); 
		int minPathSum = Integer.MAX_VALUE;
		int[] minPath = null;
		for(int i = 0; i < thePaths.size(); i++){
			int pathSum=0;
			int[] aPath=thePaths.get(i);
			
			for(int j = 0; j < aPath.length; j++){
//				System.out.print(aPath[j]+" ");
				pathSum+=aPath[j];
			}
			if(minPathSum>pathSum){
				minPathSum=pathSum;
				minPath=aPath;
			}
//			System.out.println();
		}
		System.out.println("Minimum-Sum path is == "+minPathSum);
		System.out.println("Path is: ");
		for(int i = 0; i < minPath.length;i++){
			System.out.print(minPath[i]+" ");
		}
				
//		System.out.println("Minimum-Path Sum is == "+getMinPathSum(matrix));
	}
	
	
	
	/* 
	mat: Pointer to the starting of mXn matrix 
	i, j: Current position of the robot (For the first call use 0,0) 
	m, n: Dimentions of given the matrix 
	pi: Next index to be filed in path array 
	path[0..pi-1]: The path traversed by robot till now (Array to hold the 
				path need to have space for at least m+n elements) */
	private static void traverseMatrix(int mat[][], int m, int n, 
									int i, int j, int path[], int idx) { 
		path[idx] = mat[i][j];

		// Reached the bottom of the matrix so we are left with
		// only option to move right
		if (i == m - 1) {
			for (int k = j + 1; k < n; k++) {
				path[idx + k - j] = mat[i][k];
			}
			for (int l = 0; l < idx + n - j; l++) {
				System.out.print(path[l] + " ");
			}
			System.out.println();
			return;
		}

		// Reached the right corner of the matrix we are left with
		// only the downward movement.
		if (j == n - 1) {
			for (int k = i + 1; k < m; k++) {
				path[idx + k - i] = mat[k][j];
			}
			for (int l = 0; l < idx + m - i; l++) {
				System.out.print(path[l] + " ");
			}
			System.out.println();
			return;
		}
		// Print all the paths that are possible after moving down
		traverseMatrix(mat, m, n, i + 1, j, path, idx + 1);

		// Print all the paths that are possible after moving right
		traverseMatrix(mat, m, n, i, j + 1, path, idx + 1);
	} 
	
	
	
	private static List<int[]> traverse(int mat[][], int m, int n, 
			int i, int j, int path[], int idx,List<int[]> paths) {
		path[idx] = mat[i][j];

		// Reached the bottom of the matrix so we are left with
		// only option to move right
		if (i == m - 1) {
			for (int k = j + 1; k < n; k++) {
				path[idx + k - j] = mat[i][k];
			}
			for (int l = 0; l < idx + n - j; l++) {
//				System.out.print(path[l] + " ");				
			}
			paths.add(path);
//			System.out.println();
			return paths;
		}

		// Reached the right corner of the matrix we are left with
		// only the downward movement.
		if (j == n - 1) {
			for (int k = i + 1; k < m; k++) {
				path[idx + k - i] = mat[k][j];
			}
			for (int l = 0; l < idx + m - i; l++) {
//				System.out.print(path[l] + " ");
			}
			paths.add(path);
//			System.out.println();
			return paths;
		}
		// Print all the paths that are possible after moving down
		traverse(mat, m, n, i + 1, j, path, idx + 1,paths);

		// Print all the paths that are possible after moving right
		return traverse(mat, m, n, i, j + 1, path, idx + 1,paths);
	} 

}
