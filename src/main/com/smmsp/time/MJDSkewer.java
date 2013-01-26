/*
* MJDSkewer.java
* 
*    Copyright (C) 2012 Sean P Madden
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*
*    If you would like to license this code under the GNU LGPL, please see
*    http://www.seanmadden.net/licensing for details.
*
*/
package com.smmsp.time;

/**
 * Mean Julian Date Skewer.  Used to support UTC-TAI-GPS conversions
 * before Jan 1 1972.  Before this date the UTC-TAI offset was based
 * on the current date by a simple formula A + (MJD - B) * C.  This
 * class accepts parameters A, B and C upon creation and provides 
 * scaling support for specified dates.
 * 
 * This class is NOT used for UTC-TAI conversions after JAN 1 1972.
 *
 * @author Sean P Madden
 */
public class MJDSkewer {

	private final double A;
	private final double B;
	private final double C;

	/**
	 * Creates an MJD Skewer for dates pre-1972
	 *
	 * @param a
	 * @param b
	 * @param c
	 */
	public MJDSkewer(final double a, final double b, final double c) {
		this.A = a;
		this.B = b;
		this.C = c;
	}

	/**
	* Skews the date for the UTC-TAI Conversion
	* 
	* @param MJD Date to get the skew
	* @return The UTC-TAI offset at this pre-1972 date.
	*/
	public double skew(final double MJD) {

		return this.A + ((MJD - this.B) * this.C);
	}
}
