/**
 * 
 */
package org.bawaweb.euler;

/**
 * @author Navroz
 * https://projecteuler.net/problem=15
 * 
 * Lattice paths
 * 
 * Problem 15
 * Starting in the top left corner of a 2×2 grid, 
 * and only being able to move to the right and down, 
 * there are exactly 6 routes to the bottom right corner.
 * 
 * How many such routes are there through a 20×20 grid?
 * 
 * Also see
 * 		https://www.robertdickau.com/lattices.html
 * 
 * 		GridSize			Number of LatticePaths		*2		^2		^3		^4			pvs										nCk
 * 		-------				----------------------
 * 			1					2		(me)			2		1		1		1			2								2				
 * 			2					6						4		4		8		16			4[1*1Grid]		2+(4*1)		=	6		4C2	
 * 			3					20						6		9		27		81			9[2*2Grid]		2+(9*2)		=	20				
 * 			4					70						8		16		64		256			16[3*3Grid]		2+(16*3)+20	=	70				
 * 			5					172 (check?)			10		25		125		625			25[4*4Grid]		2+(25*4)+70	=	172			
 * 
 * 
 * 	
 *
 */
public class LatticePaths {

	public static void main(String[] args) {
		
	}

}
