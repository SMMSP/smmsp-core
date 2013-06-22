/*
 * UTMTests.java
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
package com.smmsp.tests.geo;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.smmsp.geo.Ellipsoid;
import com.smmsp.geo.Ellipsoids;
import com.smmsp.geo.GeoException;
import com.smmsp.geo.GridCoordinate;
import com.smmsp.geo.UniversalTransverseMercator;

/**
 * These test cases are derived from DMATM 8358.2 "THE UNIVERSAL GRIDS:
 * Universal Transverse Mercator (UTM)" Published by the Defense
 * Mapping Agency in September 1989
 * 
 * @author sean
 */
public class UTMTests
{

	@BeforeClass
	public static void setupProperties()
	{
		/*
		 * We need to adjust the Semi-major and semi-minor
		 * axes of the Earth as these test cases were derived
		 * using the International Ellipsoid and NOT WGS-84.
		 */
		Ellipsoid international;
		try
		{
			international = Ellipsoids.findByName("INTERNATIONAL");
			UniversalTransverseMercator.setDefaultEllipsoid(international);
		} catch (GeoException e)
		{
			e.printStackTrace();
		}
	}
	
	protected void verifyGridCoordinate(
			final int zone,
			final double northing,
			final double easting,
			final double scaleFactor,
			final GridCoordinate.Double out)
	{
		assertEquals("Zone is out of range", zone, out.getZone());
		assertEquals("Expecting Northing", northing, out.getNorthing(), .1);
		assertEquals("Expecting Easting", easting, out.getEasting(), .1);
		assertEquals("Expecting Scale Factor", scaleFactor, out.getScaleFactor(), .0000001);
	}
	
	@Test
	public void test1() throws GeoException
	{
		GridCoordinate.Double out = 
			UniversalTransverseMercator.convertGeographicToUTMGrid(73., 45.);
		verifyGridCoordinate(38, 8100702.9, 500000.0, .9996, out);
		
	}

	@Test
	public void test2() throws GeoException
	{
		GridCoordinate.Double out = 
			UniversalTransverseMercator.convertGeographicToUTMGrid(30., 102.);
		verifyGridCoordinate(48, 3322624.35, 210577.93, 1.00063354, out);
	}
	
	@Test
	public void test3() throws GeoException
	{
		GridCoordinate.Double out = 
			UniversalTransverseMercator.convertGeographicToUTMGrid(72.075586111, -113.9120336111);
		verifyGridCoordinate(12, 8000000.01, 400000.0, .99972228, out);
	}
	
	@Test
	public void test4() throws GeoException
	{
		GridCoordinate.Double out = 
				UniversalTransverseMercator.convertGeographicToUTMGrid(30.0018025, 101.99994583);
			verifyGridCoordinate(47, 3322824.08, 789411.59, 1.00063346, out);
	}
	
	@Test
	public void test5() throws GeoException
	{
		GridCoordinate.Double out = 
				UniversalTransverseMercator.convertGeographicToUTMGrid(9.03630722, 0.271416388888);
			verifyGridCoordinate(31, 1000000.0, 200000.0, 1.00071386, out);
	}
	
	@Test
	public void test6() throws GeoException
	{
		GridCoordinate.Double out = 
				UniversalTransverseMercator.convertGeographicToUTMGrid(81.0584686111111, 75.);
			verifyGridCoordinate(43, 9000000.0, 500000., .9996, out);
	}
	
	@Test
	public void test7() throws GeoException
	{
		GridCoordinate.Double out = 
				UniversalTransverseMercator.convertGeographicToUTMGrid(-54.108053333, 0.059359722222);
			verifyGridCoordinate(31, 4000329.42, 307758.89, 1.00005345, out);
	}
}
