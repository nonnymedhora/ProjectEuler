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
 * Number Mind
 * ================================================
		Problem 185		Ans. 4640261571849533
		
		The game Number Mind is a variant of the well known game Master Mind.
		
		Instead of coloured pegs, you have to guess a secret sequence of digits. 
		After each guess you're only told in how many places you've guessed the correct digit. 
		
		So, if the sequence was 1234 and you guessed 2036, 
		you'd be told that you have one correct digit; 
		however, you would NOT be told that you also have another digit in the wrong place.
		
		For instance, given the following guesses for a 5-digit secret sequence,
		
		90342 ;2 correct
		70794 ;0 correct
		39458 ;2 correct
		34109 ;1 correct
		51545 ;2 correct
		12531 ;1 correct
		
		The correct sequence 39542 is unique.
		
		Based on the following guesses,
		
		5616185650518293 ;2 correct
		3847439647293047 ;1 correct
		5855462940810587 ;3 correct
		9742855507068353 ;3 correct
		4296849643607543 ;3 correct
		3174248439465858 ;1 correct
		4513559094146117 ;2 correct
		7890971548908067 ;3 correct
		8157356344118483 ;1 correct
		2615250744386899 ;2 correct
		8690095851526254 ;3 correct
		6375711915077050 ;1 correct
		6913859173121360 ;1 correct
		6442889055042768 ;2 correct
		2321386104303845 ;0 correct
		2326509471271448 ;2 correct
		5251583379644322 ;2 correct
		1748270476758276 ;3 correct
		4895722652190306 ;1 correct
		3041631117224635 ;3 correct
		1841236454324589 ;3 correct
		2659862637316867 ;2 correct
		
		Find the unique 16-digit secret sequence.
=======================================================================		
.............current best for example (whose correct sequence is is 39542)
Output
=====================================



Answer is 39542

====================================================================
 *
 *
 * A very VERY sneaky problem
 * When I looked at the answer - 464...9533
 * 
 * notice that the 12th digit - 9 - **Does Not** appear in any of the guesses
 * 
 * I made an assumption that each position was guessed at least once
 * Also, an invalid assumption, that overlaps of characters would lead to correct answerString
 */
public class NumberMind {
	
	private static final Map<String, Integer> guessMap = new HashMap<String, Integer>();
	/*static { 			// from example number 39542
		guessMap.put("90342", 2);
		guessMap.put("70794", 0);
		guessMap.put("39458", 2);
		guessMap.put("34109", 1);
		guessMap.put("51545", 2);
		guessMap.put("12531", 1);
	}*/
	
	static {			// 4640261571849533
		guessMap.put("5616185650518293", 2 );
		guessMap.put("3847439647293047", 1 );
		guessMap.put("5855462940810587", 3 );
		guessMap.put("9742855507068353", 3 );
		guessMap.put("4296849643607543", 3 );
		guessMap.put("3174248439465858", 1 );
		guessMap.put("4513559094146117", 2 );
		guessMap.put("7890971548908067", 3 );
		guessMap.put("8157356344118483", 1 );
		guessMap.put("2615250744386899", 2 );
		guessMap.put("8690095851526254", 3 );
		guessMap.put("6375711915077050", 1 );
		guessMap.put("6913859173121360", 1 );
		guessMap.put("6442889055042768", 2 );
		guessMap.put("2321386104303845", 0 );
		guessMap.put("2326509471271448", 2 );
		guessMap.put("5251583379644322", 2 );
		guessMap.put("1748270476758276", 3 );
		guessMap.put("4895722652190306", 1 );
		guessMap.put("3041631117224635", 3 );
		guessMap.put("1841236454324589", 3 );
		guessMap.put("2659862637316867", 2 );

	}
	
	private static String answerString = "";
	
	private static Map<Integer,List<Character>> positionPossValsMap = new HashMap<Integer,List<Character>>();
	private static Map<Integer,List<String>> 	numGuessMap = new HashMap<Integer,List<String>>();
	
	private static Map<Integer,List<Character>> disallowedPossValsMap = new HashMap<Integer,List<Character>>();
	private static Map<Integer, List<Character>> posnUnusedValsMap = new HashMap<Integer, List<Character>>();
	
	public static void main(String[] args) {

		generateXAnswerString();
System.out.println(answerString);
		generateNumGuessMap();
printNumGuessMap(numGuessMap);
		identifyOverlaps();
System.out.println("\nanswer string (after overlaps) is "+answerString);

		List<String> possAnsList = doMatchElimination();

		possAnsList = removeDisallowedVals(possAnsList);

		possAnsList = doXCrossElimination(possAnsList);

		possAnsList = removeXvalsFromList(possAnsList);

		possAnsList = removeGuessVals(possAnsList);

		if (possAnsList.size() == 1) {
			System.out.println("\nAnswer is " + possAnsList.get(0));
		} else {

			possAnsList = doCrossGuessElimination(possAnsList);
			if (possAnsList.size() == 1) {
				System.out.println("\nAnswer is " + possAnsList.get(0));
			} else {
				System.out.println("\nAfter doCross Guesses - remaining size now " + possAnsList.size());
				printListString(possAnsList);
			}
		}
		
		System.out.println("POSSSST");
		posnUnusedValsMap=generatePosnUnusedValsMap(posnUnusedValsMap);
		System.out.println();
		printPossMap(posnUnusedValsMap);
	}


	private static Map<Integer, List<Character>> generatePosnUnusedValsMap(Map<Integer, List<Character>> map) {
		/*final List<Character> startL = getStartListPossVals();	//cannot contain 0
		final List<Character> otherL = generateOtherListPossVals();*/
//		System.out.println("start");
//		printListChar(startL);
//		System.out.println("\nother");
//		printListChar(otherL);
		
		int length = getGuessList().get(0).length();
		
		for (int index = 0; index < length; index++) {
			List<Character> gList = new ArrayList<Character>();
			List<Character> tempL = new ArrayList<Character>();
			
			if (index == 0) {
				tempL = getStartListPossVals();//startL;
			} else {
				tempL = generateOtherListPossVals();//otherL;
			}

			for (String aGuess : getGuessList()) {
				if (guessMap.get(aGuess) != 0) {
					char gChar = aGuess.charAt(index);
					if (!gList.contains(gChar)) {
						gList.add(gChar);
					}
				}
			}
			
			for (String aGuess : getGuessList()) {
				if (guessMap.get(aGuess) == 0) {
					char gChar = aGuess.charAt(index);
					if (gList.contains(gChar)) {
						gList.remove(gList.indexOf(gChar));//(index);//
					}
					
					if(tempL.contains(gChar)){
						tempL.remove(tempL.indexOf(gChar));
					}
				}
			}
			
			
			
//System.out.println("\ngList for "+index);		printListChar(gList);	
			tempL.removeAll(gList);
//			System.out.println();
//System.out.println("tempL For "+index);printListChar(tempL);
			map.put(index, tempL);

		}
		
		
		return map;
	}


	private static List<Character> generateOtherListPossVals() {
		List<Character> aList = new ArrayList<Character>();
		for(int i = 0; i <= 9;i++){
			aList.add(String.valueOf(i).charAt(0));
		}
		return aList;
	}


	private static List<Character> getStartListPossVals() {
		List<Character> aList = new ArrayList<Character>();
		for(int i = 1; i <= 9;i++){
			aList.add(String.valueOf(i).charAt(0));
		}
		return aList;
	}


	private static List<String> doCrossGuessElimination(List<String> pList) {
		List<String> vals2Remove = new ArrayList<String>();

		for (String aVal : pList) {

			for (int numGuess : numGuessMap.keySet()) {
				for (String aGuess : numGuessMap.get(numGuess)) {
					int matchCount = 0;
					for (int i = 0; i < aVal.length(); i++) {
						if (aVal.charAt(i) == aGuess.charAt(i)) {
							matchCount += 1;
						}
					}
					if (numGuess != matchCount) {
						vals2Remove.add(aVal);
					}

				}
			}
		}

		pList.removeAll(vals2Remove);
		return pList;
	}


	private static List<String> removeGuessVals(List<String> possList) {
		List<String> vals2Remove = new ArrayList<String>();
		List<String> guessList = getGuessList();
		for (String aVal : possList) {
			if (guessList.contains(aVal)) {
				vals2Remove.add(aVal);
			}
		}
		possList.removeAll(vals2Remove);
		return possList;
	}


	private static List<String> removeXvalsFromList(List<String> possAnsList) {
		List<String> vals2Remove = new ArrayList<String>();
		for (String aVal : possAnsList) {
			for (int i = 0; i < aVal.length(); i++) {
				if (aVal.charAt(i) == 'X') {
					vals2Remove.add(aVal);
				}
			}
		}
		possAnsList.removeAll(vals2Remove);
		return possAnsList;
	}


	private static List<String> doXCrossElimination(List<String> aList) {
		List<Integer> xPosList = getXPosList();
		Map<Integer, Integer> xPosNumMap = new HashMap<Integer, Integer>();
		for (int xPos : xPosList) {
			for (String aPoss : aList) {
				if (aPoss.charAt(xPos) == 'X') {
					if (xPosNumMap.get(xPos) == null) {
						xPosNumMap.put(xPos, 1);
					} else {
						int exVal = xPosNumMap.get(xPos);
						exVal += 1;
						xPosNumMap.put(xPos, exVal);
					}
				}
			}
		}

		Map<Integer, Character> posCharsFoundMap = new HashMap<Integer, Character>();
		int comp = aList.size() - 1;
		for (int xnum : xPosNumMap.keySet()) {
			if (xPosNumMap.get(xnum) == comp) {
				for (String aPoss : aList) {
					if (aPoss.charAt(xnum) != 'X') {
						char theChar2Replace = aPoss.charAt(xnum);
						String p1 = answerString.substring(0, xnum);
						String p2 = answerString.substring(xnum + 1);
						answerString = p1 + theChar2Replace + p2;

						posCharsFoundMap.put(xnum, theChar2Replace);
					}
				}
			}
		}

		for (int pos : posCharsFoundMap.keySet()) {
			for (int i = 0; i < aList.size(); i++) {
				String possValStr = aList.get(i);
				char theChar2Replace = posCharsFoundMap.get(pos);
				String p1 = possValStr.substring(0, pos);
				String p2 = possValStr.substring(pos + 1);
				possValStr = p1 + theChar2Replace + p2;
				aList.set(i, possValStr);
			}
		}

		return aList;
	}


	private static List<String> removeDisallowedVals(List<String> aList) {
		List<String> items2Remove = new ArrayList<String>();
		for (String aVal : aList) {
			for (int i = 0; i < aVal.length(); i++) {
				if (disallowedPossValsMap.get(i).contains(aVal.charAt(i))) {
					items2Remove.add(aVal);
				}
			}
		}

		for (String rem : items2Remove) {
			aList.remove(rem);
		}

		return aList;
	}


	private static List<String> doMatchElimination() {
		List<Integer> xPosList = getXPosList();

		List<String> possibleAnswers = new ArrayList<String>();

		for (int key : numGuessMap.keySet()) {
			if (key != 1) {
				for (String aVal : numGuessMap.get(key)) {

					String[] tempS = createXTemps(xPosList);

					int matchCount = 0;
					for (int i = 0; i < answerString.length(); i++) {
						if (aVal.charAt(i) == answerString.charAt(i)) {
							matchCount += 1;
						}
					}
					if (matchCount != key) { // ||matchCount<key
						for (int i = 0; i < xPosList.size(); i++) {
							int char2ReplaceIndex = xPosList.get(i);

							String p1 = tempS[i].substring(0, xPosList.get(i));
							String p2 = tempS[i].substring(xPosList.get(i) + 1);

							tempS[i] = p1 + aVal.charAt(char2ReplaceIndex) + p2;

							possibleAnswers.add(tempS[i]);

						}
					}
				}
			}
		}

		return possibleAnswers;
	}


	private static void printList(List<Integer> list) {
		String s = "[";
		for (int i = 0; i < list.size(); i++) {
			if (i != list.size() - 1) {
				s += list.get(i) + ", ";
			} else {
				s += list.get(i) + "]";
			}
		}
		System.out.print(s);
	}


	private static List<Integer> getXPosList() {
		List<Integer> xPosList = new ArrayList<Integer>();
		for (int i = 0; i < answerString.length(); i++) {
			if (answerString.charAt(i) == 'X') {
				xPosList.add(i);
			}
		}
		return xPosList;
	}


	private static String[] createXTemps(List<Integer> xPosList) {
		String tempString = new String(answerString.toCharArray());
		String[] tempS = new String[xPosList.size()];

		for (int i = 0; i < tempS.length; i++) {
			tempS[i] = tempString;
		}
		return tempS;
	}


	private static List<char[]> pruneSingularites(List<char[]> possList) {
		List<List<Character>> singlesOk = new ArrayList<List<Character>>();

		List<String> posSingVals = numGuessMap.get(1);
		final int length = posSingVals.get(0).length();
		for(int i = 0; i < length; i++){
			List<Character> single = new ArrayList<Character>();
			for(int val = 0; val < posSingVals.size(); val++){
				char aChar = posSingVals.get(val).charAt(i);
				single.add(aChar);				
			}
			singlesOk.add(single);
		}
		
		//totdo
		for(int i = 0; i < length; i++){
			List<Character> theList = singlesOk.get(i);
			System.out.print(i+" ==>  ");
			printListChar(theList);
		}
		
		List<char[]> items2Remove = new ArrayList<char[]>();
		for(int i = 0; i < possList.size(); i++){
			char[] possble = possList.get(i);
			
			for(int s = 0; s < singlesOk.size(); s++){
				List<Character> sCharList = singlesOk.get(s);
				if(!sCharList.contains(possble[s])){
					items2Remove.add(possble);
				}
			}
		}
		System.out.println("\nitems2remove size is "+items2Remove.size()+" and possList suze is "+possList.size());
		for(char[] toRemove : items2Remove){
			possList.remove(toRemove);
		}

		boolean allOk = true;

		for (char[] posss : possList) {
			for (int posn = 0; posn < singlesOk.size(); posn++) {
				if(!singlesOk.get(posn).contains(posss[posn])){
					allOk=false;
				}
			}
		}
			
			
		
		if (allOk) {	System.out.println("ALllokkkSINGLES");
			return possList;
		}
		return null;
	}


	private static List<char[]> prunePossList(List<char[]> possList) {
		List<Integer> nonXPosList = new ArrayList<Integer>();
		List<Character> nonXPosValsList = new ArrayList<Character>();
		for (int i = 0; i < answerString.length(); i++) {
			if (answerString.charAt(i) != 'X') {
				nonXPosList.add(i);
				nonXPosValsList.add(answerString.charAt(i));
			}
		}
		
		List<char[]> items2Remove = new ArrayList<char[]>();
		for (int i = 0; i < possList.size(); i++) {
			char[] possble = possList.get(i);
			for (int posn = 0; posn < nonXPosList.size(); posn++) {
				
				if (possble[nonXPosList.get(posn)] != nonXPosValsList.get(posn)) {
					items2Remove.add(possble);
				}
			}
		}
		
		//System.out.println("possList isze is "+possList.size()+" and items2Removevsize is "+items2Remove.size());
		
		for(char[] toRemove : items2Remove){
			possList.remove(toRemove);
		}
		
		//asert-removal-check
		//all-remaining-choices-mst-have-nonX-value-in-correct-posn
		boolean allOk = true;

		for (char[] posss : possList) {
			for (int posn = 0; posn < nonXPosList.size(); posn++) {
				if(posss[nonXPosList.get(posn)]!=nonXPosValsList.get(posn)){
					allOk=false;
				}
			}
		}
			
			
		
		if (allOk) {	//System.out.println("ALllokkk");
			return possList;
		}
		return null;
	}
	private static Map<Integer, List<Character>> add2Col0OverLayMap(Map<Integer, List<Character>> ZMap, String aVal) {
		if(ZMap.size()==0){
			for(int i = 0; i < aVal.length(); i++){
				List<Character> chars = new ArrayList<Character>();
				chars.add(aVal.charAt(i));
				ZMap.put(i,chars);
			}
		}else{
			for(int i = 0; i < aVal.length(); i++){
				List<Character> exChars = ZMap.get(i);
				exChars.add(aVal.charAt(i));
				ZMap.put(i,exChars);
			}
		}
		return ZMap;
	}


	private static Map<Integer, Map<Character, Integer>> add2ColOverLayMap(Map<Integer, Map<Character, Integer>> map, String aVal) {
		if (map.size() == 0) {
			for (int i = 0; i < aVal.length(); i++) {
				Map<Character, Integer> aMap = new HashMap<Character, Integer>();

				char aChar = aVal.charAt(i);

				aMap.put(aChar, 1);

				map.put(i, aMap);

			}
		} else {
			for (int u = 0; u < aVal.length(); u++) {
				Map<Character, Integer> exMap = map.get(u);

				char aChar = aVal.charAt(u);

				if (exMap.keySet().contains(aChar)) {
					int exVal = exMap.get(aChar);
					exVal += 1;
					exMap.put(aChar, exVal);
					map.put(u, exMap);
				} else {
					exMap.put(aChar, 1);
					map.put(u, exMap);
				}
			}
		}
		return map;
	}
	
	private static void identifyOverlaps() {
		//just-identifies-overlaps-first
		
		// stores per column, those values from numGuessMap which are the same across guesses
		Map<Integer,Map<Character,Integer>> colOverlayMap = new HashMap<Integer,Map<Character,Integer>>();
		
		//remove-for-zeroes
		Map<Integer,List<Character>> col0OverlayMap = new HashMap<Integer,List<Character>>();
		
		for (String key : guessMap.keySet()) {
			int val = guessMap.get(key);
			if (val != 0) {
				colOverlayMap = add2ColOverLayMap(colOverlayMap, key);
			} else {
				col0OverlayMap = add2Col0OverLayMap(col0OverlayMap, key);
			}			
		}
		
//		printOverlayMap(colOverlayMap);
//		printZOverlayMap(col0OverlayMap);
		
		colOverlayMap = removeZVals(colOverlayMap,col0OverlayMap);
		
//		printOverlayMap(colOverlayMap);
		
		int length = getGuessList().get(0).length();
		for(int i = 0; i < length;i++){
			Map<Character, Integer> aMap = colOverlayMap.get(i);
			char tempChar = 'Z';
			int maxCharCount = -1;

			for (char aChar : aMap.keySet()) {
				int exVal = aMap.get(aChar);
				if (exVal > maxCharCount) {
					maxCharCount = exVal;
					tempChar = aChar;
				}
			}
			
			if (maxCharCount >= 2) {
				String p1 = answerString.substring(0, i);
				String p2 = answerString.substring(i + 1);
				answerString = p1 + tempChar + p2;
			}
		}
	}




	private static void printZOverlayMap(Map<Integer, List<Character>> ZMap) {
		int length = getGuessList().get(0).length();
		System.out.println("\nZMap");
		for(int i = 0; i < length; i++){
			System.out.print(" "+i+"==>");
			if(ZMap.get(i)!=null){printListChar(ZMap.get(i));}
			else{System.out.print("null\n");}
		}System.out.println();
		
	}


	private static void printOverlayMap(Map<Integer, Map<Character, Integer>> oMap) {
		int length = getGuessList().get(0).length();
		StringBuilder s = new StringBuilder("\nOverlay Map\n");
		for(int i = 0; i < length; i++){
			s.append("\n"+i+"==> [");
			Map<Character,Integer> exMap = oMap.get(i);
			for(char aChar : exMap.keySet()){
				s.append("("+aChar+","+exMap.get(aChar)+")");
			}
			s.append("]");
		}
		System.out.println(s.toString());
	}


	private static Map<Integer, Map<Character, Integer>> removeZVals(Map<Integer, Map<Character, Integer>> aMap, Map<Integer, List<Character>> ZMap) {
		for (int key : aMap.keySet()) {
			Map<Character, Integer> exMap = aMap.get(key);
			if (ZMap.get(key) != null) {
				for (Character zchar : ZMap.get(key)) {
					exMap.remove(zchar);
				}
			}
			aMap.put(key, exMap);
		}
		return aMap;
	}


	private static void identifyOverlaps11() {
		//assumes-overlaps-are-correct-guesses
		//iteration-done-per-num-guess-grouping
		for (int key : numGuessMap.keySet()) {
			List<String> vals = numGuessMap.get(key);

//			if (vals.size() > 1) {
				String test = vals.get(0);

				for (int i = 0; i < test.length(); i++) {
					for (int v = 1; v < vals.size(); v++) {
						String val = vals.get(v);
						if (test.charAt(i) == val.charAt(i)) {
							String p1 = answerString.substring(0, i);
							String p2 = answerString.substring(i + 1);
							answerString = p1 + test.charAt(i) + p2;
System.out.println("key=="+key+" i == "+i+" v is "+v+" for test "+test+" [answerString] is "+answerString);						
						}
					}
//				}
			}
		}

		List<String> singVals = numGuessMap.get(1);
		List<String> compareList = new ArrayList<String>();
		for (int key : numGuessMap.keySet()) {
			if (key != 1) {
				compareList.addAll(numGuessMap.get(key));
			}
		}
		for (String comp : compareList) {
			for (String sing : singVals) {
				for (int i = 0; i < sing.length(); i++) {
					if (sing.charAt(i) == comp.charAt(i)) {
						String p1 = answerString.substring(0, i);
						String p2 = answerString.substring(i + 1);
						answerString = p1 + sing.charAt(i) + p2;
						System.out.println(" i == "+i+" [answerString] is "+answerString);						
					}
				}
			}
		}

	}


	private static void generateXAnswerString() {
		String sample = getGuessList().get(0);
		for (int i = 0; i < sample.length(); i++) {
			answerString += "X";
		}
	}


	private static void generateNumGuessMap() {
		for (String gKey : guessMap.keySet()) {
			if (guessMap.get(gKey) != 0) {
				add2NumGuessMap(guessMap.get(gKey), gKey);
			} else {
				add2DisallowedPossMap(gKey);
			}
		}
	}

	private static void add2DisallowedPossMap(String aString) {
		for (int i = 0; i < aString.length(); i++) {
			add2DisallowedPossValsMap(i, aString.charAt(i));
		}
	}


	private static void add2DisallowedPossValsMap(int key, char aChar) {
		if (disallowedPossValsMap.get(key) == null) {
			List<Character> aList = new ArrayList<Character>();
			aList.add(aChar);
			disallowedPossValsMap.put(key, aList);
		} else {
			List<Character> exValList = disallowedPossValsMap.get(key);
			exValList.add(aChar);
			disallowedPossValsMap.put(key, exValList);
		}
	}


	private static void add2NumGuessMap(final Integer key, final String val2Add) {
		if (numGuessMap.get(key) == null) {
			List<String> valList = new ArrayList<String>();
			valList.add(val2Add);
			numGuessMap.put(key, valList);
		} else {
			List<String> exValList = numGuessMap.get(key);
			exValList.add(val2Add);
			numGuessMap.put(key, exValList);
		}
	}


	private static List<char[]> generateAllPossibleOptions() {
		int length = positionPossValsMap.size();
		char[][] matrix = new char[length][];

		for (int i = 0; i < length; i++) {
			matrix[i] = toArray(positionPossValsMap.get(i));
		}
		
		List<char[]> allPossList = combine(matrix);
		
		return allPossList;
		
		
		
		
		
		/*
		
		for(int i = 0; i < possMatrix[0].length; i++){	//	start row = 0
			String poss = String.valueOf(possMatrix[0][i]);
			int col = 0;
//			for ( col = 0; col < possMatrix.length; col++) {
//				String poss = String.valueOf(possMatrix[i][col]);
				for (int row = 1; row < possMatrix.length; row++) {
					//poss+=String.valueOf(possMatrix[row][0]);
					for (; col < possMatrix[row].length; col++) {
						poss += String.valueOf(possMatrix[row][col]);
						break;
					}

				}
				System.out.println("Poss === " + poss);
//			}
			
		}*/
		
		
		
		
		
		
//		int ll = positionPossValsMap.size();
//		int[] optionCounts = new int[ll];
//		for (int index = 0; index < ll; index++) {
//			optionCounts[index] = positionPossValsMap.get(index).size();
//		}
//		for(int i = 0; i < ll; i++){
//			for(int count : optionCounts){//System.out.println(count);
//				for(int j = 0; j < count; j++){
//						String poss = "";
//						try {
//							poss += positionPossValsMap.get(i).get(count);
//						}catch(Exception e){
//							/*System.out.println("Posss "+poss);
//							System.out.println("J is "+j);
//							System.out.println("Count is "+count);
//							System.out.println("i is "+ i);
//							e.printStackTrace();*/
//						}
//						System.out.println("poss === "+poss);
//					
//				}
//			}
//		}
		
		
		
		
		
		
		/*List<String> posnPossList = new ArrayList<String>();
		final List<String> guessList = getGuessList();
		// all guesses have same length
		final int length = guessList.get(0).length();
		
		for(int i = 0; i < length; i++){
			String poss = "";
			for(char aChar : positionPossValsMap.get(i)){
				poss += aChar;
			}
			posnPossList.add(poss);
		}
		System.out.println("Possibilities");
		printListString(posnPossList);
		
		for(int i = 0; i < posnPossList.size(); i++ ){
			int choices4index = posnPossList.get(i).length();
			String choice = "";
			for(int j = 0; j < choices4index; j++){
				choice+=posnPossList.get(i).charAt(j);
			}
			System.out.println(choice);
		}*/
	}


	private static char[] toArray(List<Character> list) {
		char[] chars = new char[list.size()];
		for (int i = 0; i < list.size(); i++) {
			chars[i] = list.get(i);
		}
		return chars;
	}


	private static void generatePositionPossibleValsMap() {
		final List<String> guessList = getGuessList();
		// all guesses have same length
		final int length = guessList.get(0).length();

		for (int i = 0; i < length; i++) {
			List<Character> charPossList = new ArrayList<Character>();

			for (String guess : guessList) {
				if (!charPossList.contains(guess.charAt(i))) {
					charPossList.add(guess.charAt(i));
				}
			}

			positionPossValsMap.put(i, charPossList);
		}
		printPossMap(positionPossValsMap);
		
		// removing possibilities in map position values where guess had 0 correct
		for(String guess: guessMap.keySet()){
			if(guessMap.get(guess)==0){
				for(int i = 0; i < guess.length(); i++){
					Character ch = guess.charAt(i);
					List<Character> possChVals = positionPossValsMap.get(i);
					possChVals.remove(ch);
					
					positionPossValsMap.put(i,possChVals);
				}
			}
		}

	}


	private final static List<String> getGuessList() {
		List<String> guessList = new ArrayList<String>();
		for (String key : guessMap.keySet()) {
			guessList.add(key);
		}
		return guessList;
	}

	private static void printPossMap(Map<Integer, List<Character>> possMap) {
		for (int key : possMap.keySet()) {
			System.out.print(key + " ==> ");
			printListChar(possMap.get(key));
			System.out.println();
		}
		System.out.println("================================");
	}
	
	private static void printNumGuessMap(Map<Integer, List<String>> aMap) {
		for (int key : aMap.keySet()) {
			System.out.print(key + " ==> ");
			printListString(aMap.get(key));
			System.out.println();
		}
		System.out.println("================================");
	}


	private static void printListChar(List<Character> list) {
		if (list == null || list.size() == 0) {
			System.out.print("Empty");
			return;
		}
		String s = "[";
		for (int i = 0; i < list.size(); i++) {
			if (i != list.size() - 1) {
				s += list.get(i) + ", ";
			} else {
				s += list.get(i) + "]";
			}
		}
		System.out.print(s);
	}
	
	private static void printListString(List<String> list) {
		if (list == null || list.size() == 0) {
			System.out.print("Empty\n");
			return;
		}
		String s = "[";
		for (int i = 0; i < list.size(); i++) {
			if (i != list.size() - 1) {
				s += list.get(i) + ", ";
			} else {
				s += list.get(i) + "]";
			}
		}
		System.out.print(s+"\n");
	}
	
	
	
	
	//not-used
	//https://stackoverflow.com/questions/41453353/return-all-combinations-from-the-matrix-in-java
	private static List<char[]> combine(char[][] matrix) {
        int sizeArray[] = new int[matrix.length];
        int counterArray[] = new int[matrix.length];
        int total = 1;
        for (int i = 0; i < matrix.length; ++i) {
            sizeArray[i] = matrix[i].length;
            total *= matrix[i].length;
        }
        List<char[]> list = new ArrayList<>(total);
        StringBuilder sb;
        for (int count = total; count > 0; --count) {
            sb = new StringBuilder();
            for (int i = 0; i < matrix.length; ++i) {
                sb.append(matrix[i][counterArray[i]]);
            }
            char tmpi[] = new char[sb.toString().length()];
            for (int tmp = 0; tmp < sb.toString().length(); tmp++) {
                tmpi[tmp] = sb.toString().toCharArray()[tmp];//Integer.parseInt("" + sb.toString().toCharArray()[tmp]);
            }
            list.add(tmpi);
            for (int incIndex = matrix.length - 1; incIndex >= 0; --incIndex) {
                if (counterArray[incIndex] + 1 < sizeArray[incIndex]) {
                    ++counterArray[incIndex];
                    break;
                }
                counterArray[incIndex] = 0;
            }
        }
        return list;
    }
	/*int i = 0;
	for (char[] c : (combine(matrix))) {
		System.out.println(Arrays.toString(c));
		i++;
	}
	System.out.println(i);*/
	
	
	private static void printMatrix(final char[][] matrix) {
		final int length = matrix.length;
		System.out.println("=========Matrix is===========:");
		for (int row = 0; row < length; row++) {
			System.out.println(Arrays.toString(matrix[row]));
		}
		System.out.println("===========================");
	}
	
	private static void printListCharArr(List<char[]> list) {
		final int size = list.size();
		System.out.println("=========List<char[]> is===========:");
		for (int row = 0; row < size; row++) {
			System.out.println(Arrays.toString(list.get(row)));
		}
		System.out.println("===========================");
	}


}


/**
 * JUNK
 //TODO
 
 		v1
 key==2 i == 3 v is 2 for test 90342 [answerString] is XXX4X
 i == 0 [answerString] is 3XX4X
 i == 2 [answerString] is 3X54X

Answer is 39542

		v2
XXXXX
1 ==> [12531, 34109]
2 ==> [90342, 39458, 51545]
================================

Overlay Map

0==> [(1,1)(3,2)(5,1)(9,1)]
1==> [(0,1)(1,1)(2,1)(4,1)(9,1)]
2==> [(1,1)(3,1)(4,1)(5,2)]
3==> [(0,1)(3,1)(4,2)(5,1)]
4==> [(1,1)(2,1)(5,1)(8,1)(9,1)]

ZMap
 0==>[7] 1==>[0] 2==>[7] 3==>[9] 4==>[4]

Overlay Map

0==> [(1,1)(3,2)(5,1)(9,1)]
1==> [(1,1)(2,1)(4,1)(9,1)]
2==> [(1,1)(3,1)(4,1)(5,2)]
3==> [(0,1)(3,1)(4,2)(5,1)]
4==> [(1,1)(2,1)(5,1)(8,1)(9,1)]

answer string (after overlaps) is 3X54X
---	thencontinues -- allOk
		
		v3
XXXXXXXXXXXXXXXX
1 ==> [4895722652190306, 8157356344118483, 6375711915077050, 3174248439465858, 6913859173121360, 3847439647293047]
2 ==> [5251583379644322, 2659862637316867, 6442889055042768, 2615250744386899, 2326509471271448, 4513559094146117, 5616185650518293]
3 ==> [7890971548908067, 3041631117224635, 5855462940810587, 8690095851526254, 4296849643607543, 1841236454324589, 9742855507068353, 1748270476758276]
================================

Overlay Map

0==> [(1,2)(2,3)(3,3)(4,3)(5,3)(6,3)(7,1)(8,2)(9,1)]
1==> [(0,1)(1,2)(2,2)(3,2)(4,1)(5,1)(6,4)(7,2)(8,5)(9,1)]
2==> [(1,4)(2,1)(4,6)(5,4)(7,2)(9,4)]
3==> [(0,2)(1,3)(2,2)(3,2)(4,1)(5,4)(6,3)(7,2)(8,1)(9,1)]
4==> [(0,1)(1,1)(2,4)(3,1)(4,2)(5,3)(6,1)(7,2)(8,5)(9,1)]
5==> [(0,1)(1,1)(2,1)(3,3)(4,2)(5,5)(6,2)(7,2)(8,3)(9,1)]
6==> [(0,2)(1,3)(2,3)(3,1)(5,3)(6,2)(8,1)(9,6)]
7==> [(0,2)(1,2)(3,2)(4,4)(5,2)(6,5)(7,1)(8,1)(9,2)]
8==> [(0,1)(1,2)(3,2)(4,6)(5,5)(7,4)(9,1)]
9==> [(0,2)(1,2)(2,1)(3,2)(4,4)(5,2)(6,1)(7,4)(8,1)(9,2)]
10==> [(0,3)(1,4)(2,3)(3,3)(4,1)(5,2)(6,2)(7,1)(8,1)(9,1)]
11==> [(0,2)(1,4)(2,4)(4,3)(5,1)(6,2)(7,2)(8,1)(9,2)]
12==> [(0,2)(1,2)(2,1)(3,1)(4,3)(5,1)(6,4)(7,2)(8,5)]
13==> [(0,3)(1,1)(2,3)(3,4)(4,2)(5,3)(6,1)(7,1)(8,3)]
14==> [(0,1)(1,1)(2,1)(3,1)(4,3)(5,4)(6,4)(7,1)(8,3)(9,2)]
15==> [(0,2)(2,1)(3,4)(4,1)(5,1)(6,2)(7,5)(8,3)(9,2)]

ZMap
 0==>[2] 1==>[3] 2==>[2] 3==>[1] 4==>[3] 5==>[8] 6==>[6] 7==>[1] 8==>[0] 9==>[4] 10==>[3] 11==>[0] 12==>[3] 13==>[8] 14==>[4] 15==>[5]

Overlay Map

0==> [(1,2)(3,3)(4,3)(5,3)(6,3)(7,1)(8,2)(9,1)]
1==> [(0,1)(1,2)(2,2)(4,1)(5,1)(6,4)(7,2)(8,5)(9,1)]
2==> [(1,4)(4,6)(5,4)(7,2)(9,4)]
3==> [(0,2)(2,2)(3,2)(4,1)(5,4)(6,3)(7,2)(8,1)(9,1)]
4==> [(0,1)(1,1)(2,4)(4,2)(5,3)(6,1)(7,2)(8,5)(9,1)]
5==> [(0,1)(1,1)(2,1)(3,3)(4,2)(5,5)(6,2)(7,2)(9,1)]
6==> [(0,2)(1,3)(2,3)(3,1)(5,3)(8,1)(9,6)]
7==> [(0,2)(3,2)(4,4)(5,2)(6,5)(7,1)(8,1)(9,2)]
8==> [(1,2)(3,2)(4,6)(5,5)(7,4)(9,1)]
9==> [(0,2)(1,2)(2,1)(3,2)(5,2)(6,1)(7,4)(8,1)(9,2)]
10==> [(0,3)(1,4)(2,3)(4,1)(5,2)(6,2)(7,1)(8,1)(9,1)]
11==> [(1,4)(2,4)(4,3)(5,1)(6,2)(7,2)(8,1)(9,2)]
12==> [(0,2)(1,2)(2,1)(4,3)(5,1)(6,4)(7,2)(8,5)]
13==> [(0,3)(1,1)(2,3)(3,4)(4,2)(5,3)(6,1)(7,1)]
14==> [(0,1)(1,1)(2,1)(3,1)(5,4)(6,4)(7,1)(8,3)(9,2)]
15==> [(0,2)(2,1)(3,4)(4,1)(6,2)(7,5)(8,3)(9,2)]

answer string (after overlaps) is 3845859647118357
		
aaaaarggggh!
 */