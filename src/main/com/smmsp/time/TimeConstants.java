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

/**
 * This class contains several constants used in time.
 * 
 * @author sean
 *
 */
public abstract class TimeConstants {

	public static final int SECONDS_IN_DAY = 86400;
	
	public static final int SECONDS_IN_HOUR = 3600;
	
	public static final int SECONDS_IN_MINUTE = 60;
	
	public static final int DAYS_IN_YEAR = 365;
	
	public static final int DAYS_IN_LEAP_YEAR = 366;
	
	public static final int[] GREGORIAN_DAYS_IN_MONTH = {
		31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
	};
	
	public static final int[] GREGORIAN_DAYS_IN_LEAP_MONTH = {
		31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31	
	};
}
