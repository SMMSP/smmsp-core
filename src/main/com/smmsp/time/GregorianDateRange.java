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
 * @author sean
 *
 */
public class GregorianDateRange {

	private GregorianDate _start;
	private GregorianDate _end;
	
	public GregorianDateRange(int startYear, int startMonth, int startDay,
			int endYear, int endMonth, int endDay){
		_start = new GregorianDate(startYear, startMonth, startDay);
		_end = new GregorianDate(endYear, endMonth, endDay);
	}
	
	public GregorianDateRange(GregorianDate start, GregorianDate end){
		_start = start;
		_end = end;
	}
	
	public boolean isWithinRange(GregorianDate date){
		throw new UnsupportedOperationException();
	}
	
	public boolean isBefore(GregorianDate date){
		throw new UnsupportedOperationException();
	}
	
	public boolean isAfter(GregorianDate date){
		throw new UnsupportedOperationException();
	}
}
