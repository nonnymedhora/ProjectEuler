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
STILL WIP.............current best for example (whose ans is 39542)
AnswerString is 3X54X
====================================================================
 *
 */
public class NumberMind {
	
	private static final Map<String, Integer> guessMap = new HashMap<String, Integer>();
	static { 			// number 39542
		guessMap.put("90342", 2);
		guessMap.put("70794", 0);
		guessMap.put("39458", 2);
		guessMap.put("34109", 1);
		guessMap.put("51545", 2);
		guessMap.put("12531", 1);
	}
	
	private static String answerString = "";
	
	private static Map<Integer,/*char[]*/List<Character>> positionPossValsMap = new HashMap<Integer,List<Character>>();
	private static Map<Integer,List<String>> numGuessMap = new HashMap<Integer,List<String>>();
	
	public static void main(String[] args) {
		/*String texst = "XXXXXXXX";
		int i = 4;
		String p1 = texst.substring(0,i);
		String p2 = texst.substring(i+1);
		texst=p1+'5'+p2;
		System.out.println(texst);*/
		
		
		generateBlankAnswerString();
		
		generatePositionPossibleValsMap();
		printPossMap(positionPossValsMap);
		
		List<char[]> allPossList = generateAllPossibleOptions();		
		System.out.println("B4 allPossList size is "+allPossList.size());
//		printListCharArr(allPossList);
		
		generateNumGuessMap();
		System.out.println("numGuessMap");
		printNumGuessMap(numGuessMap);
		
		identifyOverlaps();
		System.out.println("AnswerString is "+answerString);
		
		/*List<String> possAnsList = doMatchElimination();
		System.out.println("AFTER___ matchElimination----sizeOfList<string> is "+possAnsList.size());
		printListString(possAnsList);*/
		
		allPossList = prunePossList(allPossList);
		System.out.println("After PRUNING, allPossList size is "+allPossList.size());
		printListCharArr(allPossList);
		
		/*
		allPossList = doCrossPrunePossList(allPossList,possAnsList);*/
		
//		allPossList = pruneSingularites(allPossList);
//		System.out.println("\n\nAfter SINGLEPRUNING, allPossList size is "+allPossList.size());
		
	}


	private static List<char[]> doCrossPrunePossList(List<char[]> allPossList, List<String> possAnsList) {
		// TODO Auto-generated method stub
		return null;
	}


	private static List<String> doMatchElimination() {
		List<Integer> xPosList = getXPosList();

		List<String> possibleAnswers = new ArrayList<String>();
		
		for(int key : numGuessMap.keySet()){
			if(key!=1){
				for (String aVal : numGuessMap.get(key)) {

					String[] tempS = createXTemps(xPosList);
					
					int matchCount = 0;
					for(int i = 0; i <answerString.length(); i++){
						if(aVal.charAt(i)==answerString.charAt(i)){
							matchCount+=1;
						}
					}
					if(matchCount!=key){	//||matchCount<key
						for(int i = 0; i < xPosList.size();i++){
							int char2ReplaceIndex = xPosList.get(i);//aVal.indexOf(xPosList.get(i));
//System.out.println("For val "+aVal+ " char2ReplaceIndex is "+char2ReplaceIndex+ " whicb is "+aVal.charAt(char2ReplaceIndex));							
							String p1 = tempS[i].substring(0,xPosList.get(i));
							String p2 = tempS[i].substring(xPosList.get(i)+1);
							
							tempS[i]= p1+aVal.charAt(char2ReplaceIndex)+p2;
//System.out.println("For val "+aVal+" i s "+i+" and tempS[i] is "+tempS[i]);							
							possibleAnswers.add(tempS[i]);
							
						}
					}
				}
			}/*else{
				//key==1;
				for (String aVal : numGuessMap.get(key)) {
					String[] tempS = createXTemps(xPosList);
					for(int i = 0; i < xPosList.size();i++){
						int char2ReplaceIndex = xPosList.get(i);//aVal.indexOf(xPosList.get(i));
						
						String p1 = tempS[i].substring(0,xPosList.get(i));
						String p2 = tempS[i].substring(xPosList.get(i)+1);
						
						tempS[i]= p1+char2ReplaceIndex+p2;
						possibleAnswers.add(tempS[i]);
						
					}
				}
			}*/
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


	private static void identifyOverlaps() {
		for(int key : numGuessMap.keySet()){
			List<String> vals = numGuessMap.get(key);
			
			if(vals.size()>1){
				String test = vals.get(0);
				
				for(int i = 0; i < test.length(); i++){
					for(int v = 1; v < vals.size(); v++){
						String val = vals.get(v);
						if(test.charAt(i)==val.charAt(i)){
							String p1 = answerString.substring(0,i);
							String p2 = answerString.substring(i+1);
							answerString = p1+test.charAt(i)+p2;
						}
					}
				}
			}
		}
		
		List<String> singVals = numGuessMap.get(1);
		List<String> compareList = new ArrayList<String>();
		for(int key : numGuessMap.keySet()){
			if(key!=1){
				compareList.addAll(numGuessMap.get(key));
			}
		}
		for(String comp : compareList){
			for(String sing : singVals){
				for(int i = 0; i <sing.length(); i++){
					if(sing.charAt(i)==comp.charAt(i)){
						String p1 = answerString.substring(0,i);
						String p2 = answerString.substring(i+1);
						answerString = p1+sing.charAt(i)+p2;
					}
				}
			}
		}
		
	}


	private static void generateBlankAnswerString() {
		String sample = getGuessList().get(0);
		for(int i = 0; i < sample.length(); i++){
			answerString+="X";
		}
		System.out.println("BlankAnswerString is "+answerString);
	}


	private static void generateNumGuessMap() {
		for (String gKey : guessMap.keySet()) {
			if (guessMap.get(gKey) != 0) {
				add2NumGuessMap(guessMap.get(gKey), gKey);
			}
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
		for(int i = 0; i < list.size(); i++){
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
		for(int key : possMap.keySet()){
			System.out.print(key+" ==> ");
			printListChar(possMap.get(key));
			System.out.println();
		}
		System.out.println("================================");
	}
	
	private static void printNumGuessMap(Map<Integer,List<String>> aMap){
		for(int key : aMap.keySet()){
			System.out.print(key+" ==> ");
			printListString(aMap.get(key));
			System.out.println();
		}
		System.out.println("================================");
	}


	private static void printListChar(List<Character> list) {
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
	
	
	private static void printMatrix(final char[][] matrix){
		final int length = matrix.length;
		System.out.println("=========Matrix is===========:");
		for(int row = 0; row < length; row++){
			System.out.println(Arrays.toString(matrix[row]));
		}
		System.out.println("===========================");
	}
	
	private static void printListCharArr(List<char[]> list){
		final int size = list.size();
		System.out.println("=========List<char[]> is===========:");
		for(int row = 0; row < size; row++){
			System.out.println(Arrays.toString(list.get(row)));
		}
		System.out.println("===========================");
	}


}
