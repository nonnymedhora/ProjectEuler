/**
 * 
 */
package org.bawaweb.euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Navroz
 * 
 
 *
 */

	

	class Box {		//	unit

		private char 		row;							//	from 'A' to 'C'
		private int 		col;							// 	from 1 to 3
		private int 		value = -1;						// 	from 1 to 9

		private boolean 	placed;							//	has a fixed value from start to end
		private boolean 	assigned;						//	has a value assigned, if not then its value is -1
		private Grid 		parentGrid;						//	the parent grid of this box
		
		private List<Integer>	possibleValues;				//	the list of possible values for an unplaced/unassigned box

		public Box() {
		}

		public Box(char aRow, int aCol, int aValue) {
			super();
			this.row = aRow;
			this.col = aCol;
			this.value = aValue;
		}

		public Box(char aRow, int aCol) {
			super();
			this.row = aRow;
			this.col = aCol;
		}

		public Box(char aRow, int aCol, int aValue, boolean aPlaced) {
			super();
			this.row = aRow;
			this.col = aCol;
			this.value = aValue;
			this.placed = aPlaced;
		}

		public char getRow() {
			return this.row;
		}

		public void setRow(char row) {
			this.row = row;
		}

		public int getCol() {
			return this.col;
		}

		public void setCol(int col) {
			this.col = col;
		}

		public int getValue() {
			return this.value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public boolean isPlaced() {
			return this.placed;
		}

		public void setPlaced(boolean placed) {
			this.placed = placed;
		}

		public Grid getParentGrid() {
			return this.parentGrid;
		}

		public void setParentGrid(Grid aGrid) {
			this.parentGrid = aGrid;
		}

		public boolean isAssigned() {
			return (this.assigned && (this.value != -1));
		}

		public void setAssigned(boolean assigned) {
			this.assigned = assigned;
		}
		
		public String toString() {
			String s = "";
			if (this.value != -1) {
				s += " " + this.value + " |";
			} else {
				s += " X |";
			}
			return s;
		}

		public List<Integer> getPossibleValues() {
			return this.possibleValues;
		}

		public void setPossibleValues(List<Integer> possibleValues) {
			this.possibleValues = possibleValues;
		}

	}
	
	
	
	class Grid {	//	contains 9 boxes
		
		private String 			position;					//	from A1 - C3
		
		private Box[] 			boxes = new Box[9];

		private Map<String,Box>	gridBoxMap = new HashMap<String,Box>();

		public Grid(String aPosition, Box[] gridBoxes, Box[] placedBoxes) {
			super();
			this.position = aPosition;
			
			if (gridBoxes != null) {
				this.boxes = gridBoxes;
			} else {
				this.boxes = createEmptyGrid();
			}
			
			if (placedBoxes!=null) {
				this.initializePlacedBoxes(this.boxes, placedBoxes);
			} else {
				this.initializeBoxes();
			}
			
			this.setGridBoxMap(this.gridBoxMap);
		}
		
		private Box[] createEmptyGrid() {
			this.boxes[0] = new Box('A', 1, -1, false);		this.boxes[0].setAssigned(false);	
			this.boxes[1] = new Box('A', 2, -1, false);		this.boxes[1].setAssigned(false);
			this.boxes[2] = new Box('A', 3, -1, false);		this.boxes[2].setAssigned(false);
			this.boxes[3] = new Box('B', 1, -1, false);		this.boxes[3].setAssigned(false);
			this.boxes[4] = new Box('B', 2, -1, false);		this.boxes[4].setAssigned(false);
			this.boxes[5] = new Box('B', 3, -1, false);		this.boxes[5].setAssigned(false);
			this.boxes[6] = new Box('C', 1, -1, false);		this.boxes[6].setAssigned(false);
			this.boxes[7] = new Box('C', 2, -1, false);		this.boxes[7].setAssigned(false);
			this.boxes[8] = new Box('C', 3, -1, false);		this.boxes[8].setAssigned(false);
			
			return this.boxes;
		}
		
		public void initializeBoxes() {
			this.gridBoxMap.put("A1",	this.boxes[0]);
			this.gridBoxMap.put("A2",	this.boxes[1]);
			this.gridBoxMap.put("A3",	this.boxes[2]);
			this.gridBoxMap.put("B1",	this.boxes[3]);
			this.gridBoxMap.put("B2",	this.boxes[4]);
			this.gridBoxMap.put("B3",	this.boxes[5]);
			this.gridBoxMap.put("C1",	this.boxes[6]);
			this.gridBoxMap.put("C2",	this.boxes[7]);
			this.gridBoxMap.put("C3",	this.boxes[8]);
		}		
		
		public void initializePlacedBoxes(Box[] gridBoxes, Box[] placedBoxes) {
			List<String> posList = new ArrayList<String>();
			for(int i = 0; i < placedBoxes.length; i++) {
				Box placedBox = placedBoxes[i];
				placedBox.setPlaced(true);
				placedBox.setAssigned(true);
				
				String pos = String.valueOf(placedBox.getRow())+String.valueOf(placedBox.getCol());
				
				this.gridBoxMap.put(pos,placedBox);
				posList.add(pos);
			}
			
			List<String> gridList = new ArrayList<String>();
			for(int i = 0; i < gridBoxes.length; i++) {
				Box gridBox = gridBoxes[i];
				if ( gridBox.getValue() != -1 ) {
					gridBox.setAssigned(true);
					String pos = String.valueOf(gridBox.getRow()) + String.valueOf(gridBox.getCol());
					this.gridBoxMap.put(pos, gridBox);
					gridList.add(pos);
				}				
			}
			
			
			if( !posList.contains("A1") && !gridList.contains("A1")) {
				Box b = new Box('A',1,-1, false);
				b.setAssigned(false);
				this.gridBoxMap.put("A1",	b);				
			}
			if( !posList.contains("A2") && !gridList.contains("A2") ) {
				Box b = new Box('A',2,-1, false);
				b.setAssigned(false);
				this.gridBoxMap.put("A2",	b);				
			}
			if( !posList.contains("A3") && !gridList.contains("A3") ) {
				Box b = new Box('A',3,-1, false);
				b.setAssigned(false);
				this.gridBoxMap.put("A3",	b);				
			}	
			
			if( !posList.contains("B1") && !gridList.contains("B1")) {
				Box b = new Box('B',1,-1, false);
				b.setAssigned(false);
				this.gridBoxMap.put("B1",	b);				
			}
			if( !posList.contains("B2") && !gridList.contains("B2") ) {
				Box b = new Box('B',2,-1, false);
				b.setAssigned(false);
				this.gridBoxMap.put("B2",	b);				
			}
			if( !posList.contains("B3") && !gridList.contains("B3") ) {
				Box b = new Box('B',3,-1, false);
				b.setAssigned(false);
				this.gridBoxMap.put("B3",	b);				
			}
			
			if( !posList.contains("C1") && !gridList.contains("C1") ) {
				Box b = new Box('C',1,-1, false);
				b.setAssigned(false);
				this.gridBoxMap.put("C1",	b);				
			}
			if( !posList.contains("C2") && !gridList.contains("C2") ) {
				Box b = new Box('C',2,-1, false);
				b.setAssigned(false);
				this.gridBoxMap.put("C2",	b);				
			}
			if( !posList.contains("C3") && !gridList.contains("C3") ) {
				Box b = new Box('C',3,-1, false);
				b.setAssigned(false);
				this.gridBoxMap.put("C3",	b);				
			}
		}
		
		public boolean containsValue(int numValue) {
			boolean isThere = false;
			for(int i = 0; i < this.boxes.length; i++) {
				if(this.boxes[i].getValue() == numValue) {
					return isThere = true;
				}
			}
			return isThere;
		}
		
		
		public Box[] getCol(int col) {
			Box[] colBoxes = new Box[3];

			colBoxes[0] = this.gridBoxMap.get(String.valueOf('A') + col);
			colBoxes[1] = this.gridBoxMap.get(String.valueOf('B') + col);
			colBoxes[2] = this.gridBoxMap.get(String.valueOf('C') + col);

			return colBoxes;
		}
		
		
		public Box[] getRow(char aRow) {
			Box[] rowBoxes = new Box[3];
			
			rowBoxes[0] = this.gridBoxMap.get((String.valueOf(aRow)+1));
			rowBoxes[1] = this.gridBoxMap.get((String.valueOf(aRow)+2));
			rowBoxes[2] = this.gridBoxMap.get((String.valueOf(aRow)+3));		
			
			return rowBoxes;			
		}
		
		public int getRowSum(char aRow) {
			int sum = 0;
			sum =	this.gridBoxMap.get((String.valueOf(aRow)+1)).getValue()+
					this.gridBoxMap.get((String.valueOf(aRow)+2)).getValue()+
					this.gridBoxMap.get((String.valueOf(aRow)+3)).getValue();
			return sum;
		}
		
		public int getColSum(int aCol) {
			int sum = 0;
			sum =	this.gridBoxMap.get(String.valueOf('A')+aCol).getValue()+
					this.gridBoxMap.get(String.valueOf('B')+aCol).getValue()+
					this.gridBoxMap.get(String.valueOf('C')+aCol).getValue();
			return sum;
		}

		public Map<String, Box> getGridBoxMap() {
			return this.gridBoxMap;
		}

		public void setGridBoxMap(Map<String, Box> gridBoxMap) {
			this.gridBoxMap = gridBoxMap;
		}

		public Box[] getBoxes() {
			return this.boxes;
		}

		public void setBoxes(Box[] boxes) {
			this.boxes = boxes;
		}
		
		public void printGrid() {
			String g = this.position+"\n___________________________________\n";
			g += this.getGridBoxMap().get("A1").toString();
			g += this.getGridBoxMap().get("A2").toString();
			g += this.getGridBoxMap().get("A3").toString();
			g += "\n----------------\n";
			
			g += this.getGridBoxMap().get("B1").toString();
			g += this.getGridBoxMap().get("B2").toString();
			g += this.getGridBoxMap().get("B3").toString();
			g += "\n----------------\n";
			
			g += this.getGridBoxMap().get("C1").toString();
			g += this.getGridBoxMap().get("C2").toString();
			g += this.getGridBoxMap().get("C3").toString();
			g += "\n----------------\n";
			
			System.out.println(g);
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}
		
	}
	
	
	class Matrix {	//	contains 9 Grids
		private Grid[] 				grids 		= new Grid[9];
		private Map<String, Grid>	gridMap		= new HashMap<String,Grid>();		
		
		public Matrix(Grid[] theGrids) {
			super();
			this.setGrids(theGrids);
			
			this.initializeGrids();
			
			this.setGridMap(this.gridMap);
		}

		public void initializeGrids() {
			this.gridMap.put("A1",	this.grids[0]);
			this.gridMap.put("A2",	this.grids[1]);
			this.gridMap.put("A3",	this.grids[2]);
			this.gridMap.put("B1",	this.grids[3]);
			this.gridMap.put("B2",	this.grids[4]);
			this.gridMap.put("B3",	this.grids[5]);
			this.gridMap.put("C1",	this.grids[6]);
			this.gridMap.put("C2",	this.grids[7]);
			this.gridMap.put("C3",	this.grids[8]);
		}
		
		public void printRow(int row) {
			Box[] rowBoxes = this.getRow(row);
			String s = "";
			for(int i = 0; i < rowBoxes.length; i++) {
				s += rowBoxes[i].toString();
			}
			System.out.println("|"+s);
		}
		
		private List<Box> toList(Box[] boxes) {
			List<Box> list = new ArrayList<Box>();
			for(int i = 0; i < boxes.length; i++) {
				list.add(boxes[i]);
			}
			return list;
		}
		
		private Box[] toArray(List<Box> boxes) {
			final int size = boxes.size();
			Box[] b = new Box[size];
			for(int i = 0; i < size; i++) {
				b[i] = boxes.get(i);
			}
			return b;
		}
		
		
		public Box[] getCol(int col) {
			Box[] theCol = new Box[9];
			List<Box> colBoxes = new ArrayList<Box>();
			switch (col) {
			case 1:
					colBoxes.addAll(toList(this.gridMap.get("A1").getCol(1)));
					colBoxes.addAll(toList(this.gridMap.get("B1").getCol(1)));
					colBoxes.addAll(toList(this.gridMap.get("C1").getCol(1)));
					break;
			case 2:
					colBoxes.addAll(toList(this.gridMap.get("A1").getCol(2)));
					colBoxes.addAll(toList(this.gridMap.get("B1").getCol(2)));
					colBoxes.addAll(toList(this.gridMap.get("C1").getCol(2)));
				break;
			case 3:
					colBoxes.addAll(toList(this.gridMap.get("A1").getCol(3)));
					colBoxes.addAll(toList(this.gridMap.get("B1").getCol(3)));
					colBoxes.addAll(toList(this.gridMap.get("C1").getCol(3)));
				break;
			case 4:
					colBoxes.addAll(toList(this.gridMap.get("A2").getCol(1)));
					colBoxes.addAll(toList(this.gridMap.get("B2").getCol(1)));
					colBoxes.addAll(toList(this.gridMap.get("C2").getCol(1)));
				break;
			case 5:
					colBoxes.addAll(toList(this.gridMap.get("A2").getCol(2)));
					colBoxes.addAll(toList(this.gridMap.get("B2").getCol(2)));
					colBoxes.addAll(toList(this.gridMap.get("C2").getCol(2)));
				break;
			case 6:
					colBoxes.addAll(toList(this.gridMap.get("A2").getCol(3)));
					colBoxes.addAll(toList(this.gridMap.get("B2").getCol(3)));
					colBoxes.addAll(toList(this.gridMap.get("C2").getCol(3)));
				break;
			case 7:
					colBoxes.addAll(toList(this.gridMap.get("A3").getCol(1)));
					colBoxes.addAll(toList(this.gridMap.get("B3").getCol(1)));
					colBoxes.addAll(toList(this.gridMap.get("C3").getCol(1)));
				break;
			case 8:
					colBoxes.addAll(toList(this.gridMap.get("A3").getCol(2)));
					colBoxes.addAll(toList(this.gridMap.get("B3").getCol(2)));
					colBoxes.addAll(toList(this.gridMap.get("C3").getCol(2)));
				break;
			case 9:
					colBoxes.addAll(toList(this.gridMap.get("A3").getCol(3)));
					colBoxes.addAll(toList(this.gridMap.get("B3").getCol(3)));
					colBoxes.addAll(toList(this.gridMap.get("C3").getCol(3)));
				break;
			default:
					System.out.println("ERRRRRRrrrCol");
					System.out.println("theCol is   " + theCol);;
			}
			
			theCol = toArray(colBoxes);
			
			return theCol;
		}
		
		
		public Box[] getRow(int row) {
			Box[] theRow = new Box[9];
			List<Box> rowBoxes = new ArrayList<Box>();
			switch (row) {
			case 1:
					rowBoxes.addAll(toList(this.gridMap.get("A1").getRow('A')));
					rowBoxes.addAll(toList(this.gridMap.get("A2").getRow('A')));
					rowBoxes.addAll(toList(this.gridMap.get("A3").getRow('A')));
					break;
			case 2:
					rowBoxes.addAll(toList(this.gridMap.get("A1").getRow('B')));
					rowBoxes.addAll(toList(this.gridMap.get("A2").getRow('B')));
					rowBoxes.addAll(toList(this.gridMap.get("A3").getRow('B')));
				break;
			case 3:
					rowBoxes.addAll(toList(this.gridMap.get("A1").getRow('C')));
					rowBoxes.addAll(toList(this.gridMap.get("A2").getRow('C')));
					rowBoxes.addAll(toList(this.gridMap.get("A3").getRow('C')));
				break;
			case 4:
					rowBoxes.addAll(toList(this.gridMap.get("B1").getRow('A')));
					rowBoxes.addAll(toList(this.gridMap.get("B2").getRow('A')));
					rowBoxes.addAll(toList(this.gridMap.get("B3").getRow('A')));
				break;
			case 5:
					rowBoxes.addAll(toList(this.gridMap.get("B1").getRow('B')));
					rowBoxes.addAll(toList(this.gridMap.get("B2").getRow('B')));
					rowBoxes.addAll(toList(this.gridMap.get("B3").getRow('B')));
				break;
			case 6:
					rowBoxes.addAll(toList(this.gridMap.get("B1").getRow('C')));
					rowBoxes.addAll(toList(this.gridMap.get("B2").getRow('C')));
					rowBoxes.addAll(toList(this.gridMap.get("B3").getRow('C')));
				break;
			case 7:
					rowBoxes.addAll(toList(this.gridMap.get("C1").getRow('A')));
					rowBoxes.addAll(toList(this.gridMap.get("C2").getRow('A')));
					rowBoxes.addAll(toList(this.gridMap.get("C3").getRow('A')));
				break;
			case 8:
					rowBoxes.addAll(toList(this.gridMap.get("C1").getRow('B')));
					rowBoxes.addAll(toList(this.gridMap.get("C2").getRow('B')));
					rowBoxes.addAll(toList(this.gridMap.get("C3").getRow('B')));
				break;
			case 9:
					rowBoxes.addAll(toList(this.gridMap.get("C1").getRow('C')));
					rowBoxes.addAll(toList(this.gridMap.get("C2").getRow('C')));
					rowBoxes.addAll(toList(this.gridMap.get("C3").getRow('C')));
				break;
			default:
					System.out.println("ERRRRRRrrrow");
					System.out.println("rowIs   "+row);
			}
			
			theRow = toArray(rowBoxes);
			
			return theRow;
		}
		
		public int getColSum(int col) {
			int sum = 0;
			switch(col) {
			case 1:
					sum = 	this.gridMap.get("A1").getColSum(1) +
							this.gridMap.get("B1").getColSum(1) +
							this.gridMap.get("C1").getColSum(1);
					break;
			case 2:
				sum = 	this.gridMap.get("A1").getColSum(2) +
						this.gridMap.get("B1").getColSum(2) +
						this.gridMap.get("C1").getColSum(2);
				break;
			case 3:
				sum = 	this.gridMap.get("A1").getColSum(3) +
						this.gridMap.get("B1").getColSum(3) +
						this.gridMap.get("C1").getColSum(3);
				break;
			case 4:
				sum = 	this.gridMap.get("A2").getColSum(1) +
						this.gridMap.get("B2").getColSum(1) +
						this.gridMap.get("C2").getColSum(1);
				break;
			case 5:
				sum = 	this.gridMap.get("A2").getColSum(2) +
						this.gridMap.get("B2").getColSum(2) +
						this.gridMap.get("C2").getColSum(2);
				break;
			case 6:
				sum = 	this.gridMap.get("A2").getColSum(3) +
						this.gridMap.get("B2").getColSum(3) +
						this.gridMap.get("C2").getColSum(3);
				break;
			case 7:
				sum = 	this.gridMap.get("A3").getColSum(1) +
						this.gridMap.get("B3").getColSum(1) +
						this.gridMap.get("C3").getColSum(1);
				break;
			case 8:
				sum = 	this.gridMap.get("A3").getColSum(2) +
						this.gridMap.get("B3").getColSum(2) +
						this.gridMap.get("C3").getColSum(2);
				break;
			case 9:
				sum = 	this.gridMap.get("A3").getColSum(3) +
						this.gridMap.get("B3").getColSum(3) +
						this.gridMap.get("C3").getColSum(3);
				break;
			default:
				sum = -1;
					
			}
			
			return sum;			
		}
		
		public int getRowSum(char row) {
			int sum = 0;
			
			switch (row) {
			case 'A':
					sum = 	this.gridMap.get("A1").getRowSum('A') +
							this.gridMap.get("A2").getRowSum('A') +
							this.gridMap.get("A3").getRowSum('A');
					break;
			case 'B':
				sum = 	this.gridMap.get("A1").getRowSum('B') +
						this.gridMap.get("A2").getRowSum('B') +
						this.gridMap.get("A3").getRowSum('B');
				break;
			case 'C':
				sum = 	this.gridMap.get("A1").getRowSum('C') +
						this.gridMap.get("A2").getRowSum('C') +
						this.gridMap.get("A3").getRowSum('C');
				break;
			case 'D':
				sum = 	this.gridMap.get("B1").getRowSum('A') +
						this.gridMap.get("B2").getRowSum('A') +
						this.gridMap.get("B3").getRowSum('A');
				break;
			case 'E':
				sum = 	this.gridMap.get("B1").getRowSum('B') +
						this.gridMap.get("B2").getRowSum('B') +
						this.gridMap.get("B3").getRowSum('B');
				break;
			case 'F':
				sum = 	this.gridMap.get("B1").getRowSum('C') +
						this.gridMap.get("B2").getRowSum('C') +
						this.gridMap.get("B3").getRowSum('C');
				break;
			case 'G':
				sum = 	this.gridMap.get("C1").getRowSum('A') +
						this.gridMap.get("C2").getRowSum('A') +
						this.gridMap.get("C3").getRowSum('A');
				break;
			case 'H':
				sum = 	this.gridMap.get("C1").getRowSum('B') +
						this.gridMap.get("C2").getRowSum('B') +
						this.gridMap.get("C3").getRowSum('B');
				break;
			case 'I':
				sum = 	this.gridMap.get("C1").getRowSum('C') +
						this.gridMap.get("C2").getRowSum('C') +
						this.gridMap.get("C3").getRowSum('C');
				break;
			default:
					sum = -1;
			}
			return sum;
		}
		
		
		public int getRowSum(int row) {
			int sum = 0;
			switch (row) {
			case 1:
				sum = 	this.gridMap.get("A1").getRowSum('A') +
						this.gridMap.get("A2").getRowSum('A') +
						this.gridMap.get("A3").getRowSum('A');
				break;
			case 2:
				sum = 	this.gridMap.get("A1").getRowSum('B') +
						this.gridMap.get("A2").getRowSum('B') +
						this.gridMap.get("A3").getRowSum('B');
				break;
			case 3:
				sum = 	this.gridMap.get("A1").getRowSum('C') +
						this.gridMap.get("A2").getRowSum('C') +
						this.gridMap.get("A3").getRowSum('C');
				break;
			case 4:
				sum = 	this.gridMap.get("B1").getRowSum('A') +
						this.gridMap.get("B2").getRowSum('A') +
						this.gridMap.get("B3").getRowSum('A');
				break;
			case 5:
				sum = 	this.gridMap.get("B1").getRowSum('B') +
						this.gridMap.get("B2").getRowSum('B') +
						this.gridMap.get("B3").getRowSum('B');
				break;
			case 6:
				sum = 	this.gridMap.get("B1").getRowSum('C') +
						this.gridMap.get("B2").getRowSum('C') +
						this.gridMap.get("B3").getRowSum('C');
				break;
			case 7:
				sum = 	this.gridMap.get("C1").getRowSum('A') +
						this.gridMap.get("C2").getRowSum('A') +
						this.gridMap.get("C3").getRowSum('A');
				break;
			case 8:
				sum = 	this.gridMap.get("C1").getRowSum('B') +
						this.gridMap.get("C2").getRowSum('B') +
						this.gridMap.get("C3").getRowSum('B');
				break;
			case 9:
				sum = 	this.gridMap.get("C1").getRowSum('C') +
						this.gridMap.get("C2").getRowSum('C') +
						this.gridMap.get("C3").getRowSum('C');
				break;
			default:
					sum = -1;
			}
			return sum;
			
		}
	
		public void printMatrix() {
			System.out.println("_____________________________________");
			for (int i = 1; i <= 9; i++) {
				printRow(i);
				System.out.println("_____________________________________");
			}	
		}
		
		private boolean doChecks(Grid[] theGrid, int row, int col) {
			boolean isSumOk = checkSum(theGrid);
			if (isSumOk) {
				System.out.println("sumIsOK   [r,c] [" + row + "," + col + "]");
				return true;
			}
	
			if (!findUnassignedBox(theGrid)) {
				System.out.println("sumIsOK   [r,c] [" + row + "," + col + "]");
				return true; // Success
			}
	
			if (row >= 10 || col >= 10) {System.out.println("Box  ["+row+","+col+"]");
				return true;
			}
			return false;
			
		}
		
//		// solve() from SSaurel
//		 public boolean solve(Grid[] theGrids, int row, int col) {
//		        for (row = row; row <= 9; row++) {
//		         for (col = col; col <= 9; col++) {
//		          // we search an empty cell
//		          if (getBox(row,col).getValue()==-1/*board[row][col] == EMPTY*/) {
//		            // we try possible numbers
//		            for (int number = 1; number <= 9; number++) {
//		              if (noConflicts(theGrids,row, col, number)) {
//		                // number ok. it respects sudoku constraints
//		                assignValue(theGrids,row,col,number);/*board[row][col] = number;*/
//
//		                if (solve(theGrids,row,col+1)) { // we start backtracking recursively
//		                  return true;
//		                } else { // if not a solution, we empty the cell and we continue
//		                  removeValue(theGrids,row,col);/*board[row][col] = EMPTY;*/
//		                }
//		             }
//		            }
//
//		            return false; // we return false
//		           }
//		          }
//		         }
//
//		         return true; // sudoku solved
//			}

		
		
		public boolean solve(Grid[] theGrids, int row, int col) {
			boolean isSumOk = checkSum(theGrids);//checkSum(this.grids);
			if (isSumOk) {
				return true;
			}	
			if (!findUnassignedBox(theGrids)) {//(this.grids)) {
				return true; // Success
			}
			if(row>=10 || col >= 10) {
				if(col >= 10) {
					row += 1;
					col = 1;
				}
			
				if(row >= 10) {
					return true;
				}
			}
			
			if(getBox(row,col).isPlaced()) {//System.out.println("getBox(row,col).isPlaced() for [r,c] --> ["+row+","+col+"]");
				assignValue(theGrids,row,col,getBox(row,col).getValue());
				return solve(theGrids,row,col+1);
			}

			for ( row = row; row <= 9; row++) {
				for ( col = col; col <= 9; col++) {	
					boolean isFree = isFree(row,col);
//					System.out.println("Is row & col free for [r,c] ("+ row+","+col+") --->>  "+isFree);
					if (isFree) {
						for (int numValue = 1; numValue <= 9; numValue++) {
							//						System.out.println("numValue is  --->> " + numValue + " row,col are [" + row + "," + col + "]");

							if (noConflicts(theGrids, row, col, numValue)) {
								//System.out.println("NoooooOOOCONFLIZX for r,c,n		["+row+","+col+","+numValue+"]");
								assignValue(theGrids,row, col, numValue);

								if (solve(grids, row, col + 1)) {
									return true;
								} /*else {*/
									removeValue(theGrids,row, col);
								/*}*/

							}

						} 
					}
				}
//				boolean isRowSumOK = checkRowSum(row);
//				System.out.println("For row "+row+" isRowSumOK is "+isRowSumOK+ " and sum is "+getRowSum(row));
			} 
			return false;
		}
	
		private boolean isFree(int row, int col) {
			Box theBox = getBox(row,col);
			return !theBox.isAssigned() && !theBox.isPlaced();
		}

		private void removeValue(Grid[] theGrids, int row, int col) {
			assignValue(theGrids, row, col, -1);
		}
		
		private Box getBox(int row, int col) {
			String position = getGridPositionInMatrix(row,col);
			Grid theGrid 	= this.getGridMap().get(position);
			
			char gridRow 	= ( row % 3 == 0) ? 'C' : ( (row % 2 == 0) ? 'B' : 'A');
			int gridCol 	= ( col % 3 == 0 )	? 3 : ( col % 3 );
			
			String gridPosition = String.valueOf(gridRow)+String.valueOf(gridCol);
			Box theBox = theGrid.getGridBoxMap().get( gridPosition );
			return theBox;
		}
		
		private void assignValue(Grid[] theGrids, int row, int col, int numValue) {
			String position = this.getGridPositionInMatrix(row,col);
			Map<String, Grid> gmap = this.createGridMap(theGrids);
			Grid theGrid 	= gmap.get(position);//this.getGridMap().get(position);
			
			char gridRow 	= ( row % 3 == 0) ? 'C' : ( (row % 2 == 0) ? 'B' : 'A');
			int gridCol 	= ( col % 3 == 0 )	? 3 : ( col % 3 );
			
			String gridPosition = String.valueOf(gridRow)+String.valueOf(gridCol);
			Map<String, Box>	gbmap	=	this.createGridBoxMap(theGrid);
			Box theBox = /*theGrid.getGridBoxMap()*/gbmap.get( gridPosition );
			if(!theBox.isPlaced()) {
				theBox.setValue(numValue);				
				if( numValue != -1){
					theBox.setAssigned(true);
				} else {
					theBox.setAssigned(false);
				}
			}else{
				theBox.setValue(numValue);
				theBox.setAssigned(true);
			}
			
			theGrid.getGridBoxMap().put(gridPosition,theBox);
			gbmap.put(gridPosition,theBox);
			this.gridMap.put(position,theGrid);
		}
		
		private Map<String, Box> createGridBoxMap(Grid theGrid) {
			Map<String,Box> gbMap = new HashMap<String,Box>();
			Box[] gridBoxes = theGrid.getBoxes();
			for(int i = 0; i <= gridBoxes.length-1; i++) {
				Box aBox = gridBoxes[i];
				String position = String.valueOf(aBox.getRow())+String.valueOf(aBox.getCol());
				gbMap.put(position,aBox);
			}
			return gbMap;
		}

		private String getGridPositionInMatrix(int row, int col) {
			String position = "";
			if( row <= 3 && col <= 3) {
				position = "A1";
			} else if( row <= 3 && (col > 3 && col <= 6) ) {
				position = "A2";
			} else if ( row <= 3 && col > 6) {
				position = "A3";
			} else if ( (row > 3 && row <= 6) && col <= 3) {
				position = "B1";
			} else if( (row > 3 && row <= 6) && (col > 3 && col <= 6) ) {
				position = "B2";
			} else if( (row > 3 && row <= 6) && col > 6 ) {
				position = "B3";
			} else if( row > 6 && col <= 3) {
				position = "C1";				
			} else if (row > 6 && (col > 3 && col <= 6)) {
				position = "C2";
			} else if(row > 6 && col > 6) {
				position = "C3";
			}
			return position;
		}

		private boolean noConflicts( Grid[] theGrids,int row, int col, int numValue ) {
			/*if (row==2) {
				System.out.println(" IN___noConflicts for r,c,n  [" + row + "," + col + "," + numValue + "]");
				boolean r = usedInRow(row, numValue);
				System.out.println("In---NoConflicts usedInRow is  - " + r);
				boolean c = usedInCol(col, numValue);
				System.out.println("In---NoConflicts usedInCol is  - " + c);
				boolean g = usedInGrid(theGrids,row, col, numValue);
				System.out.println("In---NoConflicts usedInGrid is  - " + g);
				System.out.println("!r&&!c&&!g is  (" + (!r && !c && !g) + ")");
			}*/
			return !usedInRow(row, numValue) && !usedInCol(col, numValue) && !usedInGrid(theGrids, row, col, numValue) ;
		}

		private boolean usedInGrid(Grid[] theGrids, int row, int col, int numValue) {
			String position = this.getGridPositionInMatrix(row,col);
			Map<String,Grid> gMap = this.createGridMap(theGrids);
			Grid theGrid = gMap.get(position);//this.getGridMap().get(position);
			/*if( row==2) {
				System.out.println("forUSED_in_GRID	  ("+row+","+col+","+numValue+")");
				System.out.println("position is "+position);
				System.out.println("theGrid.containsValue(numValue)  -->  "+theGrid.containsValue(numValue));
				Box[] boxs = theGrid.getBoxes();
				System.out.println("GRID--BOXES---For grid position "+position);
				for(int i = 0; i < boxs.length; i++) {
					Box a = boxs[i];
					System.out.println("(r,c,n) is --> ["+a.getRow()+","+a.getCol()+","+a.getValue()+"]");
				}
			}*/
			return theGrid.containsValue(numValue);
		}

		private Map<String, Grid> createGridMap(Grid[] theGrids) {
			Map<String,Grid> gMap = new HashMap<String,Grid>();
			for(int i = 0; i < theGrids.length; i++) {
				Grid aGrid = theGrids[i];
				gMap.put(aGrid.getPosition(),aGrid);
			}
			return gMap;
		}

		private boolean usedInCol(int col, int numValue) {
			Box[] colBoxes = getCol(col);
			for(int i = 0; i < 9; i++) {
				if( colBoxes[i].getValue() == numValue) {
					return true;
				}
			}
			return false;
		}

		private boolean usedInRow(int row, int numValue) {
			Box[] rowBoxes = getRow(row);
			for(int i = 0; i < 9; i++) {
				if( rowBoxes[i].getValue() == numValue ) {
					return true;
				}
			}
			return false;
		}

		private boolean checkSum(Grid[] grids) {
			this.setGrids(grids);
			final int target = ( 9 *  10 ) / 2;
			for(int i = 1; i <= 9; i++) {	//if(i<=2){System.out.println("[checkRowSum	For-i<=2  getRowSUm("+i+") is "+getRowSum(i)+"  and getCOOLSum(i) is "+getColSum(i));}
				if( getRowSum(i) != target || getColSum(i) != target ) {
					return false;
				}				
			}
			return true;
		}
		
		private boolean checkRowSum(int row) {
			final int target = ( 9 * 10 ) / 2;			
			return (getRowSum(row) == target);
		}
		
		private boolean checkColSum(int col) {
			final int target = ( 9 * 10 ) / 2;			
			return (getColSum(col) == target);
		}
	
		private boolean findUnassignedBox(Grid[] theGrids) {
			for (int i = 0; i < theGrids.length; i++) {
				Grid aGrid = theGrids[i];
				Map<String, Box> aMap = aGrid.getGridBoxMap();
				assert (aMap.size() == 9);
				Collection<Box> boxes = aMap.values();
				Iterator<Box> iter = boxes.iterator();
				while (iter.hasNext()) {
					if (iter.next().getValue() == -1) {
						return true;
					}
				}
			}
			return false;
		}

		public Map<String, Grid> getGridMap() {
			return this.gridMap;
		}

		public void setGridMap(Map<String, Grid> gridMap) {
			this.gridMap = gridMap;
		}

		public Grid[] getGrids() {
			return this.grids;
		}

		public void setGrids(Grid[] grids) {
			this.grids = grids;
		}
	}
	
	public class SolveSudoku {	

	public static void main(String[] args) {
		extracted();
	}
		

	private static void extracted() {
		//		int col = 5;
		//		System.out.println((String.valueOf('A') + col).getClass().getName());
				
					/*//ssaurel
					Sudoku grid to solve
					 9 0 0 	1 0 0	0 0 5
					 0 0 5 	0 9 0 	2 0 1
					 8 0 0 	0 4 0 	0 0 0
			
					 0 0 0	0 8 0 	0 0 0
					 0 0 0 	7 0 0 	0 0 0
					 0 0 0	0 2 6 	0 0 9
			
					 2 0 0 	3 0 0 	0 0 6
					 0 0 0 	2 0 0 	9 0 0
					 0 0 1 	9 0 4 	5 7 0*/
				
				Box[] g1 = new Box[3];//new Box[2];
				g1[0] = new Box('A',1,9,true);//new Box('A', 1,	4, true);
		//		g1[1] = new Box('A', 2, 7);
		//		g1[2] = new Box('A', 3,	4, true);
		//		g1[3] = new Box('B', 1, 5);
				g1[1/*4*/] = new Box('B',3,5,true);//new Box('B', 2,	3, true);
				/*g1[5] = new Box('B', 3, 2);
				g1[6] = new Box('C', 1,	1, true);
				g1[7] = new Box('C', 2, 3);
				g1[8] = new Box('C', 3,	9);	*/	
				g1[2] = new Box('C',1,8,true);
				
				Grid aGrid1 = new Grid("A1", null, g1);//, new Box[] {g1[2],g1[6]});
				
		
				Box[] g2 = new Box[3];//new Box[1];
				g2[0]	=	new Box('A',1,1,true);
				g2[1]	=	new Box('B',2,9,true);
				g2[2]	=	new Box('C',2,4,true);
				/*g2[0] = new Box('A', 1,	1);
				g2[1] = new Box('A', 2, 2);
				g2[2] = new Box('A', 3,	3);
				g2[3] = new Box('B', 1, 4);
				g2[4] = new Box('B', 2,	5);
				g2[5] = new Box('B', 3, 6);*/
//				g2[0/*6*/] = new Box('C', 1,	7, true);
				/*g2[7] = new Box('C', 2, 8);
				g2[8] = new Box('C', 3,	9);	*/	
				
				Grid aGrid2 = new Grid("A2", null, g2);//, null);		
				
		
				Box[] g3 = new Box[3];//new Box[2];			
				g3[0] = new Box('A',3,5,true);//new Box('A', 1,	8, true);
				g3[1] = new Box('B',1,2,true);
				g3[2] = new Box('B',3,1,true);
				/*g3[1] = new Box('A', 2, 8);*/
//				g3[1/*2*/] = new Box('A', 3,	5, true);
				/*g3[3] = new Box('B', 1, 6);
				g3[4] = new Box('B', 2,	5);
				g3[5] = new Box('B', 3, 4);
				g3[6] = new Box('C', 1,	3);
				g3[7] = new Box('C', 2, 2, true);
				g3[8] = new Box('C', 3,	1, true);*/		
				
				Grid aGrid3 = new Grid("A3", null, g3);//, new Box[] {g3[0],g3[7],g3[8]});		
				
		
//				Box[] g4 = new Box[1];
		//		g4[0] = new Box('A', 1,	6);
//				g4[0/*1*/] = new Box('A', 2, 2, true);
				/*g4[2] = new Box('A', 3,	4);
				g4[3] = new Box('B', 1, 5);
				g4[4] = new Box('B', 2,	8);
				g4[5] = new Box('B', 3, 2);
				g4[6] = new Box('C', 1,	1);
				g4[7] = new Box('C', 2, 3);
				g4[8] = new Box('C', 3,	9);*/		
				
				Grid aGrid4 = new Grid("B1",null,null); //new Grid("B1", null, g4);//, null);		
				
				
				Box[] g5 = new Box[4];//new Box[2];
				g5[0] = new Box('A',2,8,true);
				g5[1] = new Box('B',1,7,true);
				g5[2] = new Box('C',2,2,true);
				g5[3] = new Box('C',3,6,true);
				/*g5[0] = new Box('A', 1,	6);
				g5[1] = new Box('A', 2, 7, true);
				g5[2] = new Box('A', 3,	4);
				g5[3] = new Box('B', 1, 5, true);*/
//				g5[0/*4*/] = new Box('B', 2,	8, true);
				/*g5[5] = new Box('B', 3, 2);
				g5[6] = new Box('C', 1,	1);*/
//				g5[1/*7*/] = new Box('C', 2, 1, true);
		//		g5[8] = new Box('C', 3,	9);		
				
				Grid aGrid5 = new Grid("B2", null, g5);//, new Box[] {g5[1],g5[3]});		
				
		
				Box[] g6 = new Box[1];//new Box[2];
		//		g6[0] = new Box('A', 1,	7);
				g6[0] = new Box('C',3,9,true);// new Box('A', 2, 6, true);
		//		g6[2] = new Box('A', 3,	5);
//				g6[1] = new Box('B', 1, 4, true);
				/*g6[4] = new Box('B', 2,	2);
				g6[5] = new Box('B', 3, 8);
				g6[6] = new Box('C', 1,	3);
				g6[7] = new Box('C', 2, 1);
				g6[8] = new Box('C', 3,	9);	*/	
				
				Grid aGrid6 = new Grid("B3", null, g6);//, null);
				
			
				Box[] g7 = new Box[2];//new Box[3];
				g7[0] = new Box('A',1,2,true);
				g7[1] = new Box('C',3,1,true);
				/*g7[0] = new Box('A', 1,	6);
				g7[1] = new Box('A', 2, 4);
				g7[2] = new Box('A', 3,	7);*/
//				g7[0] = new Box('B', 1, 5, true);
				/*g7[4] = new Box('B', 2,	5);
				g7[5] = new Box('B', 3, 1);*/
//				g7[1] = new Box('C', 1,	1, true);
		//		g7[7] = new Box('C', 2, 9);
//				g7[2] = new Box('C', 3,	4, true);		
				
				Grid aGrid7 = new Grid("C1", null, g7);//, null);
				
		
				Box[] g8 = new Box[4];//new Box[3];
				g8[0] = new Box('A',1,3,true);
				g8[1] = new Box('B',1,2,true);
				g8[2] = new Box('C',1,9,true);
				g8[3] = new Box('C',3,4,true);
				
//				g8[0] = new Box('A', 1,	6, true);
		//		g8[1] = new Box('A', 2, 5);
//				g8[1] = new Box('A', 3,	3, true);
//				g8[2] = new Box('B', 1, 2, true);
				/*g8[4] = new Box('B', 2,	1);
				g8[5] = new Box('B', 3, 2);
				g8[6] = new Box('C', 1,	8);
				g8[7] = new Box('C', 2, 3, true);
				g8[8] = new Box('C', 3,	9);	*/	
				
				Grid aGrid8 = new Grid("C2", null, g8);//, new Box[] {g8[7]});
				
				
				Box[] g9 = new Box[4];//new Box[1];
				g9[0] = new Box('A',3,6,true);
				g9[1] = new Box('B',1,9,true);
				g9[2] = new Box('C',1,5,true);
				g9[3] = new Box('C',2,7,true);
				
				
		//		g9[0] = new Box('A', 1,	4);
//				g9[0] = new Box('A', 2, 7, true);
				/*g9[2] = new Box('A', 3,	7);
				g9[3] = new Box('B', 1, 8);
				g9[4] = new Box('B', 2,	1);
				g9[5] = new Box('B', 3, 8);
				g9[6] = new Box('C', 1,	2);
				g9[7] = new Box('C', 2, 3);
				g9[8] = new Box('C', 3,	9);	*/	
				
				Grid aGrid9 = new Grid("C3", null, g9);//, null);
				
				
				Grid[] theGrid = new Grid[] { aGrid1, aGrid2, aGrid3, aGrid4, aGrid5,
						aGrid6, aGrid7, aGrid8, aGrid9				
				};
				
				Matrix m = new Matrix(theGrid);
				m.printMatrix();
				
				System.out.println("\n\n----------------------------------\n");
				
//				m.getGridMap().get("A3").printGrid();
				
				m.solve(theGrid, 1, 1);//solve();
				System.out.println("\n\n\n====================================\n\n\n");
				m.printMatrix();
				
				/*System.out.println("\n\n\n_____________sum_2____________________\n"+m.getRowSum(2));
				System.out.println("\n\n\n_____________sum_B____________________\n"+m.getRowSum('B'));
				System.out.println("\n\n\n______________sum_col_4______________\n"+m.getColSum(4));*/
	}

}


