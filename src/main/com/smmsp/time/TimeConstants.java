/*
 * TimeConstants.java
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

import java.util.Arrays;
import java.util.List;

/**
 * This class contains several constants used in time.
 * 
 * @author sean
 *
 */
public final class TimeConstants {

	public static final int SECONDS_IN_DAY = 86400;

	public static final int SECONDS_IN_HOUR = 3600;

	public static final int SECONDS_IN_MINUTE = 60;

	public static final int DAYS_IN_YEAR = 365;

	public static final int DAYS_IN_LEAP_YEAR = 366;

	public static final int[] GREGORIAN_DAYS_IN_MONTH = { 31, 28, 31, 30, 31,
			30, 31, 31, 30, 31, 30, 31 };

	public static final int[] GREGORIAN_DAYS_IN_LEAP_MONTH = { 31, 29, 31, 30,
			31, 30, 31, 31, 30, 31, 30, 31 };

	public static final String[] GREGORIAN_MONTH_STRINGS = { "Jan", "Feb",
			"Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
			"Dec" };

	private TimeConstants(){
		// do nothing.
	}
	
	/**
	 * Returns the month number (1 => 12) from the string specified 
	 * by month (three characters)
	 * @param month
	 * @return
	 */
	public static int monthStringToInt(final String month) {
		final List<String> months = Arrays.asList(GREGORIAN_MONTH_STRINGS);
		return months.indexOf(month) + 1;
	}

	/**
	 * Returns the number of days in the month specified by month
	 * @param month The month number (1 => 12);
	 * @param isLeapYear Is the month within a leap year?
	 * @return
	 */
	public static int getDaysInMonth(final int month,
			final boolean isLeapYear) {
		final int monthIdx = month - 1;
		if ((monthIdx < 0) || (monthIdx > 12)) {
			throw new IllegalArgumentException("Month number out of range");
		}
		final int[] daysArray = (isLeapYear) ? GREGORIAN_DAYS_IN_LEAP_MONTH
				: GREGORIAN_DAYS_IN_MONTH;
		return daysArray[monthIdx];
	}
}
