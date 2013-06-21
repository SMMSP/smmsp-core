/*
* KeplerianOrbitPropTimeTests.java
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
import org.junit.Ignore;
import org.junit.Test;

import com.smmsp.astro.KeplerianOrbit;
import com.smmsp.time.TimeException;
import com.smmsp.time.UnixTime;

/**
 * JUnit test to run through the 
 *
 * @author Sean P Madden
 */
@Ignore
public class KeplerianOrbitPropTimeTests {
	
	private KeplerianOrbit kep;
	
	/**
	 * Sets up this test.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before 
	public void setUp() throws Exception {
		kep = new KeplerianOrbit(new UnixTime(2012, 10, 12));
	}

	@Test
	public void testEqualDates() throws TimeException { 
		UnixTime test = new UnixTime(2012, 10, 12);
		
		assertTrue(0.0 == kep.getPropogationTime(test));
	}
	
	@Test
	public void testPlusOneDate() throws TimeException{
		UnixTime test = new UnixTime(2012, 10, 13);
		
		assertTrue(1.0 == kep.getPropogationTime(test));
	}
	
	@Test
	public void testMinusOneDate() throws TimeException{
		UnixTime test = new UnixTime(2012, 10, 11);
		
		assertTrue(-1.0 == kep.getPropogationTime(test));
	}

	@Test
	public void testPlus60Days() throws TimeException{
		UnixTime test = new UnixTime(2012, 12, 11);
		
		assertTrue(60.0 == kep.getPropogationTime(test));
	}
	
	@Test
	public void testMinus60Days() throws TimeException{
		UnixTime test = new UnixTime(2012, 8, 13, 0, 0, 0);
		
		assertTrue(-60.0 == kep.getPropogationTime(test));
	}
	
	@Test
	public void testPlus12Hours() throws TimeException{
		UnixTime test = new UnixTime(2012, 10, 12, 12, 0, 0);
		
		assertTrue(.50 == kep.getPropogationTime(test));
	}
	
	@Test
	public void testMinus12Hours() throws TimeException{
		UnixTime test = new UnixTime(2012, 10, 11, 12, 0, 0);
		
		assertTrue(-0.50 == kep.getPropogationTime(test));
	}
	
	@Test
	public void testPlus36Minutes() throws TimeException{
		UnixTime test = new UnixTime(2012, 10, 12, 0, 36, 0);
		
		assertTrue(0.025 == kep.getPropogationTime(test));
	}
	
	@Test
	public void testMinus36Minutes() throws TimeException{
		UnixTime test = new UnixTime(2012, 10, 11, 23, 24, 0);
		
		assertTrue(-0.025 == kep.getPropogationTime(test));
	}
	
	@Test
	public void testPlus54Seconds() throws TimeException{
		UnixTime test = new UnixTime(2012, 10, 12, 0, 0, 54);
		
		assertTrue(0.000625 == kep.getPropogationTime(test));
	}
	
	@Test
	public void testMinus54Seconds() throws TimeException{
		UnixTime test = new UnixTime(2012, 10, 11, 23, 59, 6);
		
		assertTrue(-0.000625 == kep.getPropogationTime(test));
	}
}
