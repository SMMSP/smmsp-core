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
public class GregorianDate implements Comparable<GregorianDate>, TimeInstant {

	/**
	 * The years
	 */
	private int years = 1900;

	/**
	 * The months
	 */
	private int months = 1;

	/**
	 * The days
	 */
	private int days = 1;

	/**
	 * Constructor - defaults to 1/1/1900
	 */
	public GregorianDate() {
		// do nothing.
	}

	/**
	 * Constructor.
	 * @param years
	 * @param months
	 * @param days
	 */
	public GregorianDate(final int years, final int months, final int days) {
		if ((months > 12) || (months < 1)) {
			throw new IllegalArgumentException("Invalid month range");
		}
		if ((days > TimeConstants.getDaysInMonth(months, isLeapYear(years)))
				|| (days < 1)) {
			throw new IllegalArgumentException("Invalid day in month " + months
					+ " (" + days + ")");
		}

		this.years = years;
		this.months = months;
		this.days = days;
	}

	/**
	 * @return the _years
	 */
	public int getYears() {
		return this.years;
	}

	/**
	 * @return the _months
	 */
	public int getMonths() {
		return this.months;
	}

	/**
	 * @return the _days
	 */
	public int getDays() {
		return this.days;
	}

	/**
	 * Returns true if the year within this UnixTime is a leap year
	 * @return
	 */
	public boolean isLeapYear() {
		return isLeapYear(this.years);
	}

	/**
	 * Returns true if the year specified by 'year' is a leap year.
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(final int year) {
		if ((year % 400) == 0) {
			return true;
		} else if ((year % 100) == 0) {
			return false;
		} else if ((year % 4) == 0) {
			return true;
		}
		return false;

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GregorianDate [year=" + this.years + ", month=" + this.months
				+ ", day=" + this.days + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(final GregorianDate o) {
		if (this.years < o.years) {
			return -1;
		} else if (this.years > o.years) {
			return 1;
		}
		// years are equal at this point, check months.
		if (this.months < o.months) {
			return -1;
		} else if (this.months > o.months) {
			return 1;
		}
		// months are equal at this point, check days.

		if (this.days < o.days) {
			return -1;
		} else if (this.days > o.days) {
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
