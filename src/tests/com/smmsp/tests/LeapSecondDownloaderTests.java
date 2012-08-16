/*
* LeapSecondDownloaderTests.java
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

import org.junit.Test;

import com.smmsp.core.net.LeapSecondRetreiver;

/**
 * Tests the leap seconds downloader and parser.
 *
 * @author Sean P Madden
 */
public class LeapSecondDownloaderTests {

	@Test
	public void testLeapSecondsDownloader() {
		int leaps = LeapSecondRetreiver.getLeapSeconds();
		assertTrue(leaps > Integer.MIN_VALUE);
	}

}
