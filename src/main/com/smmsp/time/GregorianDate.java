/*
 * GregorianDate.java
 * 
 * Copyright (C) 2012 Sean P Madden
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 * 
 * If you would like to license this code under the GNU LGPL, please
 * see http://www.seanmadden.net/licensing for details.
 */
package com.smmsp.time;

/**
 * Represents an instance in time on the gregorian calendar.
 * @author sean
 *
 */
public class GregorianDate implements 
				Comparable<GregorianDate>, TimeInstant{

	/**
	 * The years
	 */
	private int _years = 1900;
	
	/**
	 * The months
	 */
	private int _months = 1;
	
	/**
	 * The days
	 */
	private int _days = 1;
	
	/**
	 * Constructor - defaults to 1/1/1900
	 */
	public GregorianDate(){
		
	}
	
	/**
	 * Constructor.
	 * @param years
	 * @param months
	 * @param days
	 */
	public GregorianDate(int years, int months, int days){
		if(months > 12 || months < 1){
			throw new IllegalArgumentException("Invalid month range");
		}
		if(days > TimeConstants.GetDaysInMonth(months, isLeapYear(years))
				|| days < 1){
			throw new IllegalArgumentException(
					"Invalid day in month " + months + 
					" (" + days + ")");
		}
		
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GregorianDate [year=" + _years + ", month=" + _months
				+ ", day=" + _days + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(GregorianDate o) {
		if(_years < o._years){
			return -1;
		}else if(_years > o._years){
			return 1;
		}
		// years are equal at this point, check months.
		if(_months < o._months){
			return -1;
		}else if(_months > o._months){
			return 1;
		}
		// months are equal at this point, check days.
		
		if(_days < o._days){
			return -1;
		}else if(_days > o._days){
			return 1;
		}
		// everything is equal.
		return 0;
	}

	@Override
	public UnixTime toUnixTime() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
