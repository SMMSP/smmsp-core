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

import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.smmsp.astro.KeplerianOrbit;

/**
 * JUnit test to run through the 
 *
 * @author Sean P Madden
 */
public class KeplerianOrbitPropTimeTests {
	
	private KeplerianOrbit kep;
	
	/**
	 * Sets up this test.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		kep = new KeplerianOrbit();
		kep.set_epoch(new GregorianCalendar(2012, 10, 12));
	}

	@Test
	public void testEqualDates() { 
		GregorianCalendar test = new GregorianCalendar(2012, 10, 12);
		
		Assert.assertEquals(0.0, kep.getPropogationTime(test));
	}
	
	@Test
	public void testPlusOneDate(){
		GregorianCalendar test = new GregorianCalendar(2012, 10, 13);
		
		Assert.assertEquals(1.0, kep.getPropogationTime(test));
	}
	
	@Test
	public void testMinusOneDate(){
		GregorianCalendar test = new GregorianCalendar(2012, 10, 11);
		
		Assert.assertEquals(-1.0, kep.getPropogationTime(test));
	}

	@Test
	public void testPlus60Days(){
		GregorianCalendar test = new GregorianCalendar(2012, 12, 11);
		
		Assert.assertEquals(60.0, kep.getPropogationTime(test));
	}
	
	@Test
	public void testMinus60Days(){
		GregorianCalendar test = new GregorianCalendar(2012, 8, 13, 0, 0, 0);
		
		Assert.assertEquals(-60.0, kep.getPropogationTime(test));
	}
	
	@Test
	public void testPlus12Hours(){
		GregorianCalendar test = new GregorianCalendar(2012, 10, 12, 12, 0, 0);
		
		Assert.assertEquals(.50, kep.getPropogationTime(test));
	}
	
	@Test
	public void testMinus12Hours(){
		GregorianCalendar test = new GregorianCalendar(2012, 10, 11, 12, 0, 0);
		
		Assert.assertEquals(-0.50, kep.getPropogationTime(test));
	}
	
	@Test
	public void testPlus36Minutes(){
		GregorianCalendar test = new GregorianCalendar(2012, 10, 12, 0, 36, 0);
		
		Assert.assertEquals(0.025, kep.getPropogationTime(test));
	}
	
	@Test
	public void testMinus36Minutes(){
		GregorianCalendar test = new GregorianCalendar(2012, 10, 11, 23, 24, 0);
		
		Assert.assertEquals(-0.025, kep.getPropogationTime(test));
	}
	
	@Test
	public void testPlus54Seconds(){
		GregorianCalendar test = new GregorianCalendar(2012, 10, 12, 0, 0, 54);
		
		Assert.assertEquals(0.000625, kep.getPropogationTime(test));
	}
	
	@Test
	public void testMinus54Seconds(){
		GregorianCalendar test = new GregorianCalendar(2012, 10, 11, 23, 59, 6);
		
		Assert.assertEquals(-0.000625, kep.getPropogationTime(test));
	}
}
