/**
 * 
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
