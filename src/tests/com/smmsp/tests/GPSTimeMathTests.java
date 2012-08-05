/*
* GPSTimeMathTests.java
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
 * [Insert class description here]
 *
 * @author Sean P Madden
 */
public class GPSTimeMathTests {

	private GPSTime time;
	
	/**
	 * [Place method description here]
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		time = new GPSTime(1709, 432000000);
	}

	@Test
	public void testTimeAddOneWeek() {
		GPSTime a = new GPSTime(1, 0);
		GPSTime exp = new GPSTime(1710, 432000000);
		
		GPSTime res = time.add(a);
		
		assertEquals(exp, res);
	}
	
	@Test
	public void testSubOneWeek(){
		GPSTime a = new GPSTime(1, 0);
		GPSTime exp = new GPSTime(1708, 432000000);
		
		GPSTime res = time.subtract(a);
		
		assertEquals(exp, res);
	}
	
	@Test
	public void testAddHalfWeek(){
		GPSTime a = new GPSTime(0, GPSTime.MILLIS_IN_WEEK/2);
		GPSTime exp = new GPSTime(1710, 129600000);
		
		GPSTime res = time.add(a);
		
		assertEquals(exp, res);
	}
	
	@Test
	public void testSubHalfWeek(){
		GPSTime a = new GPSTime(0, GPSTime.MILLIS_IN_WEEK/2);
		GPSTime exp = new GPSTime(1709, 129600000);
		
		GPSTime res = time.subtract(a);
		
		assertEquals(exp, res);
	}
	
	@Test
	public void testAddOneSecond(){
		GPSTime exp = new GPSTime(1709, 432001000);
		
		GPSTime res = time.addSec(1);
		
		assertEquals(exp, res);
	}
	
	@Test
	public void testAddOneWeekSec(){
		GPSTime exp = new GPSTime(1710, 432000000);
		
		GPSTime res = time.addSec(86400*7);
		
		assertEquals(exp, res);
	}
	
	@Test
	public void testSubOneSecond(){
		GPSTime exp = new GPSTime(1709, 431999000);
		
		GPSTime res = time.subtractSec(1);
		
		assertEquals(exp, res);
	}
	
	@Test
	public void testSubOneWeekSec(){
		GPSTime exp = new GPSTime(1708, 432000000);
		
		GPSTime res = time.subtractSec(86400*7);
		
		assertEquals(exp, res);
	}
	
	@Test
	public void testAddOneWeekMillis(){
		GPSTime exp = new GPSTime(1710, 432000000);
		
		GPSTime res = time.addMillis(86400*7000);
		
		assertEquals(exp, res);
	}
	
	@Test
	public void testSubOneWeekMillis(){
		GPSTime exp = new GPSTime(1708, 432000000);
		
		GPSTime res = time.subtractMillis(86400*7000);
		
		assertEquals(exp, res);
	}

}
