/**
 * 
 */
package org.bawaweb.euler;

import java.util.Calendar;

/**
 * @author Navroz
 * https://projecteuler.net/problem=19
 * 
 * 
 *  1 Jan 1900 was a Monday.
    Thirty days has September,
    April, June and November.
    All the rest have thirty-one,
    Saving February alone,
    Which has twenty-eight, rain or shine.
    And on leap years, twenty-nine.
    A leap year occurs on any year evenly divisible by 4, 
    	but not on a century unless it is divisible by 400.

 * 
 * How many Sundays 
 * fell on the first of the month 
 * during the twentieth century 
 * (1 Jan 1901 to 31 Dec 2000)?
 * 
 * OUTPUT
 * -----------
 * There are 171 Sundays 
 *  between Jan-1-1901 and Dec-31-2000
 *
 */
public class CountingSundayz {
	
	private static boolean isSunday(Calendar aCal) {
		int dayOfWeek = aCal.get(Calendar.DAY_OF_WEEK);
		return (dayOfWeek==1);
	}


	public static void main(String[] args) {
		int numSundays = 0;
		Calendar c = Calendar.getInstance();
		for(int year = 1901; year <= 2000; year++) {
			for(int month = 0; month <= 11; month++) {			// month (Jan==0)
				c.set(year,month,1);
				if(isSunday(c)){
					numSundays++;
				}
			}
		}
		
		System.out.println("There are "+numSundays+" Sundays\n between Jan-1-1901 and Dec-31-2000");		

	}

}
