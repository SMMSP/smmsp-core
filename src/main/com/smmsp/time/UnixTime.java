/**
 * 
 */
package com.smmsp.time;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author sean
 *
 */
public class UnixTime implements TimeInstant{
	
	public static final int EPOCH_YEAR = 1970;
	public static final int EPOCH_MONTH = 1;
	public static final int EPOCH_DAY = 1;

	private int _years = 1970;
	private int _months = 1;
	private int _days = 1;
	private int _hours = 0;
	private int _minutes = 0;
	private int _seconds = 0;
	private int _nanos = 0;
	
	/**
	 * Empty constructor
	 */
	public UnixTime() {
	}

	/**
	 * @param _years
	 * @param _months
	 * @param _days
	 */
	public UnixTime(int _years, int _months, int _days) {
		this._years = _years;
		this._months = _months;
		this._days = _days;
	}

	/**
	 * @param _years
	 * @param _months
	 * @param _days
	 * @param _hours
	 * @param _minutes
	 * @param _seconds
	 */
	public UnixTime(int _years, int _months, int _days, int _hours,
			int _minutes, int _seconds) {
		this._years = _years;
		this._months = _months;
		this._days = _days;
		this._hours = _hours;
		this._minutes = _minutes;
		this._seconds = _seconds;
	}

	/**
	 * Constructor
	 * 
	 * @param _years
	 * @param _months
	 * @param _days
	 * @param _hours
	 * @param _minutes
	 * @param _seconds
	 * @param _nanos
	 */
	public UnixTime(int _years, int _months, int _days, int _hours,
			int _minutes, int _seconds, int _nanos) {
		this._years = _years;
		this._months = _months;
		this._days = _days;
		this._hours = _hours;
		this._minutes = _minutes;
		this._seconds = _seconds;
		this._nanos = _nanos;
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
	
	/**
	 * Returns the unix timestamp represented by this object
	 * @return
	 * @throws TimeException
	 */
	public long toTimestamp() throws TimeException{
		int numYearsSinceEpoch = _years - EPOCH_YEAR;
		
		if(numYearsSinceEpoch < 0){
			throw new TimeException("Years must be >= " + EPOCH_YEAR);
		}
		
		int daysSinceEpoch = 0;
		for(int i = 0; i < numYearsSinceEpoch; ++i){
			if(isLeapYear(EPOCH_YEAR + i)){
				daysSinceEpoch += TimeConstants.DAYS_IN_LEAP_YEAR;
			}else{
				daysSinceEpoch += TimeConstants.DAYS_IN_YEAR;
			}
		}
		
		daysSinceEpoch += _days - 1;
		int[] monthDays;
		if(isLeapYear()){
			monthDays = TimeConstants.GREGORIAN_DAYS_IN_LEAP_MONTH;
		}else{
			monthDays = TimeConstants.GREGORIAN_DAYS_IN_MONTH;
		}
		
		for(int i = 0; i < _months - 1; ++i){
			daysSinceEpoch += monthDays[i];
		}
		
		int hoursInSeconds = _hours * TimeConstants.SECONDS_IN_HOUR;
		int minutesInSeconds = _minutes * TimeConstants.SECONDS_IN_MINUTE;
		long daysInSeconds = daysSinceEpoch * TimeConstants.SECONDS_IN_DAY;
		
		long seconds = daysInSeconds + hoursInSeconds + minutesInSeconds;
		seconds += _seconds;
		
		return seconds;
	}

	@Override
	public GPSTime toGPSTime() {
		throw new NotImplementedException();
	}

	@Override
	public UnixTime toUnixTime() {
		return this;
	}

}
