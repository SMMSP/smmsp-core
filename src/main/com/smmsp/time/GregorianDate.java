/**
 * 
 */
package com.smmsp.time;

/**
 * @author sean
 *
 */
public class GregorianDate {

	private int _years = 1900;
	private int _months = 1;
	private int _days = 1;
	
	/**
	 * Constructor - defaults to 1/1/1900
	 */
	public GregorianDate(){
		
	}
	
	public GregorianDate(int years, int months, int days){
		_years = years;
		_months = months;
		_days = days;
	}
	
	/**
	 * @return the _years
	 */
	public int get_years() {
		return _years;
	}

	/**
	 * @return the _months
	 */
	public int get_months() {
		return _months;
	}

	/**
	 * @return the _days
	 */
	public int get_days() {
		return _days;
	}

	/**
	 * Returns true if the year within this UnixTime is a leap year
	 * @return
	 */
	public boolean isLeapYear(){
		return isLeapYear(_years);
	}
	
	/**
	 * Returns true if the year specified by 'year' is a leap year.
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year){
		if(year % 400 == 0){
			return true;
		}else if(year % 100 == 0){
			return false;
		}else if(year % 4 == 0){
			return true;
		}
		return false;
		
	}
}
