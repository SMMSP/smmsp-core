/*
 * GregorianDateTests.java
 * 
 * Copyright (C) 2013 Sean P Madden
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
package com.smmsp.tests.time;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.smmsp.time.GregorianDate;

/**
 * @author sean
 *
 */
public class GregorianDateTests {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testInvalidDates() {
		int[][] invalidDates = {
				{1900, 0, 1},
				{1900, 13, 1},
				{1900, 1, 0},
				{1900, 1, 32},
				{1900, 1, -10},
				{1900, 1, 10000},
				{1900, 2, 0},
				{1900, 2, 29}, // 1900 isn't a leap year
				{1904, 2, 30}, // 1904 is.
				{1903, 2, 29}, // 1903 isn't
				{1900, 3, 0},
				{1900, 3, 32}, 
				{1900, 4, 0},
				{1900, 4, 31},
				{1900, 5, 0},
				{1900, 5, 32},
				{1900, 6, 0},
				{1900, 6, 31},
				{1900, 6, 100},
				{1900, 6, -100},
				{1900, 7, 0},
				{1900, 7, 32},
				{1900, 7, 120},
				{1900, 8, 0},
				{1900, 8, 32},
				{1900, 8, 35},
				{1900, 9, 0},
				{1900, 9, 31},
				{1900, 9, 100},
				{1900, 10, 0},
				{1900, 10, 32},
				{1900, 10, 40},
				{1900, 11, 0},
				{1900, 11, 31},
				{1900, 11, 40},
				{1900, 12, 0},
				{1900, 12, 32},
				{1900, 12, 40},
				{1900, 13, 0},
				{2013, 2, 29}, // 2013 is not a leap year
		};
		
		for(int[] dateArr : invalidDates){
			try{
				
				@SuppressWarnings("unused")
				GregorianDate date = new GregorianDate(
						dateArr[0], dateArr[1], dateArr[2]
						);
				
				fail("Expecting IllegalArgumentException for date " 
						+ Arrays.toString(dateArr));
			}catch(IllegalArgumentException e){
				// all good - expecting this.
			}
		}
		
	}
	
	@Test
	public final void testToString(){
		GregorianDate date = new GregorianDate(2000, 1, 1);
		
		String str = date.toString();
		assertNotNull(str);
	}
	
	

}
