/*
* GPSTimeUtilsTests.java
* 
*    Copyright (C) 2012 Sean P Madden
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*
*    If you would like to license this code under the GNU LGPL, please see
*    http://www.seanmadden.net/licensing for details.
*
*/
package com.smmsp.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.smmsp.core.GPSTime;

/**
 * Tests the GPSTime Utility functions
 *
 * @author Sean P Madden
 */
public class GPSTimeUtilsTests {

	/**
	 * [Place method description here]
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAdjustedMilliRollover1() {
		int millis = GPSTime.adjustedRolloverMillis(GPSTime.MILLIS_IN_WEEK + 1);
		
		assertEquals(1, millis);
	}

	@Test
	public void testAdjustedMilliRollover2(){
		int millis = GPSTime.adjustedRolloverMillis(GPSTime.MILLIS_IN_WEEK *2 -1);
		
		assertEquals(GPSTime.MILLIS_IN_WEEK -1, millis);
	}
	
	@Test
	public void testAdjustedMilliRollover3(){
		int millis = GPSTime.adjustedRolloverMillis(0);
		
		assertEquals(0, millis);
	}
	
	@Test
	public void testAdjustedMilliRollover4(){
		int millis = GPSTime.adjustedRolloverMillis(-1 * GPSTime.MILLIS_IN_WEEK);
		
		assertEquals(0, millis);
	}
	
	@Test
	public void testAdjustedMilliRollover5(){
		int millis = GPSTime.adjustedRolloverMillis(-1 * GPSTime.MILLIS_IN_WEEK -1);
		
		assertEquals(1, millis);
	}
	
	@Test
	public void testAdjustedMilliRollover6(){
		int millis = GPSTime.adjustedRolloverMillis(-5 * GPSTime.MILLIS_IN_WEEK);
		
		assertEquals(0, millis);
	}
	
	@Test
	public void testAdjustedWeekRollover1(){
		int weeks = GPSTime.adjustedRolloverWeeks(GPSTime.MILLIS_IN_WEEK);
		
		assertEquals(1, weeks);
	}
	
	@Test 
	public void testAdjustedWeekRollover2(){
		int weeks = GPSTime.adjustedRolloverWeeks(0);
		
		assertEquals(0, weeks);
	}
	
	@Test
	public void testAdjustedWeekRollover3(){
		int weeks = GPSTime.adjustedRolloverWeeks(-1 * GPSTime.MILLIS_IN_WEEK);
		
		assertEquals(1, weeks);
	}
	
	@Test
	public void testAdjustedWeekRollover4(){
		int weeks = GPSTime.adjustedRolloverWeeks(-5 * GPSTime.MILLIS_IN_WEEK);
		
		assertEquals(5, weeks);
	}
}
