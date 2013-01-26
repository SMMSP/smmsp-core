/*
 * JulianDate.java
 * 
 * Copyright (C) 2013 Sean P Madden
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
 * @author sean
 *
 */
public class JulianDate implements Comparable<JulianDate>, TimeInstant{

	/**
	 * Epoch of 12H Nov. 16 1858
	 */
	public static final double JULIAN_REDUCED_OFFSET = 2400000;
	
	/**
	 * Epoch of OH Nov 17. 1858 - introduces by SAO in 1957
	 */
	public static final double JULIAN_MODIFIED_OFFSET = 2400000.5;
	
	/**
	 * Epoch of OH May 24, 1968 - introduced by NASA in 1979
	 */
	public static final double JULIAN_TRUNCATED_OFFSET = 2440000.5;
	
	/**
	 * The internal julian date representation
	 */
	private double _julianDate = 2400000;
	
	/**
	 * Private constructor - only use static methods.
	 */
	private JulianDate(){
		
	}
	
	/**
	 * Creates and returns a new julian date.
	 * @param julianDate
	 * @return
	 */
	public static JulianDate getJulianDate(double julianDate){
		JulianDate d = new JulianDate();
		d._julianDate = julianDate;
		return d;
	}
	
	/**
	 * Creates and returns a new modified julian date
	 * @param mjd
	 * @return
	 */
	public static JulianDate getModifiedJulianDate(double mjd){
		JulianDate d = new JulianDate();
		d._julianDate = mjd + JULIAN_MODIFIED_OFFSET;
		return d;
	}
	
	/**
	 * Creates and returns a new reduced julian date
	 * @param rjd
	 * @return
	 */
	public static JulianDate getReducedJulianDate(double rjd){
		JulianDate d = new JulianDate();
		d._julianDate = rjd + JULIAN_REDUCED_OFFSET;
		return d;
	}
	
	/**
	 * Creates and returns a new truncated julian date
	 * @param tjd
	 * @return
	 */
	public static JulianDate getTruncatedJulianDate(double tjd){
		JulianDate d = new JulianDate();
		d._julianDate = tjd + JULIAN_TRUNCATED_OFFSET;
		return d;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(JulianDate arg0) {
		return Double.compare(_julianDate, arg0._julianDate);
	}

	/**
	 * Returns the standard julian date
	 * @return
	 */
	public double getJulianDate(){
		return _julianDate;
	}
	
	/**
	 * Converts this Julian Date into a modified julian date.
	 * @return
	 */
	public double getModifiedJulianDate(){
		return _julianDate - JULIAN_MODIFIED_OFFSET;
	}
	
	
	/**
	 * Converts this julian date into a reduced julian date.
	 * @return
	 */
	public double getTruncatedJulianDate(){
		return _julianDate - JULIAN_TRUNCATED_OFFSET;
	}

	@Override
	public UnixTime toUnixTime() {
		// TODO Auto-generated method stub
		return null;
	}
}
