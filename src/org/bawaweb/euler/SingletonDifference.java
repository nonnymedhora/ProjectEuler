/**
 * 
 */
package org.bawaweb.euler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Navroz
 * 
 * Singleton difference
	Problem 136
	Diff. 50%
	
	The positive integers, x, y, and z, 
	are consecutive terms of an arithmetic progression. 
	
	Given that n is a positive integer, 
	the equation, x^2 − y^2 − z^2 = n, 
	has exactly one solution when n = 20:
	
	13^2 − 10^2 − 7^2 = 20
	
	In fact there are twenty-five values of n below one hundred 
	for which the equation has a unique solution.
	
	How many values of n less than fifty million 
	have exactly one solution?
	
	
	
	Output----100
	-------------
	For n<100 there are 26
		3 --> [4 3 2 ]
		4 --> [3 2 1 ]
		7 --> [9 7 5 ]
		11 --> [14 11 8 ]
		12 --> [8 6 4 ]
		16 --> [6 4 2 ]
		19 --> [24 19 14 ]
		20 --> [13 10 7 ]
		23 --> [29 23 17 ]
		28 --> [18 14 10 ]
		31 --> [39 31 23 ]
		43 --> [54 43 32 ]
		44 --> [28 22 16 ]
		47 --> [59 47 35 ]
		48 --> [16 12 8 ]
		52 --> [33 26 19 ]
		59 --> [74 59 44 ]
		67 --> [84 67 50 ]
		68 --> [43 34 25 ]
		71 --> [89 71 53 ]
		76 --> [48 38 28 ]
		79 --> [99 79 59 ]
		80 --> [26 20 14 ]
		87 --> [37 29 21 ]
		92 --> [58 46 34 ]
		95 --> [25 19 13 ]
		


 *
 */
public class SingletonDifference {
	
	final static int target = 100;
	
	//	stores [n ==> {x,y,z}]
	static Map<Integer,List<Integer>> solutionMap = new HashMap<Integer,List<Integer>>();
	
	static boolean isProgression(int x, int y, int z) {		
		if ( x > y && y > z ) {
			int diff1 = x - y;
			int diff2 = y - z;
			if( diff1 == diff2 )
				return true;
		}		
		return false;
	}
	
	static boolean isSingletonDifference(int n, int x, int y, int z) {
		return (n == ((int)Math.pow(x,2)-(int)Math.pow(y,2)-(int)Math.pow(z,2)));		
	}
	
	public static void main(String[] args) {
		
		for(int n = 1; n < target; n++) {
			List<Integer> list = new ArrayList<Integer>();
			
			for(int x = 99; x >=3; x--) {
				for(int y = 98; y>=2; y--) {
					for(int z = 97; z>=1; z--) {
						if( x > y && y > z ) {
							if( isProgression( x,y,z ) ) {
								if (isSingletonDifference(n, x, y, z)) {
									list.add(x);
									list.add(y);
									list.add(z);

									solutionMap.put(n, list);
								}
							}
						}
					}
				}
			}			
		}
		
		solutionMap = getUniqueSolutions(solutionMap);
		
		//	x-30,y-29,z-28		solCount	40	n<=100
		//	x-50,y-49,z-48		solCount	52	n<=100	
		//	x-90,y-89,z-88		solCount	63	n<=100
		//	x-99,y-98,z-97		solCount	65	n>100
		System.out.println("For n<"+target+" there are "+solutionMap.size());	
		printMap(solutionMap);
	}

	private static Map<Integer, List<Integer>> getUniqueSolutions(Map<Integer, List<Integer>> smap) {
		Map<Integer,List<Integer>> uMap = new HashMap<Integer,List<Integer>>();
		Set<Integer> set = smap.keySet();
		Iterator<Integer> iter = set.iterator();
		while( iter.hasNext() ) {
			int n = iter.next();
			List<Integer> list = smap.get(n);
			if(list.size()==3) {
				uMap.put(n,list);
			}
			
		}
		return uMap;
	}

	private static void printMap(Map<Integer, List<Integer>> map) {
		StringBuilder s = new StringBuilder();
		TreeMap<Integer,List<Integer>> tm = new TreeMap<Integer, List<Integer>>();
		tm.putAll(map);
		
		Set<Integer> aSet =	tm.keySet();		
		Iterator<Integer> iter = aSet.iterator();
		while(iter.hasNext()){
			int n = iter.next();
			List<Integer> list = tm.get(n);
			String strList = openList(list);
			s.append(n + " --> ["+strList+"]\n");
		}
		
		/*//Collections.sort(aSet.stream().sorted().collect(Collectors.toList()),(o1,o2)->o1.compareTo(o2));
		 //--
		 for(int n = 1; n < target; n++) {
			if( map.get(n) != null) {
				List<Integer> list = map.get(n);
				String strList = openList(list);
				s.append(n + " --> ["+strList+"]\n");	
			}
		}*/
		System.out.println(s.toString());
	}

	private static String openList(List<Integer> list) {
		String s = "";
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()) {
			s += it.next() + " ";
		}
		return s;
	}

}
