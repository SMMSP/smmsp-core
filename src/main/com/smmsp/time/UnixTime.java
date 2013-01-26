/*
 * UnixTime.java
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
 * @author sean
 *
 */
public class UnixTime implements TimeInstant {

	public static final int EPOCH_YEAR = 1970;
	public static final int EPOCH_MONTH = 1;
	public static final int EPOCH_DAY = 1;

	private GregorianDate date = new GregorianDate();
	private int hours = 0;
	private int minutes = 0;
	private int seconds = 0;

	/**
	 * Empty constructor - defaults to 1/1/1970
	 */
	public UnixTime() {
		this.date = new GregorianDate(EPOCH_YEAR, EPOCH_MONTH, EPOCH_DAY);
	}

	/**
	 * @param _years
	 * @param _months
	 * @param _days
	 */
	public UnixTime(final int _years, final int _months, final int _days) {
		this.date = new GregorianDate(_years, _months, _days);
	}

	/**
	 * @param _years
	 * @param _months
	 * @param _days
	 * @param _hours
	 * @param _minutes
	 * @param _seconds
	 */
	public UnixTime(final int _years, final int _months, final int _days,
			final int _hours, final int _minutes, final int _seconds) {
		this(_years, _months, _days);
		this.hours = _hours;
		this.minutes = _minutes;
		this.seconds = _seconds;
	}

	/**
	 * Returns the unix timestamp represented by this object
	 * @return
	 * @throws TimeException
	 */
	public long toTimestamp() throws TimeException {
		final int numYearsSinceEpoch = this.date.getYears() - EPOCH_YEAR;

		if (numYearsSinceEpoch < 0) {
			throw new TimeException("Years must be >= " + EPOCH_YEAR);
		}

		int daysSinceEpoch = 0;
		for (int i = 0; i < numYearsSinceEpoch; ++i) {
			if (GregorianDate.isLeapYear(EPOCH_YEAR + i)) {
				daysSinceEpoch += TimeConstants.DAYS_IN_LEAP_YEAR;
			} else {
				daysSinceEpoch += TimeConstants.DAYS_IN_YEAR;
			}
		}

		daysSinceEpoch += this.date.getDays() - 1;
		int[] monthDays;
		if (this.date.isLeapYear()) {
			monthDays = TimeConstants.GREGORIAN_DAYS_IN_LEAP_MONTH;
		} else {
			monthDays = TimeConstants.GREGORIAN_DAYS_IN_MONTH;
		}

		for (int i = 0; i < (this.date.getMonths() - 1); ++i) {
			daysSinceEpoch += monthDays[i];
		}

		final int hoursInSeconds = this.hours * TimeConstants.SECONDS_IN_HOUR;
		final int minutesInSeconds = this.minutes
				* TimeConstants.SECONDS_IN_MINUTE;
		final long daysInSeconds = daysSinceEpoch
				* TimeConstants.SECONDS_IN_DAY;

		long seconds = daysInSeconds + hoursInSeconds + minutesInSeconds;
		seconds += this.seconds;

		return seconds;
	}

	@Override
	public UnixTime toUnixTime() {
		return this;
	}

}
