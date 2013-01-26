/*
 * GregorianDateRange.java
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
 * This class represents a span of two gregorian dates.  For the 
 * purposes of this class, the range is [start, end). 
 * @author sean
 *
 */
public class GregorianDateRange {

	private final GregorianDate _start;
	private final GregorianDate _end;

	public GregorianDateRange(final int startYear, final int startMonth,
			final int startDay, final int endYear, final int endMonth,
			final int endDay) {
		this._start = new GregorianDate(startYear, startMonth, startDay);
		this._end = new GregorianDate(endYear, endMonth, endDay);
	}

	public GregorianDateRange(final GregorianDate start, final GregorianDate end) {
		this._start = start;
		this._end = end;
	}

	public boolean isWithinRange(final GregorianDate date) {
		throw new UnsupportedOperationException();
	}

	public boolean isBefore(final GregorianDate date) {
		throw new UnsupportedOperationException();
	}

	public boolean isAfter(final GregorianDate date) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return "GregorianDateRange [start=" + this._start + ", end="
				+ this._end + "]";
	}
}
