/*
 * GPSTime.java
 * Copyright (C) 2012 Sean P Madden
 * This program is free software: you can redistribute it and/or
 * modify
 * it under the terms of the GNU General Public License as published
 * by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 * If you would like to license this code under the GNU LGPL, please
 * see
 * http://www.seanmadden.net/licensing for details.
 */
package com.smmsp.time;

/**
 * This class represents the concept of GPS Time. GPS time represents
 * time using the Week number since the GPS Epoch
 * 
 * @author Sean P Madden
 */
public class GPSTime implements Comparable<GPSTime>, TimeInstant {

	/**
	 * The full GPS week is measured since January 6th 1980 on
	 * Midnight
	 */
	private int fullGPSWeek = 1024;

	/**
	 * How many milliseconds into the current GPS week are we?
	 */
	private long millisecOfCurrentWeek = 0;

	/**
	 * Number of milliseconds in a single GPS week
	 * SecInDay * MillisInSec * DaysInWeek, See
	 * http://www.gps.gov/technical/ps/1995-SPS-signal-specification.
	 * pdf
	 * section 2.3.5
	 */
	public static final long MILLIS_IN_WEEK = 604800000;

	/**
	 * Constructor
	 */
	public GPSTime() {
		// do nothing
	}

	/**
	 * Constructor
	 * @param fullGPSWeek
	 * @param millisIntoWeek
	 */
	public GPSTime(final int fullGPSWeek, final long millisIntoWeek) {
		this.fullGPSWeek = fullGPSWeek;
		this.millisecOfCurrentWeek = millisIntoWeek;
	}

	/**
	 * Returns the _fullGPSWeek
	 * 
	 * @return _fullGPSWeek the _fullGPSWeek
	 */
	public int getFullGPSWeek() {
		return this.fullGPSWeek;
	}

	/**
	 * Sets the _fullGPSWeek
	 * 
	 * @param _fullGPSWeek
	 *            the _fullGPSWeek to set
	 */
	public void setFullGPSWeek(final int _fullGPSWeek) {
		this.fullGPSWeek = _fullGPSWeek;
	}

	/**
	 * Returns the _millisecOfCurrentWeek
	 * 
	 * @return _millisecOfCurrentWeek the _millisecOfCurrentWeek
	 */
	public long getMillisecOfCurrentWeek() {
		return this.millisecOfCurrentWeek;
	}

	/**
	 * Sets the _millisecOfCurrentWeek
	 * 
	 * @param l
	 *            the _millisecOfCurrentWeek to set
	 */
	public void setMillisecOfCurrentWeek(final long l) {
		this.millisecOfCurrentWeek = l;
	}

	public GPSTime add(final GPSTime other) {
		int week = this.fullGPSWeek + other.getFullGPSWeek();
		long millis = this.millisecOfCurrentWeek
				+ other.getMillisecOfCurrentWeek();

		// compute rollover bits
		if (millis >= MILLIS_IN_WEEK) {
			week += adjustedRolloverWeeks(millis);
			millis = adjustedRolloverMillis(millis);
		} else if (millis < 0) {
			week -= adjustedRolloverWeeks(millis) + 1;
			millis = (int) MILLIS_IN_WEEK - adjustedRolloverMillis(millis);
		}

		return new GPSTime(week, millis);
	}

	/**
	 * Subtract 'other' from this time.
	 * 
	 * @param other
	 *            The time to subtract
	 * @return a new GPSTime of the result
	 */
	public GPSTime subtract(final GPSTime other) {
		final GPSTime t = new GPSTime();
		t.setFullGPSWeek(other.getFullGPSWeek() * -1);
		t.setMillisecOfCurrentWeek(other.getMillisecOfCurrentWeek() * -1);
		return this.add(t);
	}

	/**
	 * Adds seconds to this time
	 * 
	 * @param seconds
	 *            The number of seconds to add (go forward in time)
	 * @return new GPSTime of result
	 */
	public GPSTime addSec(final int seconds) {
		return this.addMillis(1000 * seconds);
	}

	/**
	 * Subtract seconds from this time
	 * 
	 * @param seconds
	 *            number of seconds to subtract (go back in time)
	 * @return new GPSTime of result.
	 */
	public GPSTime subtractSec(final int seconds) {
		return this.addSec(-1 * seconds);
	}

	/**
	 * Adds milliseconds to this time, returns a new object
	 * 
	 * @param millis
	 *            Milliseconds to add
	 * @return new GPSTime of result
	 */
	public GPSTime addMillis(final long millis) {
		return this.add(new GPSTime(0, millis));
	}

	/**
	 * Subtract millis from this time
	 * 
	 * @param millis
	 *            number of milliseconds to subtract (go back in time)
	 * @return new GPSTime of result
	 */
	public GPSTime subtractMillis(final long millis) {
		return this.addMillis(-1 * millis);
	}

	/**
	 * Computes the remainder of millis over or under a GPS weeks.
	 * Truncates numbers to the range -MILLIS_IN_WEEK ->
	 * MILLIS_IN_WEEK
	 * 
	 * @param millis
	 *            Number of Millis to truncate
	 * @return Truncated Millis
	 */
	public static long adjustedRolloverMillis(final long millis) {
		return Math.abs(millis) % MILLIS_IN_WEEK;
	}

	/**
	 * Returns the whole number portion of Weeks represented by the
	 * milliseconds
	 * 
	 * @param millis
	 *            The number of millis
	 * @return Whole number of weeks
	 */
	public static int adjustedRolloverWeeks(long millis) {
		millis = Math.abs(millis);
		final long fulls = millis - adjustedRolloverMillis(millis);
		return (int) (fulls / MILLIS_IN_WEEK);
	}

	/**
	 * HashCode
	 * 
	 * @see java.lang.Object#hashCode()
	 * @return
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + this.fullGPSWeek;
		result = (prime * result) + (int) this.millisecOfCurrentWeek;
		return result;
	}

	/**
	 * Equals!
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final GPSTime other = (GPSTime) obj;
		if (this.fullGPSWeek != other.fullGPSWeek) {
			return false;
		}
		if (this.millisecOfCurrentWeek != other.millisecOfCurrentWeek) {
			return false;
		}
		return true;
	}

	/**
	 * ToString
	 * 
	 * @see java.lang.Object#toString()
	 * @return
	 */
	@Override
	public String toString() {
		return "GPSTime [fullGPSWeek=" + this.fullGPSWeek
				+ ", millisecOfCurrentWeek=" + this.millisecOfCurrentWeek + "]";
	}

	@Override
	public int compareTo(final GPSTime o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UnixTime toUnixTime() {
		// TODO Auto-generated method stub
		return null;
	}

}
